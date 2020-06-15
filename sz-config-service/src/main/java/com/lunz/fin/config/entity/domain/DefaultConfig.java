package com.lunz.fin.config.entity.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sys_config")
public class DefaultConfig implements Serializable {
    private Integer id;

    private String category;

    private String key;

    private String value;

    private String clientId;

    private String clientName;

    private String deleted;

    private String appKey;
}
