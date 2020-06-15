package com.lunz.fin.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Liuruixia
 * @Description:
 * @date 2019/06/20
 */
@Data
@AllArgsConstructor
public class ApplicationRole {
    /**
     * 应用角色id
     */
    private String id;
    /**
     * 应用角色name
     */
    private String name;

}
