package com.lunz.fin.config.entity.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sys_dictionary")
public class Dictionary implements Serializable {
    private Integer id;

    private String type;

    private String content;

    private Integer code;

    private String clientId;

    private String pattern;

    private String clientName;

    private String appKey;

}