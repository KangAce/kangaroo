package ink.kangaroo.reminders.controller;

import ink.kangaroo.common.core.web.controller.BaseController;
import ink.kangaroo.common.core.web.domain.AjaxResult;
import ink.kangaroo.reminders.model.entity.RemindersReminderEntity;
import ink.kangaroo.reminders.model.mapper.RemindersReminderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/20 17:32
 */
@RestController
@RequestMapping("group")
public class GroupController extends BaseController {

    @Autowired
    RemindersReminderMapper reminderMapper;

    @RequestMapping
    public AjaxResult addGroup(String groupName){
        reminderMapper.delete(reminderMapper.query().where().id().eq(1).end());
        RemindersReminderEntity remindersReminderEntity = new RemindersReminderEntity();
        int insert = reminderMapper.insert(remindersReminderEntity);
        if (insert>0){
            return AjaxResult.success().setData(remindersReminderEntity.getId());
        }else {
            return AjaxResult.fail("");
        }

    }
}
