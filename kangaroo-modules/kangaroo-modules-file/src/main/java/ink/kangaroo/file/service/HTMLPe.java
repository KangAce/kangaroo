package ink.kangaroo.file.service;

import com.alibaba.nacos.shaded.com.google.protobuf.ServiceException;
import com.ruiyun.jvppeteer.core.Puppeteer;
import com.ruiyun.jvppeteer.core.browser.Browser;
import com.ruiyun.jvppeteer.core.page.Page;
import com.ruiyun.jvppeteer.exception.TimeoutException;
import com.ruiyun.jvppeteer.options.LaunchOptions;
import ink.kangaroo.common.core.utils.DateUtils;
import ink.kangaroo.common.core.utils.DecimalUtils;
import ink.kangaroo.file.M3U8Loader;
import ink.kangaroo.file.domain.M3U8;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/28 18:14
 */
public class HTMLPe {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(DateUtils.getTime().replace("-","").replace(" ","").replace(":",""));
        System.out.println(62^4);
        System.out.println(DecimalUtils._10_to_N(Long.parseLong(DateUtils.getTime().replace("-","").replace(" ","").replace(":","")),62));
        String s = DecimalUtils._10_to_N(System.currentTimeMillis() % 14776336, 62);
        System.out.println(DecimalUtils.N_to_10(s,62));
        System.out.println();
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
}
