package ink.kangaroo.reminders.controller;

import ink.kangaroo.common.core.exception.base.BaseException;
import ink.kangaroo.common.core.utils.bean.BeanUtils;
import ink.kangaroo.common.core.web.controller.BaseController;
import ink.kangaroo.common.core.web.domain.AjaxResult;
import ink.kangaroo.reminders.model.entity.RemindersReminderEntity;
import ink.kangaroo.reminders.model.mapper.RemindersReminderMapper;
import ink.kangaroo.reminders.model.param.RemindersReminderParam;
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
@RequestMapping("group")
public class ReminderController extends BaseController {

    @Autowired
    RemindersReminderMapper reminderMapper;

    @PostMapping
    public AjaxResult addReminder(@Valid RemindersReminderParam param) {
        RemindersReminderEntity entity = new RemindersReminderEntity();
        BeanUtils.copyBeanProp(entity, param);
        int insert = reminderMapper.insert(entity);
        if (insert > 0) {
            return AjaxResult.success().setData(entity.getId());
        } else {
            return AjaxResult.fail("");
        }
    }

    @PutMapping
    public AjaxResult updateReminder(@Valid RemindersReminderParam param) {
        if (param.getId() == null) {
            throw new BaseException("");
        }
        RemindersReminderEntity entity = new RemindersReminderEntity();
        BeanUtils.copyBeanProp(entity, param);
        int insert = reminderMapper.updateById(entity);
        if (insert > 0) {
            return AjaxResult.success().setData(entity.getId());
        } else {
            return AjaxResult.fail("");
        }
    }

    @DeleteMapping
    public AjaxResult deleteReminder(@NotNull Long id) {

        int insert = reminderMapper.deleteById(id);
        if (insert > 0) {
            return AjaxResult.success().setData(id);
        } else {
            return AjaxResult.fail("");
        }
    }

    @DeleteMapping
    public AjaxResult getReminder(@NotNull Long id) {

        RemindersReminderEntity byId = reminderMapper.findById(id);
        if (byId != null) {
            return AjaxResult.success().setData(byId);

        } else {
            return AjaxResult.fail("");
        }
    }


}
