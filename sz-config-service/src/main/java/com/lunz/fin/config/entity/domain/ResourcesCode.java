package com.lunz.fin.config.entity.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author haha
 */
@Data
@TableName("sys_resources_code")
public class ResourcesCode implements Serializable {
    private Long id;

    private String name;

}