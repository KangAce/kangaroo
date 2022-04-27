package ink.kangaroo.common.core.utils.file;

import ink.kangaroo.common.core.utils.StringUtils;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 文件处理工具类
 *
 * @author Kangaroo
 */
public class FileUtils extends org.apache.commons.io.FileUtils {
    public static void main(String[] args) {
//        String s = cutVideo("D://upload/AirDroid/ffmpeg.exe", "D:\\upload\\avatar\\20220304\\db3c013ec8961eb7f0d9ca5b533fafb6\\13\\13.mp4", "D:\\upload\\tmp\\20220305\\5a4035d7f02347fb9f4c69429c642c46.mp4", "00:00:02", "00:00:05");
//        System.out.println(s);
//        File file = new File("F:\\video\\b79172577bb24c2fa66017c909ecc878.mp4");
//        File file = new File("F:\\tools\\111.png");
//        String md5 = MD5.create().digestHex(file);
//        System.out.println(md5);
//        for (int i = 0; i < 10; i++) {
//            new FileUtils().sout();
//        }
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(15, 15, 0, TimeUnit.HOURS,
                new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.DiscardOldestPolicy());

        try {


            backupFile("D:\\maven\\repository", "D:\\maven\\repositoryBackup",threadPool);
            threadPool.shutdown();

//            createParentDirectories(new File(""));
//            copyDirectory(new File(""),new File(""));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sout() {
        System.out.println(this.getClass().getName());
    }

    /*****
     * 剪辑视频
     * @param vedioEditPath ffmpeg 执行文件路径
     * @param filePath 操作文件路径
     * @param outFilePath 输出文件路径
     * @param startTime 开始时间
     * @param duration 持续时间
     * @return 返回生成文件路径，若失败则返回null
     */
    public static String cutVideo(String vedioEditPath, String filePath, String outFilePath, String startTime, String duration) {

        System.out.println("开始裁剪");
        System.out.println(vedioEditPath + filePath + outFilePath + startTime + duration);
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(vedioEditPath, "-ss", startTime, "-t", duration, "-i", filePath, "-vcodec", "copy", "-acodec", "copy", outFilePath);

            Process process = processBuilder.start();

            InputStream stderr = process.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null)
                System.out.println(line);
            ;
            process.waitFor();

            if (br != null)
                br.close();
            if (isr != null)
                isr.close();
            if (stderr != null)
                stderr.close();
            System.out.println("裁剪结束");
            return outFilePath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 字符常量：斜杠 {@code '/'}
     */
    public static final char SLASH = '/';

    /**
     * 字符常量：反斜杠 {@code '\\'}
     */
    public static final char BACKSLASH = '\\';

    public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";

    /**
     * 输出指定文件的byte数组
     * 
     * @param filePath 文件路径
     * @param os 输出流
     * @return
     */
    public static void writeBytes(String filePath, OutputStream os) throws IOException
    {
        FileInputStream fis = null;
        try
        {
            File file = new File(filePath);
            if (!file.exists())
            {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0)
            {
                os.write(b, 0, length);
            }
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            if (os != null)
            {
                try
                {
                    os.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除文件
     * 
     * @param filePath 文件
     * @return
     */
    public static boolean deleteFile(String filePath)
    {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists())
        {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 文件名称验证
     * 
     * @param filename 文件名称
     * @return true 正常 false 非法
     */
    public static boolean isValidFilename(String filename)
    {
        return filename.matches(FILENAME_PATTERN);
    }

    /**
     * 检查文件是否可下载
     * 
     * @param resource 需要下载的文件
     * @return true 正常 false 非法
     */
    public static boolean checkAllowDownload(String resource)
    {
        // 禁止目录上跳级别
        if (StringUtils.contains(resource, ".."))
        {
            return false;
        }

        // 检查允许下载的文件规则
        if (ArrayUtils.contains(MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION, FileTypeUtils.getFileType(resource)))
        {
            return true;
        }

        // 不在允许下载的文件规则
        return false;
    }

    /**
     * 下载文件名重新编码
     * 
     * @param request 请求对象
     * @param fileName 文件名
     * @return 编码后的文件名
     */
    public static String setFileDownloadHeader(HttpServletRequest request, String fileName) throws UnsupportedEncodingException
    {
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        if (agent.contains("MSIE"))
        {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        }
        else if (agent.contains("Firefox"))
        {
            // 火狐浏览器
            filename = new String(fileName.getBytes(), "ISO8859-1");
        }
        else if (agent.contains("Chrome"))
        {
            // google浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        else
        {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }

    /**
     * 返回文件名
     *
     * @param filePath 文件
     * @return 文件名
     */
    public static String getName(String filePath)
    {
        if (null == filePath)
        {
            return null;
        }
        int len = filePath.length();
        if (0 == len)
        {
            return filePath;
        }
        if (isFileSeparator(filePath.charAt(len - 1)))
        {
            // 以分隔符结尾的去掉结尾分隔符
            len--;
        }

        int begin = 0;
        char c;
        for (int i = len - 1; i > -1; i--)
        {
            c = filePath.charAt(i);
            if (isFileSeparator(c))
            {
                // 查找最后一个路径分隔符（/或者\）
                begin = i + 1;
                break;
            }
        }

        return filePath.substring(begin, len);
    }

    /**
     * 是否为Windows或者Linux（Unix）文件分隔符<br>
     * Windows平台下分隔符为\，Linux（Unix）为/
     *
     * @param c 字符
     * @return 是否为Windows或者Linux（Unix）文件分隔符
     */
    public static boolean isFileSeparator(char c)
    {
        return SLASH == c || BACKSLASH == c;
    }

    /**
     * 下载文件名重新编码
     *
     * @param response 响应对象
     * @param realFileName 真实文件名
     * @return
     */
    public static void setAttachmentResponseHeader(HttpServletResponse response, String realFileName) throws UnsupportedEncodingException
    {
        String percentEncodedFileName = percentEncode(realFileName);

        StringBuilder contentDispositionValue = new StringBuilder();
        contentDispositionValue.append("attachment; filename=")
                .append(percentEncodedFileName)
                .append(";")
                .append("filename*=")
                .append("utf-8''")
                .append(percentEncodedFileName);

        response.setHeader("Content-disposition", contentDispositionValue.toString());
        response.setHeader("download-filename", percentEncodedFileName);
    }

    /**
     * 百分号编码工具方法
     *
     * @param s 需要百分号编码的字符串
     * @return 百分号编码后的字符串
     */
    public static String percentEncode(String s) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode(s, StandardCharsets.UTF_8.toString());
        return encode.replaceAll("\\+", "%20");
    }

    /**
     * 全路径复制，若存在则跳过
     */
    public static void backupFile(String srcFolderStr, String destFolderStr, ThreadPoolExecutor threadPool) throws IOException {
        if (srcFolderStr == null) {
            throw new RuntimeException("srcFolder is null");
        }
        if (destFolderStr == null) {
            throw new RuntimeException("destFolder is null");
        }
        File srcFolder = new File(srcFolderStr);
        if (!srcFolder.exists()) {
            throw new RuntimeException("destFolder is not exists");
        }

        if (!srcFolder.isDirectory()) {
            throw new RuntimeException("destFolder is not exists");
        }
        File destFolder = new File(destFolderStr);
        if (!destFolder.exists()) {
            destFolder.mkdirs();
        }
        if (!destFolder.isDirectory()) {
            throw new RuntimeException("destFolder is not exists");
        }
        backupFile(srcFolder, destFolder,threadPool);
    }

    /**
     * 全路径复制，若存在则跳过
     */
    public static void backupFile(File srcFolder, File destFolder, ThreadPoolExecutor threadPool) throws IOException {
        if (srcFolder == null) {
            throw new RuntimeException("srcFolder is null");
        }
        if (destFolder == null) {
            throw new RuntimeException("destFolder is null");
        }
        if (srcFolder.isFile()) {
            if (destFolder.exists()) {
                return;
            }
            org.apache.commons.io.FileUtils.copyFile(srcFolder, destFolder);
        }
        if (srcFolder.isDirectory()) {
            if (!destFolder.exists()) {
                destFolder.mkdirs();
            }
            File[] files = srcFolder.listFiles();
            for (File file : files) {
                //计算目标路径
                threadPool.execute(() -> {
                    String absolutePath = srcFolder.getAbsolutePath();
                    String replace = absolutePath.replace(srcFolder.getAbsolutePath(), destFolder.getAbsolutePath());
                    replace = replace + "\\" + file.getName();
                    try {
                        backupFile(file, new File(replace),threadPool);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });
            }
        }
    }
}
