package ink.kangaroo.gateway.security.service;

import com.alibaba.fastjson.JSONObject;
import ink.kangaroo.common.core.domain.R;
import ink.kangaroo.common.core.utils.VerifyImageUtil;
import ink.kangaroo.common.redis.enums.RedisKeyEnum;
import ink.kangaroo.common.redis.service.RedisService;
import ink.kangaroo.gateway.security.MobileCodeType;
import ink.kangaroo.gateway.security.SliderVerificationCodeType;
import ink.kangaroo.gateway.security.domain.SliderVerificationVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/7/19 13:33
 */
@Slf4j
@Service
public class ValidateServiceImpl implements ValidateService {
    @Resource
    RedisService redisService;

//    @Resource
//    MqConfig mqConfig;
//    @Resource
//    ProducerBean producerBean;

    @Override
    public R checkSlider(String uuid, int X, int Y, SliderVerificationCodeType type) {
        JSONObject message = new JSONObject();
        message.put("code", 2);
        message.put("massage", "验证不通过，请重试！");
        if (null == uuid || uuid.trim().length() < 1) {
            message.put("code", 0);
            message.put("massage", "请求参数错误:token为空！");
        }
        Map<String, Object> tokenObj = redisService.getCacheMap(RedisKeyEnum.SLIDER_VALIDATE_TOKEN.wrap2Key(type.getType().toString(), uuid));
        redisService.deleteObject(RedisKeyEnum.SLIDER_VALIDATE_TOKEN.wrap2Key(String.valueOf(type.getType()), uuid));

        if (null == tokenObj) {
            message.put("code", -1);
            message.put("massage", "验证码超期，请重新请求！");
        } else {
            int sX = (Integer) tokenObj.get("X");
            int sY = (Integer) tokenObj.get("Y");
            if (sY != Y) {
                message.put("code", 0);
                message.put("massage", "请求参数错误:位置信息不正确！");
            } else {
                if (Math.abs(sX - X) <= 2) {
                    message.put("code", 1);
                    message.put("massage", "验证通过！");
                } else {
                    message.put("code", 2);
                    message.put("massage", "验证不通过，请重试！");
                }
            }
        }
        if (message.getInteger("code") == 1) {
            return R.ok(true);
        } else {
            return R.ok(false);
        }
    }

//    @Override
//    public R getMobileCode(String phone, MobileCodeType type) {
//        return getMobileCode(phone, 10 * 60 * 1000L, type);
//    }
//
//    @Override
//    public R<String> getMobileCode(String phone, Long time, MobileCodeType type) {
//        Message message = new Message();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("phoneNum", phone);
//        int random = (int) (Math.random() * 999999);
//        String code = String.format("%06d", random);
//        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//        jsonObject.put("uuid", uuid);
//        jsonObject.put("code", code);
//        jsonObject.put("type", type.getType());
//        jsonObject.put("times", 0);
//        JSONObject codeObject = new JSONObject();
//        codeObject.put("code", code);
//        codeObject.put("times", 0);
//        codeObject.put("overtime", System.currentTimeMillis() + time);
//        log.info("getMobileCode:key:{},value:{}", RedisKeyEnum.SMS_VALIDATE_TOKEN.wrap3Key(phone, String.valueOf(type.getType()), uuid), codeObject.toJSONString());
//        redisService.setCacheObject(RedisKeyEnum.SMS_VALIDATE_TOKEN.wrap3Key(phone, String.valueOf(type.getType()), uuid), codeObject.toJSONString(), time, TimeUnit.MILLISECONDS);
//        message.setKey(uuid);
//        message.setTag(mqConfig.getTag());
//        message.setTopic(mqConfig.getTopic());
//        message.setBody(jsonObject.toJSONString().getBytes(StandardCharsets.UTF_8));
//        producerBean.send(message);
//        log.debug("uuid = {},code = {}, type = {}", uuid, code, type);
//
//        return R.ok(uuid);
//    }

