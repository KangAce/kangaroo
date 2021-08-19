package ink.kangaroo.gateway.security.service;

import com.alibaba.fastjson.JSONObject;
import ink.kangaroo.common.core.utils.VerifyImageUtil;
import ink.kangaroo.common.redis.enums.RedisKeyEnum;
import ink.kangaroo.common.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 1. Created by yansheng on 2014/6/29.
 */
@Slf4j
@Controller
@RequestMapping("/validate")
public class ValidateController {

    @Autowired
    RedisService redisService;
    /**
     * 生成验证码
     *
     * @return
     */
    @RequestMapping(value = "/init")
    @ResponseBody
    public JSONObject init() throws IOException {
        JSONObject object = new JSONObject();

        List<byte[]> imgList = redisService.getCacheObject(RedisKeyEnum.KEY_VALIDATE_IMG.keyName());
        List<byte[]> tpllist = redisService.getCacheObject(RedisKeyEnum.KEY_VALIDATE_TPL.keyName());
        if (null == imgList || imgList.size() < 1 || tpllist == null || tpllist.size() < 1) {
            imgList = new ArrayList<byte[]>();
            tpllist = new ArrayList<byte[]>();
            initValidateResources(imgList,tpllist);

            redisService.setCacheObject(RedisKeyEnum.KEY_VALIDATE_IMG.keyName(),imgList);
            redisService.setCacheObject(RedisKeyEnum.KEY_VALIDATE_TPL.keyName(),tpllist);

        }

        byte[] targetIS = null;
        byte[] templateIS = null;
        Random ra = new Random();
        if (null != imgList){
            int rd = ra.nextInt(imgList.size());
            targetIS = imgList.get(rd);
        }
        if (null != tpllist){
            int rd = ra.nextInt(tpllist.size());
            templateIS = tpllist.get(rd);
        }

        Map<String, Object> pictureMap = null;
        try {
            pictureMap = VerifyImageUtil.pictureTemplatesCut(templateIS,targetIS , "png", "jpg");
            String newImage = Base64Utils.encodeToString((byte[]) pictureMap.get("newImage"));
            String sourceImage = Base64Utils.encodeToString((byte[]) pictureMap.get("oriCopyImage"));
            int X = (int) pictureMap.get("X");
            int Y = (int) pictureMap.get("Y");
            object.put("newImage", newImage);
            object.put("sourceImage", sourceImage);
            //object.put("X", X);
            object.put("Y", Y);


            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            Map<String, Object> tokenObj = new HashMap<String, Object>();
            tokenObj.put("uuid", uuid);
            tokenObj.put("X", X);
            tokenObj.put("Y", Y);
            //uuid 保存2分钟
            redisService.setCacheObject(RedisKeyEnum.SLIDER_VALIDATE_TOKEN.wrap2Key("type",uuid), tokenObj, 120000L, TimeUnit.SECONDS);
            object.put("uuid", uuid);
        } catch (Exception e) {
            log.error("",e);
        }
        return object;
    }

    /**
     * 初始化验证图形生成资源
     * @param imgList
     * @param tpllist
     */
    private void initValidateResources(List<byte[]> imgList, List<byte[]> tpllist) throws IOException {
        /*加载验证原图*/
        String target = URLDecoder.decode(ValidateController.class.getClassLoader().getResource("static/image/validate/targets").getPath(),"UTF-8");
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

                } else if (name.startsWith("static/image/validate/templates") && !name.equals("static/image/validate/templates/")  && (name.endsWith(".jpg") || name.endsWith(".png"))) {
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
                for (File f : fs){
                    targetIS = IOUtils.toByteArray(new FileInputStream(f));
                    imgList.add(targetIS);
                }
            }
            /*加载切图模板*/
            String template = URLDecoder.decode(ValidateController.class.getClassLoader().getResource("static/image/validate/templates").getFile(),"UTF-8");
            File templateBaseFile = new File(template);
            if (null != templateBaseFile) {
                File[] fs = templateBaseFile.listFiles();
//                Random ra = new Random();
//                if (null != fs && fs.length > 0) {
//                    int random = ra.nextInt(fs.length);
//                    templateIS = IOUtils.toByteArray(new FileInputStream(fs[random]));
//                }
                for (File f : fs){
                    templateIS = IOUtils.toByteArray(new FileInputStream(f));
                    tpllist.add(templateIS);
                }
            }
        }
        log.info("initValidateResources:template size:" + tpllist.size() + "target size:" + imgList.size());
    }


    /**
     * 验证方法 (有验证码的方法提交，有时候也可以带上验证参数，做后端二次验证)
     *
     * @return
     */
    @RequestMapping(value = "check",method = RequestMethod.POST)
    @ResponseBody
    public boolean check(String token, int X, int Y) {
        JSONObject message = new JSONObject();
        message.put("code", 2);
        message.put("massage", "验证不通过，请重试！");
        if (null == token || token.trim().length() < 1) {
            message.put("code", 0);
            message.put("massage", "请求参数错误:token为空！");
        }
        Map<String, Object> tokenObj = redisService.getCacheMap(RedisKeyEnum.SLIDER_VALIDATE_TOKEN.wrap2Key("type",token));
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
        if (message.getInteger("code")==1) {
            return true;
        } else {
            return false;
        }
    }






    public static void main(String[] args) {
        String stream = ValidateController.class.getClassLoader().getResource("static/image/validate/").getFile();
        File validateBaseFile = new File(stream);
        File vf = null;
        if (null != validateBaseFile) {
            File[] fs = validateBaseFile.listFiles();
            Random ra = new Random();
            if (null != fs && fs.length > 0) {
                int random = ra.nextInt(fs.length);
                vf = fs[random];
            }
        }
        System.out.println(vf.getName());
    }
}
