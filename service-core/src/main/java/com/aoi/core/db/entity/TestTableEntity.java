package com.aoi.core.db.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.sql.Timestamp;

/**
 * @ClassName TestTable
 * @Description TODO
 * @Author 86184
 * @Date 2024/10/1 16:13
 */
@TableName("test_table")
public class TestTableEntity {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String name;

    private String description;

    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime;

    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    @TableField(fill = FieldFill.UPDATE)
    private Timestamp updateTime;

    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
