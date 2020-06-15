package com.lunz.fin.models;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Liuruixia
 * @Description: 用户详情（token 解析）
 * @date 2019/06/20
 */
@Data
@NoArgsConstructor
public class UserDetails implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3902673607259720897L;
    /**
     * userId 用户Id
     */
    @JSONField(name = "sub")
    private String id;
    /**
     * loginName 登录名
     */
    private String name;
    /**
     * name 用户名称
     */
    private String userName;
    /**
     * email 邮箱地址
     */
    private String email;
    /**
     * plainRoles 角色
     */
    private List<String> plainRoles;
    /**
     * roles 应用角色
     */
    private List<ApplicationRole> roles;
    /**
     * applications 所属机构列表
     */
//		private List<OrganizationDTO> applications;
    /**
     * authToken 兼容老用户中心Token
     */
    private String authToken;
    /**
     * DepartmentId 部门ID
     */
    protected String departmentId;
    /**
     * OrganizationId 组织机构ID
     */
    protected String organizationId;
    /**
     * OrganizationName 组织机构名称
     */
    protected String organizationName;
    /**
     * DepartmentName 部门名称
     */
    protected String departmentName;

}
