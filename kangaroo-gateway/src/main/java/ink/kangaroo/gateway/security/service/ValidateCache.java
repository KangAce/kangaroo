package ink.kangaroo.gateway.security.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Auther herb
 * @Description 本地缓存:用户图形验证码资源
 * @Date: create in ${Time} ${Date}
 * @Modified By: herb
 */
@Slf4j
public class ValidateCache {

    private static LoadingCache<String,List<byte[]>> cache = CacheBuilder.newBuilder()
            .maximumSize(2)
            .expireAfterWrite(365, TimeUnit.DAYS)
            .build(createCacheLoader());

    private static CacheLoader<String, List<byte[]>> createCacheLoader() {
        return new CacheLoader<String, List<byte[]>>(){
            @Override
            public List<byte[]> load(String key) throws Exception {
                return null;
            }
        };
    }

    public static void set(String key,List<byte[]> value){
        cache.put(key,value);
    }

    public static List<byte[]> get(String key){
        List<byte[]> value = null;
        try {
            value = cache.get(key);
        } catch (Exception e) {
            log.info("ValidateCache 缓存未查询到图片或模板!");
            value = null;
        }
        return value;
    }
    public static long size(){
        return cache.size();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        List<byte[]> value = new ArrayList<>();
        value.add("ok".getBytes("utf-8"));
        ValidateCache.set("121", value);
        ValidateCache.set("111",value);
        ValidateCache.set("12",value);
        ValidateCache.set("113",value);
        System.out.println(cache.size());
        //注意缓存的最大容量
        List<byte[]> rv = ValidateCache.get("113");
        System.out.println(new String(rv.get(0),"utf-8"));
        System.out.println(ValidateCache.size());
        while (cache.size() > 0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<byte[]> rv1 = ValidateCache.get("113");
            System.out.println(new String(rv1.get(0),"utf-8"));
        }

    }
}
