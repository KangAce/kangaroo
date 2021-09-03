package ink.kangaroo.file.service;

import ink.kangaroo.common.core.utils.DateUtils;
import ink.kangaroo.common.core.utils.DecimalUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/28 18:14
 */
public class HTMLPe {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(DateUtils.getTime().replace("-", "").replace(" ", "").replace(":", ""));
        System.out.println(DecimalUtils._10_to_N(Long.parseLong(DateUtils.getTime().replace("-", "").replace(" ", "").replace(":", "")), 62));
        String s = DecimalUtils._10_to_N(System.currentTimeMillis() % 14776336, 62);
        System.out.println(s);
        System.out.println(DecimalUtils.N_to_10(s, 62));
        System.out.println();

        //定义指定视频ID
        extracted1(10,2);


//        System.setProperty("webdriver.chrome.driver", "D:\\Devsoftware\\chrome-win\\chromedriver.exe");//这一步必不可少
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--no-sandbox",
//                "--disable-setuid-sandbox",
//                "--disable-dev-shm-usage",
//                "window-size=1920x3000"/*指定浏览器分辨率*/,
//                "--disable-gpu"/*谷歌文档提到需要加上这个属性来规避bug*/,
//                "--hide-scrollbars"/*隐藏滚动条, 应对一些特殊页面*/,
//                "blink-settings=imagesEnabled=false"/*不加载图片, 提升速度*/,
//                "--headless"/*浏览器不提供可视化页面. linux下如果系统不支持可视化不加这条会启动失败*/);
////        options.setBinary("D:\\Devsoftware\\chrome-win\\chromedriver.exe");
//        options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
//        ChromeDriver driver = new ChromeDriver(options);
//        driver.get("https://www.baidu.com/");
//        String pageSource = driver.getPageSource();
//        System.out.println(pageSource);
//        driver.quit();

    }

    /**
     * @param creativeNum 定义需要创建的创意数量
     * @param vcount      定义视频数量
     */
    private static void extracted(int creativeNum, int vcount) {
        String specifyTheVideo = "55";

        List<String> resultList = new ArrayList<>();
        //视频idlist
        List<String> vids = new ArrayList<>();
        for (int i = 0; i < vcount; i++) {
            vids.add(String.valueOf(i));
        }
        int sumcount = creativeNum * vcount;//100

        int currCount = 0;
        int index = 0;
        for (int i = 0; i < sumcount&&index < vids.size(); i++) {
            currCount++;
            if (currCount == creativeNum) {
                currCount = 0;
                sumcount++;
                index++;
                resultList.add(specifyTheVideo);
                continue;
            }
            resultList.add(vids.get(index));
        }
        System.out.println(resultList.size());
        System.out.println(resultList);
    }
    /**
     * @param creativeNum 定义需要创建的创意数量
     * @param vcount      定义视频数量
     */
    private static void extracted1(int creativeNum, int vcount) {
        String specifyTheVideo = "55";

        List<String> resultList = new ArrayList<>();
        //视频idlist
        List<String> vids = new ArrayList<>();
        for (int i = 0; i < vcount; i++) {
            vids.add(String.valueOf(i));
        }
        List<String> tmpList = new ArrayList<>();
        for (String vid : vids) {
            for (int i = 0; i < creativeNum; i++) {
                tmpList.add(vid);
            }
        }
        int currnum = 0;/*
        for (String tmp : tmpList) {
            currnum++;
            if (currnum%creativeNum==0){
                resultList.add(specifyTheVideo);
                continue;
            }
            resultList.add(tmp);
        }*/
        for (int i = 0; i < tmpList.size(); i++) {
            currnum++;
            if (currnum%creativeNum==0){
                resultList.add(specifyTheVideo);
                i--;
                continue;
            }
            resultList.add(tmpList.get(i));

        }

        System.out.println(resultList.size());
        System.out.println(resultList);

        List<List<String>> res = new ArrayList<>();
        for (String s : resultList) {
            List<String> tmpList1 = new ArrayList<>();
            for (int i = 0; i <creativeNum; i++) {

            }
        }
    }
}
