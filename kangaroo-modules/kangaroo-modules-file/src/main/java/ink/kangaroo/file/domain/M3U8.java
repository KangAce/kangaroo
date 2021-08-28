package ink.kangaroo.file.domain;

import ink.kangaroo.common.core.exception.BaseException;
import ink.kangaroo.common.core.utils.StringUtils;
import ink.kangaroo.file.listener.DownloadListener;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bytedeco.javacpp.Loader;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Security;
import java.util.*;
import java.util.concurrent.*;

import static ink.kangaroo.file.domain.Constant.FILESEPARATOR;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/25 0:12
 */
@Data
@Slf4j
public class M3U8 {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    //合并后的文件存储目录
    private String dir;

    //合并后的视频文件名称
    private String fileName;
    private RestTemplate restTemplate;
    private String baseUrl;

    private String streamInf;
    /**
     *
     */
    private String version;
    /**
     *
     */
    private String targetDuration;
    /**
     *
     */
    private String playlistType;
    /**
     *
     */
    private String mediaSequence;

    private Key key;

    private List<TS> tsList;

    //监听间隔
    private volatile long interval = 1000L;
    //重试次数
    private int retryCount = 30;
    //链接连接超时时间（单位：毫秒）
    private long timeoutMillisecond = 100000L;

    //已完成ts片段个数
    private int finishedCount = 0;
    //已经下载的文件大小
    private BigDecimal downloadBytes = new BigDecimal(0);
    //优化内存占用
    private static final BlockingQueue<byte[]> BLOCKING_QUEUE = new LinkedBlockingQueue<>();

    //监听事件
    private Set<DownloadListener> listenerSet = new HashSet<>(5);
    //解密后的片段
    private Set<File> finishedFiles = new ConcurrentSkipListSet<>(Comparator.comparingInt(o -> Integer.parseInt(o.getName().replace(".ts", ""))));
    private int threadCount;

    public void loadKey(String keyStr) throws IOException {
        Key key = new Key();
        key.setIv("");
        String[] split1 = keyStr.split(",");
        for (String s1 : split1) {
            if (s1.contains("METHOD")) {
                key.setMethod(s1.split("=", 2)[1]);
                continue;
            }
            if (s1.contains("URI")) {
                key.setUri(s1.split("=", 2)[1].replace("\"", ""));
                ResponseEntity<Resource> forEntity = restTemplate.getForEntity(baseUrl + key.getUri(), Resource.class);
                InputStream inputStream = Objects.requireNonNull(forEntity.getBody()).getInputStream();
                byte[] bytes = new byte[128];
                int len;
                len = inputStream.read(bytes);
                key.setIsByte(true);
                if (len == 1 << 4) {
                    key.setKeyBytes(Arrays.copyOf(bytes, 16));
                }
                continue;
            }
            if (s1.contains("IV")) {
                key.setIv(s1.split("=", 2)[1]);
            }
        }
        this.key = key;

    }

