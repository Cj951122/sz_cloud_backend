package com.lunz.fin.config.entity.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: Fin-Cloud
 * @description
 * @author: lgq
 * @create: 2020-05-08 09:24
 **/
@Data
public class DtoSysDictionaryList implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private List<DtoSysDictionary> value;
}
