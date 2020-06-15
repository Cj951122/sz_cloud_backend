package com.lunz.fin.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Liuruixia
 * @Description:部门信息
 * @date 2019/06/20
 */
@Data
@AllArgsConstructor
public class Department {
    /**
     * 部门id
     */
    private String id;
    /**
     * 部门名称
     */
    private String name;
}
