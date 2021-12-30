package ink.kangaroo.douyin.controller;

import com.alibaba.fastjson.JSON;
import ink.kangaroo.common.core.domain.R;
import ink.kangaroo.common.core.utils.SecurityUtils;
import ink.kangaroo.system.api.RemoteFileService;
import ink.kangaroo.system.api.domain.SysFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Classname DyOpenService
 * @Description TODO
 * @Date 2021/11/29 5:39
 * @Created by Kangaroo
 */
@RestController
@RequestMapping("api/upload")
public class UploadController {

    @Autowired
    RemoteFileService remoteFileService;


    @RequestMapping("douyin")
    public String auth(MultipartFile file) {
        String username = SecurityUtils.getUsername();

//        R<SysFile> upload = remoteFileService.upload(file);
//        String s = JSON.toJSONString(upload);
//        String username = SecurityUtils.getUsername();
//        System.out.println(username);
//        System.out.println(s);
        return username;
    }
}
