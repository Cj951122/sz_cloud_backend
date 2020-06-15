package com.lunz.fin.config.entity;


import lombok.Data;
import java.io.Serializable;

/**
 *
 */
@Data
public class DictionaryVo implements  Serializable {

    private Integer id;

    private String type;

    private String content;

    private Integer code;

    private String clientId;

    private String pattern;

    private String clientName;

    private String appKey;
}
