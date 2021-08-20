package ink.kangaroo.reminders.model.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.base.IEntity;
import lombok.Data;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/20 18:04
 */
@Data
@FluentMybatis
public class RemindersGroupEntity implements IEntity {
    private Long id;
    private String groupId;
    private Long iconId;
}
