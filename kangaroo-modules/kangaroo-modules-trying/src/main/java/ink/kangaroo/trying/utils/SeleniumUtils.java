//package ink.kangaroo.trying.utils;
//
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//public class SeleniumUtils {
//    private static ChromeDriver driver = null;
//
//    static {
//        System.setProperty("webdriver.chrome.driver", "D:\\chrome\\chrome-win\\chromedriver.exe");//这一步必不可少
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--no-sandbox",
//                "--disable-setuid-sandbox",
//                "--disable-dev-shm-usage",
//                "window-size=1920x3000"/*指定浏览器分辨率*/,
//                "--disable-gpu"/*谷歌文档提到需要加上这个属性来规避bug*/,
//                "--hide-scrollbars"/*隐藏滚动条, 应对一些特殊页面*/,
//                "blink-settings=imagesEnabled=false"/*不加载图片, 提升速度*/,
//                "--headless"/*浏览器不提供可视化页面. linux下如果系统不支持可视化不加这条会启动失败*/
//        );
//        options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
//
//        driver = new ChromeDriver(options);
//    }
//
//    public static void main(String[] args) {
//        System.out.println(get("https://www.baidu.com/"));
//        System.out.println(get("https://www.icourse163.org/learn/ZJU-1003315004?tid=1465129446#/learn/content?type=detail&id=1243861291&cid=1267432677"));
//
////        driver.
////        driver.switchTo().window(tabHandle);
//    }
//
//    private static String get(String s) {
//        getDriver().get(s);
//        return getDriver().getPageSource();
//    }
//
//    public static ChromeDriver getDriver() {
//        return driver;
//    }
//}
