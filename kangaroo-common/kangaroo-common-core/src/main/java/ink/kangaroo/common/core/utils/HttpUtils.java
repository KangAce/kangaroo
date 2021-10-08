package ink.kangaroo.common.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * http工具类
 */
public class HttpUtils {

    /**
     * get请求，请求头不填
     *
     * @param url    请求地址
     * @param querys query参数
     * @return String
     * @throws IOException 请求异常
     */
    public static String doGet(String url, Map<String, String> querys) throws Exception {
        return doGet(url, querys, null);
    }

    /**
     * get请求
     *
     * @param url     请求地址
     * @param headers 请求头 参数为空，则传null
     * @param querys  query参数 参数为空，则传null
     * @return String
     * @throws IOException 请求异常
     */
    public static String doGet(String url, Map<String, String> headers, Map<String, String> querys) throws Exception {
        HttpClient httpClient = wrapClient(url);
        HttpGet request = new HttpGet(buildUrl(url, querys));
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                request.addHeader(header.getKey(), header.getValue());
            }
        }
        return EntityUtils.toString(httpClient.execute(request).getEntity(), "utf-8");

    }

    /**
     * post form请求
     *
     * @param url 请求路径
     * @return 结果
     * @throws IOException 异常
     */
    public static String doPost(String url) throws Exception {
        return doPost(url, "");
    }

    /**
     * post form请求
     *
     * @param url   请求路径
     * @param bodys 请求体
     * @return 结果
     * @throws IOException 异常
     */
    public static String doPost(String url, Map<String, String> bodys) throws Exception {
        return doPost(url, null, bodys);
    }

    /**
     * post form请求
     *
     * @param url    请求路径
     * @param querys 请求参数
     * @param bodys  请求体
     * @return 结果
     * @throws IOException 异常
     */
    public static String doPost(String url, Map<String, String> querys, Map<String, String> bodys) throws Exception {
        return doPost(url, null, querys, bodys);
    }


    /**
     * post form请求
     *
     * @param url     请求路径
     * @param headers 请求头
     * @param querys  请求参数
     * @param bodys   请求体
     * @return 结果
     * @throws IOException 异常
     */
    public static String doPost(String url, Map<String, String> headers, Map<String, String> querys, Map<String, String> bodys) throws Exception {
        HttpClient httpClient = wrapClient(url);
        HttpPost request = new HttpPost(buildUrl(url, querys));
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                request.addHeader(header.getKey(), header.getValue());
            }
        }
        if (bodys != null) {
            List<NameValuePair> nameValuePairList = new ArrayList<>();
            for (String key : bodys.keySet()) {
                nameValuePairList.add(new BasicNameValuePair(key, bodys.get(key)));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");
            formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
            request.setEntity(formEntity);
        }
        return EntityUtils.toString(httpClient.execute(request).getEntity(), "utf-8");
    }

    /**
     * Post String 参数
     *
     * @param url  路径
     * @param body 请求体
     * @return 结果
     * @throws IOException 异常
     */
    public static String doPost(String url, String body) throws Exception {
        return doPost(url, null, body);
    }

    /**
     * Post String 参数
     *
     * @param url    路径
     * @param querys 查询参数
     * @param body   请求体
     * @return 结果
     * @throws IOException 异常
     */
    public static String doPost(String url, Map<String, String> querys, String body) throws Exception {
        return doPost(url, null, querys, body);
    }

    /**
     * Post String 参数
     *
     * @param url     路径
     * @param headers 请求头
     * @param querys  查询参数
     * @param body    请求体
     * @return 结果
     * @throws IOException 异常
     */
    public static String doPost(String url, Map<String, String> headers, Map<String, String> querys, String body) throws Exception {
        HttpClient httpClient = wrapClient(url);
        HttpPost request = new HttpPost(buildUrl(url, querys));
        if (headers != null) {
            for (Map.Entry<String, String> e : headers.entrySet()) {
                request.addHeader(e.getKey(), e.getValue());
            }
        }
        if (StringUtils.isNotBlank(body)) {
            request.setEntity(new StringEntity(body, "utf-8"));
        }
        return EntityUtils.toString(httpClient.execute(request).getEntity(), "utf-8");
    }

    /**
     * Post Json 参数
     *
     * @param url  路径
     * @param body 请求体
     * @return 结果
     * @throws IOException 异常
     */
    public static String doPostJson(String url, String body) throws Exception {
        return doPostJson(url, null, body);
    }

    /**
     * Post Json 参数
     *
     * @param url    路径
     * @param querys 查询参数
     * @param body   请求体
     * @return 结果
     * @throws IOException 异常
     */
    public static String doPostJson(String url, Map<String, String> querys, String body) throws Exception {
        return doPostJson(url, null, querys, body);
    }

    /**
     * Post Json 参数
     *
     * @param url     路径
     * @param headers 请求头
     * @param querys  查询参数
     * @param body    请求体
     * @return 结果
     * @throws IOException 异常
     */
    public static String doPostJson(String url, Map<String, String> headers, Map<String, String> querys, String body) throws Exception {
        HttpClient httpClient = wrapClient(url);
        HttpPost request = new HttpPost(buildUrl(url, querys));
        if (headers != null) {
            for (Map.Entry<String, String> e : headers.entrySet()) {
                request.addHeader(e.getKey(), e.getValue());
            }
        }
        if (StringUtils.isNotBlank(body)) {
            request.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
        }
        return EntityUtils.toString(httpClient.execute(request).getEntity(), "utf-8");
    }

    /**
     * Post byte数组参数
     *
     * @param url  url
     * @param body body
     * @return HttpResponse
     * @throws Exception Exception
     */
    public static String doPost(String url, byte[] body) throws Exception {
        return doPost(url, null, body);
    }

    /**
     * Post byte数组参数
     *
     * @param url    url
     * @param querys querys
     * @param body   body
     * @return HttpResponse
     * @throws Exception Exception
     */
    public static String doPost(String url, Map<String, String> querys, byte[] body) throws Exception {
        return doPost(url, null, querys, body);
    }

    /**
     * Post byte数组参数
     *
     * @param url     url
     * @param headers headers
     * @param querys  querys
     * @param body    body
     * @return HttpResponse
     * @throws Exception Exception
     */
    public static String doPost(String url, Map<String, String> headers, Map<String, String> querys, byte[] body) throws Exception {
        HttpClient httpClient = wrapClient(url);
        HttpPost request = new HttpPost(buildUrl(url, querys));
        if (headers != null) {
            for (Map.Entry<String, String> e : headers.entrySet()) {
                request.addHeader(e.getKey(), e.getValue());
            }
        }
        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }
        return EntityUtils.toString(httpClient.execute(request).getEntity(), "utf-8");
    }

    /**
     * Post stream 流参数
     *
     * @param url       url
     * @param body      body
     * @param fileParam 文件字段参数名
     * @param is        InputStream 输入流
     * @return String
     * @throws Exception Exception
     */
    public static String doPost(String url, Map<String, String> body, String fileParam, InputStream is)
            throws Exception {
        return doPost(url, null, body, fileParam, is);
    }

    /**
     * Post stream 流参数
     *
     * @param url       url
     * @param querys    查询参数
     * @param body      body
     * @param fileParam 文件字段参数名
     * @param is        InputStream 输入流
     * @return String
     * @throws Exception Exception
     */
    public static String doPost(String url, Map<String, String> querys,
                                Map<String, String> body, String fileParam, InputStream is)
            throws Exception {
        return doPost(url, null, querys, body, fileParam, is);
    }

    /**
     * Post stream 流参数
     *
     * @param url       url
     * @param headers   请求头
     * @param querys    查询参数
     * @param body      body
     * @param fileParam 文件字段参数名
     * @param is        InputStream 输入流
     * @return String
     * @throws Exception Exception
     */
    public static String doPost(String url, Map<String, String> headers, Map<String, String> querys,
                                Map<String, String> body, String fileParam, InputStream is)
            throws Exception {
        return doPost(url, headers, querys, body, fileParam, UUID.randomUUID().toString().replace("-", ""), is);
    }

    /**
     * Post stream 流参数
     *
     * @param url       url
     * @param fileParam 文件字段参数名
     * @param fileName  文件名
     * @param is        InputStream 输入流
     * @return String
     * @throws Exception Exception
     */
    public static String doPost(String url, String fileParam, String fileName, InputStream is) throws Exception {
        return doPost(url, null, fileParam, fileName, is);
    }

    /**
     * Post stream 流参数
     *
     * @param url       url
     * @param body      body
     * @param fileParam 文件字段参数名
     * @param fileName  文件名
     * @param is        InputStream 输入流
     * @return String
     * @throws Exception Exception
     */
    public static String doPost(String url, Map<String, String> body, String fileParam, String fileName, InputStream is)
            throws Exception {
        return doPost(url, null, body, fileParam, fileName, is);
    }

    /**
     * Post stream 流参数
     *
     * @param url       url
     * @param querys    查询参数
     * @param body      body
     * @param fileParam 文件字段参数名
     * @param fileName  文件名
     * @param is        InputStream 输入流
     * @return String
     * @throws Exception Exception
     */
    public static String doPost(String url, Map<String, String> querys,
                                Map<String, String> body, String fileParam, String fileName, InputStream is)
            throws Exception {
        return doPost(url, null, querys, body, fileParam, fileName, is);
    }

    /**
     * Post stream 流参数
     *
     * @param url       url
     * @param headers   请求头
     * @param querys    查询参数
     * @param body      body
     * @param fileParam 文件字段参数名
     * @param fileName  文件名
     * @param is        InputStream 输入流
     * @return String
     * @throws Exception Exception
     */
    public static String doPost(String url, Map<String, String> headers, Map<String, String> querys,
                                Map<String, String> body, String fileParam, String fileName, InputStream is)
            throws Exception {
        HttpClient httpClient = wrapClient(url);
        HttpPost request = new HttpPost(buildUrl(url, querys));
        if (headers != null) {
            for (Map.Entry<String, String> e : headers.entrySet()) {
                request.addHeader(e.getKey(), e.getValue());
            }
        }
        if (is != null || body != null) {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            if (is != null && StringUtils.isNotBlank(fileParam) && StringUtils.isNotBlank(fileName)) {
                builder.addBinaryBody(fileParam, is, ContentType.MULTIPART_FORM_DATA, fileName);
            }
            for (Map.Entry<String, String> bodyData : body.entrySet()) {
                builder.addTextBody(bodyData.getKey(), bodyData.getValue());
            }
            request.setEntity(builder.build());
        }
        return EntityUtils.toString(httpClient.execute(request).getEntity(), "utf-8");
    }

    /**
     * Put String
     *
     * @param url url
     * @return String
     * @throws Exception Exception
     */
    public static String doPut(String url)
            throws Exception {
        return doPut(url, "");
    }

    /**
     * Put String
     *
     * @param url  url
     * @param body body
     * @return String
     * @throws Exception Exception
     */
    public static String doPut(String url, String body)
            throws Exception {
        return doPut(url, null, body);
    }

    /**
     * Put String
     *
     * @param url    url
     * @param querys querys
     * @param body   body
     * @return String
     * @throws Exception Exception
     */
    public static String doPut(String url, Map<String, String> querys, String body)
            throws Exception {
        return doPut(url, null, querys, body);
    }

    /**
     * Put String
     *
     * @param url     url
     * @param headers headers
     * @param querys  querys
     * @param body    body
     * @return String
     * @throws Exception Exception
     */
    public static String doPut(String url, Map<String, String> headers, Map<String, String> querys, String body)
            throws Exception {
        HttpClient httpClient = wrapClient(url);
        HttpPut request = new HttpPut(buildUrl(url, querys));
        if (headers != null) {
            for (Map.Entry<String, String> e : headers.entrySet()) {
                request.addHeader(e.getKey(), e.getValue());
            }
        }
        if (StringUtils.isNotBlank(body)) {
            request.setEntity(new StringEntity(body, "utf-8"));
        }
        return EntityUtils.toString(httpClient.execute(request).getEntity(), "utf-8");
    }

    /**
     * Put byte数组
     *
     * @param url  url
     * @param body body
     * @return String
     * @throws Exception Exception
     */
    public static String doPut(String url, byte[] body)
            throws Exception {
        return doPut(url, null, body);
    }

    /**
     * Put byte数组
     *
     * @param url    url
     * @param querys querys
     * @param body   body
     * @return String
     * @throws Exception Exception
     */
    public static String doPut(String url, Map<String, String> querys, byte[] body)
            throws Exception {
        return doPut(url, null, querys, body);
    }

    /**
     * Put byte数组
     *
     * @param url     url
     * @param headers headers
     * @param querys  querys
     * @param body    body
     * @return String
     * @throws Exception Exception
     */
    public static String doPut(String url, Map<String, String> headers, Map<String, String> querys, byte[] body)
            throws Exception {
        HttpClient httpClient = wrapClient(url);
        HttpPut request = new HttpPut(buildUrl(url, querys));
        if (headers != null) {
            for (Map.Entry<String, String> e : headers.entrySet()) {
                request.addHeader(e.getKey(), e.getValue());
            }
        }
        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }
        return EntityUtils.toString(httpClient.execute(request).getEntity(), "utf-8");
    }

    /**
     * Delete
     *
     * @param url url
     * @return String
     * @throws Exception Exception
     */
    public static String doDelete(String url)
            throws Exception {
        return doDelete(url, null);
    }

    /**
     * Delete
     *
     * @param url    url
     * @param querys querys
     * @return String
     * @throws Exception Exception
     */
    public static String doDelete(String url, Map<String, String> querys)
            throws Exception {
        return doDelete(url, null, querys);
    }

    /**
     * Delete
     *
     * @param url     url
     * @param headers headers
     * @param querys  querys
     * @return String
     * @throws Exception Exception
     */
    public static String doDelete(String url, Map<String, String> headers, Map<String, String> querys)
            throws Exception {
        HttpClient httpClient = wrapClient(url);
        HttpDelete request = new HttpDelete(buildUrl(url, querys));
        if (headers != null) {
            for (Map.Entry<String, String> e : headers.entrySet()) {
                request.addHeader(e.getKey(), e.getValue());
            }
        }
        return EntityUtils.toString(httpClient.execute(request).getEntity(), "utf-8");
    }

    /**
     * 拼接url
     *
     * @param url    url
     * @param querys query参数
     * @return 拼接结果
     * @throws UnsupportedEncodingException query参数有不能转url编码，则会抛出异常
     */
    private static String buildUrl(String url, Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(url);
        if (Objects.nonNull(querys)) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (sbQuery.length() > 0) {
                    sbQuery.append("&");
                }
                if (StringUtils.isBlank(query.getKey()) && StringUtils.isNotBlank(query.getValue())) {
                    sbQuery.append(query.getValue());
                }
                if (StringUtils.isNotBlank(query.getKey())) {
                    sbQuery.append(query.getKey());
                    if (!StringUtils.isBlank(query.getValue())) {
                        sbQuery.append("=");
                        sbQuery.append(URLEncoder.encode(query.getValue(), "utf-8"));
                    }
                }
            }
            if (sbQuery.length() > 0) {
                sbUrl.append("?").append(sbQuery);
            }
        }
        return sbUrl.toString();
    }

    private static HttpClient wrapClient(String host) throws Exception {
        HttpClient httpClient;
        if (host.startsWith("https://")) {
            httpClient = sslClient();
        } else {
            httpClient = HttpClients.createDefault();
        }
        return httpClient;
    }

    private static HttpClient sslClient() throws Exception {
        return HttpClients
                .custom()
                .setSSLSocketFactory(
                        new SSLConnectionSocketFactory(
                                new SSLContextBuilder()
                                        //信任所有
                                        .loadTrustMaterial(null, (TrustStrategy) (chain, authType) -> true)
                                        .build()))
                .build();
    }

    public static void main(String[] args) throws Exception {
        String accessToken = "6208c0b67ebdbf5737d85b2f635f3e6c";
        String accountId = "16961308";
        String md5 = "9e0ad275033088e440175099b474167d";
        String fileName = "fileName";
        String urlPath = "https://api.e.qq.com/v1.3/videos/add";

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("access_token", accessToken);
        queryParams.put("timestamp", LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")) + "");
        queryParams.put("nonce", UUID.randomUUID().toString().replaceAll("-", ""));

        Map<String, String> params = new HashMap<>();
        params.put("account_id", accountId + "");
        params.put("signature", md5);
        params.put("description", fileName);

        URL url = new URL("https://xjfs.xjcod.com/transport/creative/video/2021-09-14/jNxn4cyQcj.mp4");
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        String response = doPost(urlPath, null, queryParams, params, "video_file", fileName, inputStream);
        System.out.println(response);

    }
}