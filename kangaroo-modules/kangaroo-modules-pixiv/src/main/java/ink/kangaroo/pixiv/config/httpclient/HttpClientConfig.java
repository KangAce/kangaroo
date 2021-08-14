package ink.kangaroo.pixiv.config.httpclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.*;
import java.net.http.HttpClient;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.concurrent.ExecutorService;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/11 1:21
 */

@Configuration
public class HttpClientConfig {
    @Autowired
    private DefaultCookieManager defauleCookieManager;

    @Bean
    public TrustManager[] trustAllCertificates() {
        return new TrustManager[]{new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null; // Not relevant.
            }

            @Override
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) {
                // TODO Auto-generated method stub
            }

            @Override
            public void checkServerTrusted(X509Certificate[] arg0, String arg1) {
                // TODO Auto-generated method stub
            }
        }};
    }

    @Bean
    @Primary
    @Autowired
    public HttpClient httpClientWithOutProxy(TrustManager[] trustAllCertificates, ExecutorService httpclientExecutorService) throws NoSuchAlgorithmException, KeyManagementException {
        SSLParameters sslParams = new SSLParameters();
        sslParams.setEndpointIdentificationAlgorithm("");
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCertificates, new SecureRandom());
        //使用tribe的CookieManager，并且接受所有第三方Cookie
        defauleCookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        return HttpClient.newBuilder().cookieHandler(defauleCookieManager)
                .version(HttpClient.Version.HTTP_1_1)
                .sslParameters(sslParams)
                .sslContext(sc)
                .connectTimeout(Duration.ofSeconds(30))
                //          .proxy(ProxySelector.of(new InetSocketAddress("127.0.0.1", 8888)))
                .executor(httpclientExecutorService)
                .followRedirects(HttpClient.Redirect.NEVER)
                .build();
    }

    @Autowired
    @Bean(name = "httpClientWithProxy")
    public HttpClient httpClient(TrustManager[] trustAllCertificates, ExecutorService httpclientExecutorService) throws NoSuchAlgorithmException, KeyManagementException {
        SSLParameters sslParams = new SSLParameters();
        sslParams.setEndpointIdentificationAlgorithm("");
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCertificates, new SecureRandom());
        //使用tribe的CookieManager，并且接受所有第三方Cookie
        defauleCookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieStore cookieStore = defauleCookieManager.getCookieStore();

//        String pixiv_cookies = (String) optionService.getByKeyOfNonNull("pixiv_cookies");
        String pixiv_cookies ="63063042_Dj8QtEy9tAS3shSsDgCuWFTbUsPSC7uB";
//        cookieStore.add(URI.create("pixiv.net"),new HttpCookie("PHPSESSID",pixiv_cookies));
        cookieStore.add(URI.create("https://www.pixiv.net"),new HttpCookie("PHPSESSID",pixiv_cookies));
        if (System.getProperty("os.name").equals("Windows 10")) {
            return HttpClient.newBuilder()
                    .cookieHandler(defauleCookieManager)
                    .version(HttpClient.Version.HTTP_1_1)
                    .sslParameters(sslParams)
                    .sslContext(sc)
                    .proxy(ProxySelector.of(new InetSocketAddress("127.0.0.1", 1080)))
                    .executor(httpclientExecutorService)
                    .followRedirects(HttpClient.Redirect.NEVER)
                    .build();
        }
        return HttpClient.newBuilder()
                .cookieHandler(defauleCookieManager)
                .version(HttpClient.Version.HTTP_1_1)
                .sslParameters(sslParams)
                .sslContext(sc)
//                .proxy(ProxySelector.of(new InetSocketAddress("127.0.0.1", 1080)))
                .executor(httpclientExecutorService)
                .followRedirects(HttpClient.Redirect.NEVER)
                .build();
    }

}
