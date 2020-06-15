package com.lunz.fin.config.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class DefaultConfigVo implements Serializable {

    private Integer id;

    private String category;

    private String key;

    private String value;

    private String clientId;

    private String clientName;

    private String deleted;

    private String appKey;
}
