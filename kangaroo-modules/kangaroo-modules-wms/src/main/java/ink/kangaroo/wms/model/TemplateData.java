package ink.kangaroo.wms.model;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.base.IEntity;
import ink.kangaroo.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/6 9:06
 */
@Data
@FluentMybatis
public class TemplateData implements IEntity {

    private Integer id;
    private Integer instanceId;
    private String data;
    private String htmlText;
    private Long userId;

}
