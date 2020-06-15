package com.lunz.fin.config.entity.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author haha
 * @desc 客户ID规则配置表
 */
@Data
@TableName("tb_client")
public class ClientConfig implements Serializable {
    @TableId(value = "Id",type = IdType.INPUT)
    private String id;

    private String name;

    private String code;

    private String applicationCode;

    private Date createdAt;

    private String createdById;

    private Date updatedAt;

    private String updatedById;

    private Boolean deleted;

    private Date deletedAt;

    private String deletedById;

    private String jsonString;

}