package ink.kangaroo.wms.model;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.base.IEntity;
import lombok.Data;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/6 9:11
 */
@Data
@FluentMybatis
public class TemplateInstance implements IEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TemplateInstance.id
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TemplateInstance.defId
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    private Integer defId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TemplateInstance.moduleId
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    private Integer moduleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TemplateInstance.name
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TemplateInstance.created
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    private Integer created;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TemplateInstance.updated
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    private Integer updated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TemplateInstance.userId
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TemplateInstance.isDeleted
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    private Byte isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TemplateInstance.code
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TemplateInstance.isPublish
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    private Byte isPublish;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TemplateInstance.type
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    private String type;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TemplateInstance.id
     *
     * @return the value of TemplateInstance.id
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TemplateInstance.id
     *
     * @param id the value for TemplateInstance.id
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TemplateInstance.defId
     *
     * @return the value of TemplateInstance.defId
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public Integer getDefId() {
        return defId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TemplateInstance.defId
     *
     * @param defId the value for TemplateInstance.defId
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public void setDefId(Integer defId) {
        this.defId = defId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TemplateInstance.moduleId
     *
     * @return the value of TemplateInstance.moduleId
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public Integer getModuleId() {
        return moduleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TemplateInstance.moduleId
     *
     * @param moduleId the value for TemplateInstance.moduleId
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TemplateInstance.name
     *
     * @return the value of TemplateInstance.name
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TemplateInstance.name
     *
     * @param name the value for TemplateInstance.name
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TemplateInstance.created
     *
     * @return the value of TemplateInstance.created
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public Integer getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TemplateInstance.created
     *
     * @param created the value for TemplateInstance.created
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public void setCreated(Integer created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TemplateInstance.updated
     *
     * @return the value of TemplateInstance.updated
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public Integer getUpdated() {
        return updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TemplateInstance.updated
     *
     * @param updated the value for TemplateInstance.updated
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TemplateInstance.userId
     *
     * @return the value of TemplateInstance.userId
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TemplateInstance.userId
     *
     * @param userId the value for TemplateInstance.userId
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TemplateInstance.isDeleted
     *
     * @return the value of TemplateInstance.isDeleted
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public Byte getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TemplateInstance.isDeleted
     *
     * @param isDeleted the value for TemplateInstance.isDeleted
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TemplateInstance.code
     *
     * @return the value of TemplateInstance.code
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TemplateInstance.code
     *
     * @param code the value for TemplateInstance.code
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TemplateInstance.isPublish
     *
     * @return the value of TemplateInstance.isPublish
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public Byte getIsPublish() {
        return isPublish;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TemplateInstance.isPublish
     *
     * @param isPublish the value for TemplateInstance.isPublish
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public void setIsPublish(Byte isPublish) {
        this.isPublish = isPublish;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TemplateInstance.type
     *
     * @return the value of TemplateInstance.type
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TemplateInstance.type
     *
     * @param type the value for TemplateInstance.type
     *
     * @mbggenerated Mon Jan 04 13:23:33 CST 2016
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }



}