    public void go() {
        //线程池
        final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadCount);
        int i = 0;
        //如果生成目录不存在，则创建
        File file1 = new File(dir);
        if (!file1.exists())
            file1.mkdirs();
        //执行多线程下载
        for (TS s : tsList) {
            i++;
            fixedThreadPool.execute(getThread(s.getUri(), i));
        }
        fixedThreadPool.shutdown();
        //下载过程监视
        new Thread(() -> {
            int consume = 0;
            //轮询是否下载成功
            while (!fixedThreadPool.isTerminated()) {
                try {
                    consume++;
                    BigDecimal bigDecimal = new BigDecimal(downloadBytes.toString());
                    Thread.sleep(1000L);
                    log.info("已用时" + consume + "秒！\t下载速度：" + StringUtils.convertToDownloadSpeed(new BigDecimal(downloadBytes.toString()).subtract(bigDecimal), 3) + "/s");
                    log.info("\t已完成" + finishedCount + "个，还剩" + (tsList.size() - finishedCount) + "个！");
                    log.info(new BigDecimal(finishedCount).divide(new BigDecimal(tsList.size()), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP) + "%");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("下载完成，正在合并文件！共" + finishedFiles.size() + "个！" + StringUtils.convertToDownloadSpeed(downloadBytes, 3));
            //开始合并视频
            mergeTs();
            //删除多余的ts片段
            deleteFiles();
            log.info("视频合并完成，欢迎使用!");
        }).start();
        startListener(fixedThreadPool);
    }

    /**
     * 合并下载好的ts片段
     */
    private void mergeTs() {
        String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
//        file 'input1.mkv'
        File file = new File(dir + FILESEPARATOR + fileName + FILESEPARATOR + fileName + ".txt");
        if (file.exists()) {
            file.delete();
        }
        File file1 = new File(dir + FILESEPARATOR + fileName + ".mp4");
        if (file1.exists()) {
            file1.delete();
        }
        List<String> tmpFiles = new ArrayList<>();
        for (File finishedFile : finishedFiles) {
            tmpFiles.add("file '" + finishedFile.getAbsolutePath() + "'");
        }
        try {
            FileUtils.writeLines(file, tmpFiles, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        ffmpeg -f concat -safe 0 -i filelist.txt -c copy output.mkv
        ProcessBuilder pb = new ProcessBuilder(ffmpeg, "-f", "concat", "-safe", "0", "-i", file.getAbsolutePath(), "-c", "copy", dir + FILESEPARATOR + fileName + ".mp4");
        try {
            int i = pb.inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除下载好的片段
     */
    private void deleteFiles() {
        File file = new File(dir + FILESEPARATOR + fileName);
        if (file.exists()) {
            file.delete();
        }
//        for (File f : file.listFiles()) {
//            if (f.getName().endsWith(".xy") || f.getName().endsWith(".ts"))
//                f.delete();
//        }
    }

    private void startListener(ExecutorService fixedThreadPool) {
        new Thread(() -> {
            for (DownloadListener downloadListener : listenerSet)
                downloadListener.start();
            //轮询是否下载成功
            while (!fixedThreadPool.isTerminated()) {
                try {
                    Thread.sleep(interval);
                    for (DownloadListener downloadListener : listenerSet)
                        downloadListener.process(baseUrl, finishedCount, tsList.size(), new BigDecimal(finishedCount).divide(new BigDecimal(tsList.size()), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (DownloadListener downloadListener : listenerSet)
                downloadListener.end();
        }).start();
        new Thread(() -> {
            while (!fixedThreadPool.isTerminated()) {
                try {
                    BigDecimal bigDecimal = new BigDecimal(downloadBytes.toString());
                    Thread.sleep(1000L);
                    for (DownloadListener downloadListener : listenerSet)
                        downloadListener.speed(StringUtils.convertToDownloadSpeed(new BigDecimal(downloadBytes.toString()).subtract(bigDecimal), 3) + "/s");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 开启下载线程
     *
     * @param urls ts片段链接
     * @param i    ts片段序号
     * @return 线程
     */
    private Thread getThread(String urls, int i) {
        return new Thread(() -> {
            //自定义请求头
            Map<String, Object> requestHeaderMap = new HashMap<>();
            requestHeaderMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
            int count = 1;
            HttpURLConnection httpURLConnection = null;
            //xy为未解密的ts片段，如果存在，则删除
            File file2 = new File(dir + FILESEPARATOR + fileName + FILESEPARATOR + i + ".xy");
            try {
                FileUtils.forceMkdirParent(file2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (file2.exists()) {
                file2.delete();
            }
            OutputStream outputStream = null;
            InputStream inputStream1 = null;
            FileOutputStream outputStream1 = null;
            byte[] bytes;
            try {
                bytes = BLOCKING_QUEUE.take();
            } catch (InterruptedException e) {
                bytes = new byte[Constant.BYTE_COUNT];
            }
            //重试次数判断
            while (count <= retryCount) {
                try {
                    //模拟http请求获取ts片段文件
//                    ResponseEntity<Resource> forEntity = restTemplate.getForEntity(baseUrl + urls, Resource.class);
//                    InputStream inputStream = forEntity.getBody().getInputStream();
                    //
                    URL url = new URL(baseUrl + urls);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout((int) timeoutMillisecond);
                    for (Map.Entry<String, Object> entry : requestHeaderMap.entrySet())
                        httpURLConnection.addRequestProperty(entry.getKey(), entry.getValue().toString());
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setReadTimeout((int) timeoutMillisecond);
                    httpURLConnection.setDoInput(true);
                    InputStream inputStream = httpURLConnection.getInputStream();
                    try {
                        outputStream = new FileOutputStream(file2);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        continue;
                    }
                    int len;
                    //将未解密的ts片段写入文件
                    while ((len = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, len);
                        synchronized (this) {
                            downloadBytes = downloadBytes.add(new BigDecimal(len));
                        }
                    }
                    outputStream.flush();
                    inputStream.close();
                    inputStream1 = new FileInputStream(file2);
                    int available = inputStream1.available();
                    if (bytes.length < available) {
                        bytes = new byte[available];
                    }
                    inputStream1.read(bytes);
                    File file = new File(dir + FILESEPARATOR + fileName + FILESEPARATOR + i + ".ts");
                    outputStream1 = new FileOutputStream(file);
                    //开始解密ts片段，这里我们把ts后缀改为了xyz，改不改都一样
                    byte[] decrypt = key.decrypt(bytes, available, key.getUri(), key.getIv(), key.getMethod());
                    if (decrypt == null) {
                        outputStream1.write(bytes, 0, available);
                    } else {
                        outputStream1.write(decrypt);
                    }
                    finishedFiles.add(file);
                    break;
                } catch (Exception e) {
                    if (e instanceof InvalidKeyException || e instanceof InvalidAlgorithmParameterException) {
                        log.error("解密失败！");
                        break;
                    }
                    log.debug("第" + count + "获取链接重试！\t" + urls);
                    count++;
//                    e.printStackTrace();
                } finally {
                    try {
                        if (inputStream1 != null) {
                            inputStream1.close();
                        }
                        if (outputStream1 != null) {
                            outputStream1.close();
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        BLOCKING_QUEUE.put(bytes);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (count > retryCount) {
                //自定义异常
                throw new BaseException("连接超时！" + urls);
            }
            finishedCount++;
//                log.info(urls + "下载完毕！\t已完成" + finishedCount + "个，还剩" + (tsSet.size() - finishedCount) + "个！");
        });
    }

    public void setThreadCount(int threadCount) {
        if (BLOCKING_QUEUE.size() < threadCount) {
            for (int i = BLOCKING_QUEUE.size(); i < threadCount * Constant.FACTOR; i++) {
                try {
                    BLOCKING_QUEUE.put(new byte[Constant.BYTE_COUNT]);
                } catch (InterruptedException ignored) {
                }
            }
        }
        this.threadCount = threadCount;
    }

}
