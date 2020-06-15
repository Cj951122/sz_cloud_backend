package com.lunz.fin.config.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * @author haha
 */
@Data
@TableName("sys_clientstatus_config")
public class ClientStatusConfig implements Serializable {

    @TableId(value = "Id",type = IdType.AUTO)
    private Long id;

    private String clientId;

    private String clientName;

    private Short status;

    private String statusDesc;

    private Long code;

    private Short nodeOrder;

    @TableField(exist = false)
    private String codeName;

}