    @Override
    public R<SliderVerificationVo> getSliderVerificationCode(SliderVerificationCodeType type) {
        return this.getSliderVerificationCode(type, 60 * 10 * 1000L);
    }

    @Override
    public R<SliderVerificationVo> getSliderVerificationCode(SliderVerificationCodeType type, Long timeout) {
        SliderVerificationVo object = new SliderVerificationVo();
        List<byte[]> imgList = redisService.getCacheObject(RedisKeyEnum.KEY_VALIDATE_IMG.keyName());
        List<byte[]> tpllist = redisService.getCacheObject(RedisKeyEnum.KEY_VALIDATE_TPL.keyName());
        if (null == imgList || imgList.size() < 1 || tpllist == null || tpllist.size() < 1) {
            imgList = new ArrayList<byte[]>();
            tpllist = new ArrayList<byte[]>();
            try {
                initValidateResources(imgList, tpllist);
            } catch (IOException e) {
                e.printStackTrace();
                return R.fail();
            }

            redisService.setCacheObject(RedisKeyEnum.KEY_VALIDATE_IMG.keyName(), imgList);
            redisService.setCacheObject(RedisKeyEnum.KEY_VALIDATE_TPL.keyName(), tpllist);

        }
        byte[] targetIS = null;
        byte[] templateIS = null;
        Random ra = new Random();
        if (null != imgList) {
            int rd = ra.nextInt(imgList.size());
            targetIS = imgList.get(rd);
        }
        if (null != tpllist) {
            int rd = ra.nextInt(tpllist.size());
            templateIS = tpllist.get(rd);
        }
        Map<String, Object> pictureMap = null;
        try {
            pictureMap = VerifyImageUtil.pictureTemplatesCut(templateIS, targetIS, "png", "jpg");
            String newImage = Base64Utils.encodeToString((byte[]) pictureMap.get("newImage"));
            String sourceImage = Base64Utils.encodeToString((byte[]) pictureMap.get("oriCopyImage"));
            int X = (int) pictureMap.get("X");
            int Y = (int) pictureMap.get("Y");
            object.setNewImage( newImage);
            object.setOriCopyImage(sourceImage);
            //object.put("X", X);
            //object.put("X", X);
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            object.setUuid(uuid);

            Map<String, Object> tokenObj = new HashMap<String, Object>();
            tokenObj.put("uuid", uuid);
            tokenObj.put("X", X);
            tokenObj.put("Y", Y);
            //uuid 保存2分钟
            redisService.setCacheObject(RedisKeyEnum.SLIDER_VALIDATE_TOKEN.wrap2Key(type.toString(), uuid), tokenObj, 120000L, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("", e);
        }
        return R.ok(object);
    }

    /**
     * 初始化验证图形生成资源
     *
     * @param imgList
     * @param tpllist
     */
    private void initValidateResources(List<byte[]> imgList, List<byte[]> tpllist) throws IOException {
        /*加载验证原图*/
        String target = URLDecoder.decode(ValidateController.class.getClassLoader().getResource("static/image/validate/targets").getPath(), "UTF-8");
        byte[] targetIS = null;
        byte[] templateIS = null;
        if (target.indexOf("!/") != -1) {//jar包
            String jarPath = "jar:" + target;
            log.debug(jarPath);
            URL jarURL = new URL(jarPath);
            JarURLConnection jarCon = (JarURLConnection) jarURL.openConnection();
            JarFile jarFile = jarCon.getJarFile();
            Enumeration<JarEntry> jarEntrys = jarFile.entries();
            while (jarEntrys.hasMoreElements()) {
                JarEntry entry = jarEntrys.nextElement();
                String name = entry.getName();
                if (name.startsWith("static/image/validate/targets") && !name.equals("static/image/validate/targets/") && (name.endsWith(".jpg") || name.endsWith(".png"))) {
                    log.debug("targets=" + name);
                    InputStream isTemplates = jarFile.getInputStream(entry);
                    targetIS = IOUtils.toByteArray(jarFile.getInputStream(entry));
                    imgList.add(targetIS);

                } else if (name.startsWith("static/image/validate/templates") && !name.equals("static/image/validate/templates/") && (name.endsWith(".jpg") || name.endsWith(".png"))) {
                    log.debug("templates=" + name);
                    InputStream isTemplates = jarFile.getInputStream(entry);
                    templateIS = IOUtils.toByteArray(jarFile.getInputStream(entry));
                    tpllist.add(templateIS);
                }
            }
        } else {
            File targetBaseFile = new File(target);
            if (null != targetBaseFile) {
                File[] fs = targetBaseFile.listFiles();
//                Random ra = new Random();
//                if (null != fs && fs.length > 0) {
//                    int random = ra.nextInt(fs.length);
//                    targetIS = IOUtils.toByteArray(new FileInputStream(fs[random]));
//                }
                for (File f : fs) {
                    targetIS = IOUtils.toByteArray(new FileInputStream(f));
                    imgList.add(targetIS);
                }
            }
            /*加载切图模板*/
            String template = URLDecoder.decode(ValidateController.class.getClassLoader().getResource("static/image/validate/templates").getFile(), "UTF-8");
            File templateBaseFile = new File(template);
            if (null != templateBaseFile) {
                File[] fs = templateBaseFile.listFiles();
//                Random ra = new Random();
//                if (null != fs && fs.length > 0) {
//                    int random = ra.nextInt(fs.length);
//                    templateIS = IOUtils.toByteArray(new FileInputStream(fs[random]));
//                }
                for (File f : fs) {
                    templateIS = IOUtils.toByteArray(new FileInputStream(f));
                    tpllist.add(templateIS);
                }
            }
        }
        log.info("initValidateResources:template size:" + tpllist.size() + "target size:" + imgList.size());
    }

//    @Override
//    public R<String> checkMobileCode(String phone, String uuid, String code, MobileCodeType type) {
//        R<String> cacheMobileCode = this.getCacheMobileCode(phone, uuid, type);
//        if (cacheMobileCode.getCode() != R.SUCCESS) {
//            return R.fail(11011, "验证码无效:" + cacheMobileCode.getMsg());
//        }
//        String cacheCode = cacheMobileCode.getData();
//        if (StringUtils.isEmpty(cacheCode)) {
//            log.info("获取缓存验证码失败：phone：{}，uuid：{}，type：{}", phone, uuid, type.getType());
//            return R.fail(11012, "验证码错误");
//        }
//        if (!cacheCode.equals(code)) {
//            log.info("验证码不相同：phone：{}，uuid：{}，type：{}", phone, uuid, type.getType());
//            return R.fail(11012, "验证码失败");
//        }
//
//        return R.ok("验证成功");
//    }

//    @Override
//    public R<String> getCacheMobileCode(String phone, String uuid, MobileCodeType type) {
//        String key = RedisKeyEnum.SMS_VALIDATE_TOKEN.wrap3Key(phone, String.valueOf(type.getType()), uuid);
//        log.info("getCacheMobileCode:key:{}", key);
//        String cacheCodeJson = redisService.getCacheObject(key);
//        log.info("getCacheMobileCode:value:{}", cacheCodeJson);
//        JSONObject jsonObject = JSON.parseObject(cacheCodeJson);
//        if (jsonObject == null) {
//            return R.fail("验证码失效,请重新获取");
//        }
//        if (System.currentTimeMillis() > jsonObject.getLong("overtime")) {
//            return R.fail("验证码失效,请重新获取!");
//        }
//        if (jsonObject.getInteger("times") > 2) {
//            redisService.deleteObject(key);
//        }
//        jsonObject.put("times", jsonObject.getInteger("times") + 1);
//        redisService.setCacheObject(RedisKeyEnum.SMS_VALIDATE_TOKEN.wrap3Key(phone, String.valueOf(type.getType()), uuid), jsonObject.toJSONString(), 10L, TimeUnit.MINUTES);
//        return R.ok(jsonObject.getString("code"));
//
//        //需要可以使用三次
//    }

}
