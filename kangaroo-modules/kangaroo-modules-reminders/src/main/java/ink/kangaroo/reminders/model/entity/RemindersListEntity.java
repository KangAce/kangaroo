package ink.kangaroo.reminders.model.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.base.IEntity;
import lombok.Data;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/20 18:06
 */
@Data
@FluentMybatis
public class RemindersListEntity implements IEntity {

    private Long id;

    private String listName;

    private Long iconId;

    private Long groupId;

}
