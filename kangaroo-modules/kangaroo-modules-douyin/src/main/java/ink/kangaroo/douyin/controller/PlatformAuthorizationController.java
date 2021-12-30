package ink.kangaroo.douyin.controller;

import ink.kangaroo.common.core.constant.UserConstants;
import ink.kangaroo.common.core.utils.ExcelUtil;
import ink.kangaroo.common.core.utils.SecurityUtils;
import ink.kangaroo.common.core.utils.UrlUtil;
import ink.kangaroo.common.core.web.controller.BaseController;
import ink.kangaroo.common.core.web.domain.AjaxResult;
import ink.kangaroo.common.core.web.page.TableDataInfo;
import ink.kangaroo.common.log.annotation.Log;
import ink.kangaroo.common.log.enums.BusinessType;
import ink.kangaroo.common.security.annotation.PreAuthorize;
import ink.kangaroo.douyin.domain.PlatformAuthorization;
import ink.kangaroo.douyin.domain.PlatformType;
import ink.kangaroo.douyin.open.api.DyOpenService;
import ink.kangaroo.douyin.service.PlatformAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/platform/authorization")
public class PlatformAuthorizationController extends BaseController {

    @Autowired
    private PlatformAuthorizationService platformAuthorizationService;
    @Autowired
    private DyOpenService dyOpenService;

    @PreAuthorize(hasPermi = "system:dict:list")
    @GetMapping("/list")
    public TableDataInfo list(PlatformAuthorization platformAuthorization) {
        startPage();
        List<PlatformAuthorization> list = platformAuthorizationService.selectPlatformAuthorizationList(platformAuthorization);
        return getDataTable(list);
    }

    @PreAuthorize(hasPermi = "platform:auth:redirect")
    @GetMapping("/redirect")
    public TableDataInfo redirect(String code, String state) {


//        List<PlatformAuthorization> list = platformAuthorizationService.selectPlatformAuthorizationList(platformAuthorization);
        return getDataTable(Collections.emptyList());
    }

    @PreAuthorize(hasPermi = "platform:auth:redirect")
    @GetMapping("/auth")
    public void auth(HttpServletResponse httpServletResponse, Integer authType, String authName, String remark) {
        String douyinBaseUrl = "https://open.douyin.com/platform/oauth/connect/";
        String toutiaoBaseUrl = "https://open.snssdk.com/oauth/authorize/";
        String xiguaBaseUrl = "https://open-api.ixigua.com/oauth/connect";
        //client_key	 应用的唯一标识
        //response_type 写死为'code'即可
        //scope 应用授权作用域,多个授权作用域以英文逗号（,）分隔
        // //optionalScope 应用授权可选作用域,多个授权作用域以英文逗号（,）分隔，每一个授权作用域后需要加上一个是否默认勾选的参数，1为默认勾选，0为默认不勾选
        //redirect_uri 授权成功后的回调地址，必须以http/https开头。域名必须对应申请应用时填写的域名，如不清楚请联系应用申请人。
        //state 用于保持请求和回调的状态
        Map<String, String> params = new HashMap<>();
        params.put("client_key", "");
        params.put("response_type", "code");
        params.put("scope", "");
        params.put("optionalScope", "");
        params.put("redirect_uri", "https://www.imore.fun/douyin/platform/authorization/redirect");
        params.put("state", authName + ":" + authType);
        String get = UrlUtil.getParams("get", params);
        PlatformType byCode = PlatformType.getByCode(authType);
        String projectUrl = "/";
        switch (byCode) {
            case DOUYIN:
                projectUrl = douyinBaseUrl + get;
                break;
            case XIGUA:
                projectUrl = xiguaBaseUrl + get;
                break;
            case TOUTIAO:
                projectUrl = toutiaoBaseUrl + get;
                break;
            default:
                break;
        }
        httpServletResponse.setHeader("Location", projectUrl);

        httpServletResponse.setStatus(302);

    }

    @Log(title = "平台授权", businessType = BusinessType.EXPORT)
    @PreAuthorize(hasPermi = "system:dict:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PlatformAuthorization platformAuthorization) throws IOException {
        List<PlatformAuthorization> list = platformAuthorizationService.selectPlatformAuthorizationList(platformAuthorization);
        ExcelUtil<PlatformAuthorization> util = new ExcelUtil<PlatformAuthorization>(PlatformAuthorization.class);
        util.exportExcel(response, list, "平台授权");
    }

    /**
     * 查询平台授权详细
     */
    @PreAuthorize(hasPermi = "system:dict:query")
    @GetMapping(value = "/{authId}")
    public AjaxResult getInfo(@PathVariable Long authId) {
        return AjaxResult.createAjaxResult(platformAuthorizationService.selectPlatformAuthorizationById(authId));
    }

    /**
     * 新增字典类型
     */
    @PreAuthorize(hasPermi = "system:dict:add")
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody PlatformAuthorization dict) {
        if (UserConstants.NOT_UNIQUE.equals(platformAuthorizationService.checkDictTypeUnique(dict))) {
            return AjaxResult.fail("新增字典'" + dict.getAuthName() + "'失败，字典类型已存在");
        }
        dict.setCreateBy(SecurityUtils.getUsername());
        return toAjax(platformAuthorizationService.insertDictType(dict));
    }

    /**
     * 修改字典类型
     */
    @PreAuthorize(hasPermi = "system:dict:edit")
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody PlatformAuthorization dict) {
        if (UserConstants.NOT_UNIQUE.equals(platformAuthorizationService.checkDictTypeUnique(dict))) {
            return AjaxResult.fail("修改字典'" + dict.getAuthName() + "'失败，授权已存在");
        }
        dict.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(platformAuthorizationService.updatePlatformAuthorization(dict));
    }

    /**
     * 删除字典类型
     */
    @PreAuthorize(hasPermi = "system:dict:remove")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictIds}")
    public AjaxResult remove(@PathVariable Long[] dictIds) {
        platformAuthorizationService.deleteDictTypeByIds(dictIds);
        return success();
    }

    /**
     * 刷新字典缓存
     */
    @PreAuthorize(hasPermi = "system:dict:remove")
    @Log(title = "字典类型", businessType = BusinessType.CLEAN)
    @DeleteMapping("/refreshCache")
    public AjaxResult refreshCache() {
        platformAuthorizationService.resetAuthorizationache();
        return AjaxResult.success();
    }

    /**
     * 获取字典选择框列表
     */
    @GetMapping("/optionselect")
    public AjaxResult optionselect() {
        List<PlatformAuthorization> dictTypes = platformAuthorizationService.selectPlatformAuthorizationAll();
        return AjaxResult.createAjaxResult(dictTypes);
    }
}
