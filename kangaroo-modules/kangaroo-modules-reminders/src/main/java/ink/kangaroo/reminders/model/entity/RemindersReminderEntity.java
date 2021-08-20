package ink.kangaroo.reminders.model.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.base.IEntity;
import lombok.Data;

import java.util.Date;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/20 18:07
 */
@Data
@FluentMybatis
public class RemindersReminderEntity implements IEntity {

    private Long id;
    private String title;
    private String notes;
    private String url;
    private Date reminderDate;
    private Date reminderTime;
    private Boolean flag;
    private Integer priority;
    private Long listId;

}
