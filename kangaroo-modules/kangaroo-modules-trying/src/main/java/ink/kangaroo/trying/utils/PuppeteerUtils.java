package ink.kangaroo.trying.utils;

import com.ruiyun.jvppeteer.core.Puppeteer;
import com.ruiyun.jvppeteer.core.browser.Browser;
import com.ruiyun.jvppeteer.core.page.Page;
import com.ruiyun.jvppeteer.options.LaunchOptions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PuppeteerUtils {
    private static Browser browser = null;
    private Integer poolSize;
    private static Set<Page> pagePool = new HashSet<>();

    static {
        String path = null;
        path = new String("D:\\Devsoftware\\chrome-win\\chrome.exe".getBytes(), StandardCharsets.UTF_8);
        ArrayList<String> arrayList = new ArrayList<>();
        new LaunchOptions();
        LaunchOptions launchOptions = new LaunchOptions();
        launchOptions.setArgs(arrayList);
        launchOptions.setTimeout(30000);
        launchOptions.setHeadless(false);
        launchOptions.setExecutablePath(path);
        arrayList.add("--no-sandbox");
        arrayList.add("--disable-setuid-sandbox");
        arrayList.add("--disable-dev-shm-usage");
        arrayList.add("window-size=1920x1080");
        arrayList.add("--disable-gpu");
        arrayList.add("--hide-scrollbars");
        arrayList.add("blink-settings=imagesEnabled=false");
//        arrayList.add( "--headless");
        try {
            browser = Puppeteer.launch(launchOptions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Browser getBrowser() {
        return browser;
    }

    public static Page getPage() {
        browser.newPage();
        Page next = getPages().iterator().next();
//        pagePool.remove(next);

        return next;
    }

    public static List<Page> getPages() {
        return browser.pages();
    }

    public static void freePage(Page page) {
        pagePool.add(page);
    }
}
