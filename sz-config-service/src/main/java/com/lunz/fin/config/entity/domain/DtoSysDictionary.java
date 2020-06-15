package com.lunz.fin.config.entity.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: Fin-Cloud
 * @description  sys_dictionary实体(value-label)
 * @author: lgq
 * @create: 2020-05-08 09:23
 **/
@Data
public class DtoSysDictionary implements Serializable {
    /**
     * 状态码 code
     */
    @ApiModelProperty(value = "状态码")
    private String value;

    /**
     * 状态描述 content
     */
    @ApiModelProperty(value = "状态描述")
    private String label;

    private static final long serialVersionUID = 1L;
}
