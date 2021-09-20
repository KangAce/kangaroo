package ink.kangaroo.wms.controller;

import ink.kangaroo.wms.service.ModuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ink.kangaroo.wms.model.Module;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 模块管理通用页面
 */

@Controller
@RequestMapping("/module")
public class ModuleController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ModuleService moduleService;

    /**
     * 模块列表action
     */
    @RequestMapping("/list/{pid}")
    public String list(@PathVariable Integer pid, Model model, HttpServletRequest request) {
        String searchName = request.getParameter("searchName");
        //获取所有子模块列表
        Module mod = new Module();
        mod.setParentId(pid);
        if (!StringUtils.isEmpty(searchName)) {
            mod.setName(searchName);
        }
        List<Module> modlist = moduleService.queryAll(mod);

        //获取父模块信息
        if (pid > 0) {
            Module parent = moduleService.detail(pid);
            model.addAttribute("parent", parent);
        }

        model.addAttribute("list", modlist);
        model.addAttribute("parentid", pid);
        return "/module/list";
    }

    /**
     * 模块编辑action
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        //获取详细信息
        Module mod = moduleService.detail(id);

        if (mod == null) {
            return "redirect:/module/list/0.do";
        } else {
            model.addAttribute("m", mod);
            model.addAttribute("pid", mod.getParentId());

            if (mod.getParentId() > 0) {
                Module p = moduleService.detail(mod.getParentId());
                model.addAttribute("p", p);
            }
            return "/module/input";
        }
    }

    /**
     * 模块新增action
     */
    @RequestMapping("/add/{pid}")
    public String add(@PathVariable Integer pid, Model model) {
        if (pid < 1) {
            model.addAttribute("pid", pid);
        } else {
            Module mod = moduleService.detail(pid);
            if (mod == null) {
                return "redirect:/module/list/0.do";
            } else {
                model.addAttribute("pid", pid);
                model.addAttribute("p", mod);
            }
        }

        return "/module/input";
    }

    /**
     * 模块数据更新action
     *
     * @return
     */
    @RequestMapping("/update/{type}/{id}")
    @ResponseBody
    public Boolean update(@PathVariable String type, @PathVariable Integer id, HttpServletRequest request) {
        Integer workId = 1;//Integer.parseInt(user.get("workId"));

        //日志记录保存
        logger.info("[{}@workId]:{} (Module) id[{}]", workId, type, id);

        if ("delete".equals(type)) {
            //删除数据
            Module mod = new Module();
            mod.setId(id);
            mod.setUserId(workId);
            return moduleService.delete(mod);
        } else if ("update".equals(type)) {
            //更新数据
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String js = request.getParameter("js");
            String css = request.getParameter("css");

            Module mod = new Module();
            mod.setUserId(workId);
            mod.setId(id);
            mod.setName(name);
            mod.setCss(css);
            mod.setDescription(description);
            mod.setJs(js);
            mod.setUpdated((int) (System.currentTimeMillis() * 0.001));

            return moduleService.update(mod);
        } else if ("add".equals(type)) {
            //父节点数据
            Byte level = 0;
            Integer rootId = 0;
            if (id > 0) {
                Module parent = moduleService.detail(id);
                if (parent == null) {
                    return false;
                } else {
                    level = parent.getLevel();
                    rootId = parent.getRootId();
                }
            }
            level++;

            //增加数据
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String js = request.getParameter("js");
            String css = request.getParameter("css");
            Integer now = (int) (System.currentTimeMillis() * 0.001);

            Module mod = new Module();
            mod.setUpdated(now);
            mod.setJs(js);
            mod.setDescription(description);
            mod.setCss(css);
            mod.setName(name);
            mod.setCreated(now);
            mod.setIsDeleted((byte) 0);
            mod.setIsShow(1);
            mod.setLevel(level);
            mod.setRootId(rootId);
            mod.setParentId(id);
            mod.setUserId(workId);

            return moduleService.insert(mod);
        } else {
            return false;
        }
    }

}
