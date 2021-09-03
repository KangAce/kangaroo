package ink.kangaroo.wms.controller;

import ink.kangaroo.wms.model.TemplateDef;
import ink.kangaroo.wms.service.ModuleService;
import ink.kangaroo.wms.service.TemplateDefService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ink.kangaroo.wms.model.Module;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/3 18:23
 */

@RestController
@RequestMapping("/def")
public class TemplateDefController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TemplateDefService templateDefService;

    @Resource
    private ModuleService moduleService;

    @RequestMapping("/list/{mId}")
    public String list(@PathVariable Integer mId, Model mod, HttpServletRequest request) {
        //获取模块节点信息
        Module m = moduleService.detail(mId);

        if (m == null || m.getIsDeleted().equals(1)) {
            return "redirect:/module/list/0.do";
        }

        //关键词查询
        String searchName = request.getParameter("searchName");

        //查询数据
        List<TemplateDef> list = templateDefService.queryModuleList(mId, searchName);
        mod.addAttribute("list", list);
        mod.addAttribute("module", m);
        mod.addAttribute("searchName", searchName);
        return "def/list";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        //获取详细信息
        TemplateDef def = templateDefService.detail(id);

        if (def == null) {
            return "redirect:/module/list/0.do";
        } else {
            model.addAttribute("def", def);
            model.addAttribute("mid", def.getModuleId());

            return "/def/input";
        }
    }

    @RequestMapping("/add/{mid}")
    public String add(@PathVariable Integer mid, Model model) {
        Module mod = moduleService.detail(mid);
        if (mod == null) {
            return "redirect:/module/list/0.do";
        } else {
            model.addAttribute("mid", mid);
        }

        return "/def/input";
    }

    @RequestMapping("/update/{type}/{id}")
    @ResponseBody
    public Boolean update(@PathVariable String type, @PathVariable Integer id, HttpServletRequest request) {
        Integer workId = 1;//Integer.parseInt(user.get("workId"));

        logger.info("[{}@workId]:{} (TemplateDef) id[{}]", workId, type, id);

        if ("delete".equals(type)) {
            TemplateDef def = new TemplateDef();
            def.setUserId(workId);
            def.setId(id);
            return templateDefService.delete(def);
        } else if ("update".equals(type)) {
            String name = request.getParameter("defName");
            String structure = request.getParameter("structure");
            String moduleId = request.getParameter("moduleId");

            TemplateDef def = new TemplateDef();
            def.setId(id);
            def.setName(name);
            def.setStructure(structure);
            def.setModuleId(Integer.parseInt(moduleId));
            def.setUserId(workId);
            return templateDefService.update(def);
        } else if ("add".equals(type)) {
            String name = request.getParameter("defName");
            String structure = request.getParameter("structure");
            Integer now = (int) (System.currentTimeMillis() * 0.001);

            TemplateDef def = new TemplateDef();
            def.setName(name);
            def.setStructure(structure);
            def.setModuleId(id);
            def.setUserId(workId);
            def.setCreated(now);
            def.setUpdated(now);
            def.setIsDeleted((byte) 0);
            return templateDefService.insert(def);
        } else {
            return false;
        }

    }
}
