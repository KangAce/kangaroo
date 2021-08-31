package ink.kangaroo.reminders.controller;

import ink.kangaroo.common.core.exception.base.BaseException;
import ink.kangaroo.common.core.utils.bean.BeanUtils;
import ink.kangaroo.common.core.web.controller.BaseController;
import ink.kangaroo.common.core.web.domain.AjaxResult;
import ink.kangaroo.reminders.model.entity.RemindersListEntity;
import ink.kangaroo.reminders.model.mapper.RemindersListMapper;
import ink.kangaroo.reminders.model.param.RemindersListParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/20 17:32
 */
@RestController
@RequestMapping("list")
public class ListController extends BaseController {

    @Autowired
    RemindersListMapper listMapper;

    @PostMapping
    public AjaxResult addList(@Valid RemindersListParam param) {
        RemindersListEntity entity = new RemindersListEntity();
        BeanUtils.copyBeanProp(entity, param);
        int insert = listMapper.insert(entity);
        if (insert > 0) {
            return AjaxResult.success().setData(entity.getId());
        } else {
            return AjaxResult.fail("");
        }
    }

    @PutMapping
    public AjaxResult updateList(@Valid RemindersListParam param) {
        if (param.getId() == null) {
            throw new BaseException("");
        }
        RemindersListEntity entity = new RemindersListEntity();
        BeanUtils.copyBeanProp(entity, param);
        int insert = listMapper.updateById(entity);
        if (insert > 0) {
            return AjaxResult.success().setData(entity.getId());
        } else {
            return AjaxResult.fail("");
        }
    }

    @DeleteMapping
    public AjaxResult deleteList(@NotNull Long id) {

        int insert = listMapper.deleteById(id);
        if (insert > 0) {
            return AjaxResult.success().setData(id);
        } else {
            return AjaxResult.fail("");
        }
    }

    @DeleteMapping
    public AjaxResult getList(@NotNull Long id) {

        RemindersListEntity byId = listMapper.findById(id);
        if (byId != null) {
            return AjaxResult.success().setData(byId);

        } else {
            return AjaxResult.fail("");
        }
    }


}
