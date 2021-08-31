package ink.kangaroo.reminders.controller;

import ink.kangaroo.common.core.exception.base.BaseException;
import ink.kangaroo.common.core.utils.bean.BeanUtils;
import ink.kangaroo.common.core.web.controller.BaseController;
import ink.kangaroo.common.core.web.domain.AjaxResult;
import ink.kangaroo.reminders.model.entity.RemindersGroupEntity;
import ink.kangaroo.reminders.model.mapper.RemindersGroupMapper;
import ink.kangaroo.reminders.model.param.RemindersGroupParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/20 17:32
 */
@RestController
@RequestMapping("group")
public class GroupController extends BaseController {

    @Autowired
    @Qualifier("remindersGroupMapper")
    RemindersGroupMapper listMapper;

    @PostMapping
    public AjaxResult addGroup(@Valid RemindersGroupParam param) {
        RemindersGroupEntity entity = new RemindersGroupEntity();
        BeanUtils.copyBeanProp(entity, param);
        int insert = listMapper.insert(entity);
        if (insert > 0) {
            return AjaxResult.success().setData(entity.getId());
        } else {
            return AjaxResult.fail("");
        }
    }

    @PutMapping
    public AjaxResult updateGroup(@Valid RemindersGroupParam param) {
        if (param.getId() == null) {
            throw new BaseException("");
        }
        RemindersGroupEntity entity = new RemindersGroupEntity();
        BeanUtils.copyBeanProp(entity, param);
        int insert = listMapper.updateById(entity);
        if (insert > 0) {
            return AjaxResult.success().setData(entity.getId());
        } else {
            return AjaxResult.fail("");
        }
    }

    @DeleteMapping
    public AjaxResult deleteGroup(@NotNull Long id) {

        int insert = listMapper.deleteById(id);
        if (insert > 0) {
            return AjaxResult.success().setData(id);
        } else {
            return AjaxResult.fail("");
        }
    }

    @DeleteMapping
    public AjaxResult getGroup(@NotNull Long id) {

        RemindersGroupEntity byId = listMapper.findById(id);
        if (byId != null) {
            return AjaxResult.success().setData(byId);

        } else {
            return AjaxResult.fail("");
        }
    }

}
