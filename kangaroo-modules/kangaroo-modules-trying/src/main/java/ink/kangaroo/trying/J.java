package ink.kangaroo.trying;

import ink.kangaroo.common.core.utils.DecimalUtils;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.info.MultimediaInfo;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/28 16:59
 */
public class J {
    public static void main(String[] args) throws Exception {

        while (true){
            System.out.println(DecimalUtils.timeToStringByCount(4));
            Thread.sleep(1000);
        }
//        URL url = null;
//        try {
//            url = new URL("https://xjfs.xjcod.com/transport/creative/video/2021-04-30/YchijkmrfH.mp4");
//            MultimediaObject object = new MultimediaObject(url);
//            MultimediaInfo info = object.getInfo();
//            long duration = info.getDuration();
//            System.out.println("duration-->" + duration);
//            System.out.println("video size-->" + info.getVideo().getSize());
//            //码率
//            System.out.println("video bit rate-->" + info.getVideo().getBitRate());
//            //帧率
//            System.out.println("video bit rate-->" + info.getVideo().getFrameRate());
//            System.out.println("video decoder-->" + info.getVideo().getDecoder());
//            System.out.println("format-->" + info.getFormat());
//            System.out.println("metadata-->" + info.getMetadata());
//            System.out.println("audio-->" + info.getAudio());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        String releaseDate = "2019-11-10";
//        String date = "2019-11-11";
//        int i = daysBetween(releaseDate, date);
//        String s = plugDay(releaseDate, i);
//        System.out.println(i);
//        System.out.println(s);
    }

    /**
     * 字符串日期格式的计算
     *
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static String plugDay(String date, int i) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.DAY_OF_YEAR, 10);//日期加10天
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;
    }

}
