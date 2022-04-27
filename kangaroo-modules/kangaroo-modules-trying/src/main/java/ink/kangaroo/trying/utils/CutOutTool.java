package ink.kangaroo.trying.utils;


import cn.hutool.core.util.IdUtil;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.bytedeco.javacpp.Loader;

import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Program: csdn @ClassName: CutOutTool @Author: 剑客阿良_ALiang @Date: 2022-01-23 18:27 @Description:
 * 裁剪工具 @Version: V1.0
 */
public class CutOutTool {
    private static String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
    private static String ffprobe = Loader.load(org.bytedeco.ffmpeg.ffprobe.class);

    /**
     * * 视频剪辑
     *
     * @param videoPath
     * @param outputDir
     * @return
     * @throws Exception
     */
    public static String clipVideo(
            String videoPath,
            String outputDir
    ) {

        return null;
    }

    /**
     * * 视频剪辑
     *
     * @param videoPath
     * @return
     * @throws Exception
     */
    public static String getVideoInfo(
            String videoPath
    ) {
        return runFfprobeCommand(true, ffprobe,"-v", "error" ,"-show_streams", "-print_format json" ,videoPath );
    }

    /**
     * 视频转码为默认编码 h264
     *
     * @param videoPath
     * @param outputDir
     * @return
     * @throws Exception
     */
    public static String castEcodeVideo(
            String videoPath,
            String outputDir
    ) throws Exception {
        return castEcodeVideo(videoPath, outputDir, "h264");
    }

    /**
     * 视频转码为默认编码 h264
     *
     * @param videoPath
     * @param outputDir
     * @return
     */
    public static String castEcodeVideo(
            File videoPath,
            File outputDir
    ) {
        List<String> paths = Splitter.on(".").splitToList(videoPath.getAbsolutePath());
        String ext = paths.get(paths.size() - 1);
//        if (!Arrays.asList("mp4", "avi", "flv").contains(ext.toLowerCase())) {
//            throw new Exception("format error");
//        }
        String resultPath =
                Joiner.on(File.separator).join(Arrays.asList(outputDir.getAbsolutePath(), videoPath.getName().replace(".mkv", ".mp4")));
        StringBuilder result = new StringBuilder();
        try {
            ProcessBuilder builder =
                    new ProcessBuilder(
                            ffmpeg,
                            "-i",
                            videoPath.getAbsolutePath(),
                            "-vcodec",
                            "h264",
                            resultPath);
            Process subprocess = builder.start();
            InputStream in = subprocess.getInputStream();
            byte[] re = new byte[1024];
            while (in.read(re) != -1) {
                System.out.println(new String(re));
                result.append(new String(re));
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * * 视频转码
     *
     * @param videoPath
     * @param outputDir
     * @return
     * @throws Exception
     */
    public static String castEcodeVideo(
            String videoPath,
            String outputDir,
            String vcodec
    ) throws Exception {
        List<String> paths = Splitter.on(".").splitToList(videoPath);
        String ext = paths.get(paths.size() - 1);
//        if (!Arrays.asList("mp4", "avi", "flv").contains(ext.toLowerCase())) {
//            throw new Exception("format error");
//        }
        String resultPath =
                Joiner.on(File.separator).join(Arrays.asList(outputDir, IdUtil.simpleUUID() + "." + "mp4"));
        return runCommand(true, ffmpeg,
                "-i",
                videoPath,
                "-vcodec",
                vcodec,
                resultPath);
    }

    /**
     * 视频裁剪
     *
     * @param videoPath 视频地址
     * @param outputDir 临时目录
     * @param startX    裁剪起始x坐标
     * @param startY    裁剪起始y坐标
     * @param weight    裁剪宽度
     * @param height    裁剪高度
     * @throws Exception 异常
     */
    public static String cutOutVideo(
            String videoPath,
            String outputDir,
            Integer startX,
            Integer startY,
            Integer weight,
            Integer height)
            throws Exception {
        List<String> paths = Splitter.on(".").splitToList(videoPath);
        String ext = paths.get(paths.size() - 1);
        if (!Arrays.asList("mp4", "avi", "flv").contains(ext.toLowerCase())) {
            throw new Exception("format error");
        }
        String resultPath =
                Joiner.on(File.separator).join(Arrays.asList(outputDir, IdUtil.simpleUUID() + "." + ext));
        ProcessBuilder builder =
                new ProcessBuilder(
                        ffmpeg,
                        "-i",
                        videoPath,
                        "-vf",
                        MessageFormat.format(
                                "crop={0}:{1}:{2}:{3}",
                                String.valueOf(weight),
                                String.valueOf(height),
                                String.valueOf(startX),
                                String.valueOf(startY)),
                        "-b",
                        "2000k",
                        "-y",
                        "-threads",
                        "5",
                        "-preset",
                        "ultrafast",
                        "-strict",
                        "-2",
                        resultPath);
        builder.inheritIO().start().waitFor();
        return resultPath;
    }

    private static String runFfprobeCommand(boolean print, String... args) {
        StringBuilder result = new StringBuilder();
        try {

//                if (e != null) {
//                    if (e instanceof String) {
//                        return (String) e;
//                    }
//                    return null;
//                } else {
//                    return null;
//                }
            List<String> collect = Stream.of(ffprobe, args).map(e -> (String) e).collect(Collectors.toList());
            ProcessBuilder builder =
                    new ProcessBuilder(collect);
            Process subprocess = builder.start();
            if (print) {
                InputStream in = subprocess.getInputStream();
                byte[] re = new byte[1024];
                while (in.read(re) != -1) {
                    System.out.println(new String(re));
                    result.append(new String(re));
                }
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    //    private static String runCommand(String print, String... args) {
//        return runCommand("","");
//    }
    private static String runCommand(boolean print, String... args) {
        StringBuilder result = new StringBuilder();
        try {
            ProcessBuilder builder =
                    new ProcessBuilder(args);
            Process subprocess = builder.start();
            if (print) {
                InputStream in = subprocess.getInputStream();
                byte[] re = new byte[1024];
                while (in.read(re) != -1) {
                    System.out.println(new String(re));
                    result.append(new String(re));
                }
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(getVideoInfo("E:\\video\\16.武林外传第61回至第64回.mp4"));
        File srcFile = new File("E:\\迅雷下载\\武林外传.全81集含花絮.My.Own.Swordsman.2006.1080P.HEVC.H265.AAC.DHTCLUB");
        File targetFile = new File("E:\\video");
        File[] files = srcFile.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains(".mkv");
            }
        });
        for (File file : files) {
            castEcodeVideo(file, targetFile);
        }
//        System.out.println(
//                castEcodeVideo("E:\\tmp\\1-4.mkv", "E:\\tmp\\"));


    }
}