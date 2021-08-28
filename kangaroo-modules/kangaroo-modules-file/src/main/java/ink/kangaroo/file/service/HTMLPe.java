package ink.kangaroo.file.service;

import com.alibaba.nacos.shaded.com.google.protobuf.ServiceException;
import com.ruiyun.jvppeteer.core.Puppeteer;
import com.ruiyun.jvppeteer.core.browser.Browser;
import com.ruiyun.jvppeteer.core.page.Page;
import com.ruiyun.jvppeteer.exception.TimeoutException;
import com.ruiyun.jvppeteer.options.LaunchOptions;
import ink.kangaroo.file.M3U8Loader;
import ink.kangaroo.file.domain.M3U8;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

//        System.setProperty("webdriver.chrome.driver","D:\\Devsoftware\\chrome-win\\chrome.exe");//这一步必不可少

//        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
//
//
//        ChromeOptions options = new ChromeOptions();
//        options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
//        ChromeDriver driver = new ChromeDriver(options);
//
//        //driver.quit();
//        String pageSource = driver.getPageSource();

        String path = null;
        path = new String("D:\\Devsoftware\\chrome-win\\chrome.exe".getBytes(), StandardCharsets.UTF_8);

//       String  path ="D:\\develop\\project\\toString\\chrome-win\\chrome.exe";
        ArrayList<String> arrayList = new ArrayList<>();
        new LaunchOptions();
        LaunchOptions launchOptions = new LaunchOptions();
        launchOptions.setArgs(arrayList);
        launchOptions.setTimeout(3000000);
        launchOptions.setHeadless(false);
        launchOptions.setExecutablePath(path);
        arrayList.add("--no-sandbox");
        arrayList.add("--disable-setuid-sandbox");
        Browser browser = Puppeteer.launch(launchOptions);
        Page page = browser.newPage();

//        System.out.println(pageSource);
        Document document = Jsoup.parse(page.content());
        for (Element div : document.body().select("div#header").select("div").select("div.tophead").select("div.wrap.mt20").select("div.box.movie_list").select("ul").select("li").select("a")) {
            System.out.println(div);
            String href = div.attr("href");
            String fileName = div.attr("alt");
            System.out.println();

//            page = browser.newPage();
            System.out.println(href);
            try {
                page.goTo(href);
            }catch (TimeoutException e){
//                Thread.sleep(10000);
                continue;
            } catch (InterruptedException e) {
                continue;
            }
            String content = page.content();
            Document parse = Jsoup.parse(content);
            Elements select = parse.body().select("div#header").select("div").select("div.tophead.wk").select("div.player").select("div").select("video#video.video-js").select("source");
            System.out.println("select(\"div#header\").select(\"div\").select(" + select);
            String src = select.attr("src");
            M3U8 load = null;
            try {
                load = M3U8Loader.load(src);
            } catch (Exception e) {
                continue;
            }
            load.setDir("D:\\m3u8JavaTest");
            load.setFileName(fileName);
            load.go();
            System.out.println("第一次循环");
        }
        try {
            page.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        System.out.println(stringResponseEntity.getBody());

//        System.out.println("=======================content=============="+content);


    }
}
