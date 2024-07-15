package com.yusjade.libms.pojo;

import java.util.Date;
import lombok.Data;

/**
 * tb_user
 * @author 19179
 * @date yyyy-MM-dd HH:mm:ss
 */
@Data
public class User {
    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 权限名
     */
    private String permissionName;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private String gender;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 创建日期
     */
    private Date createdAt;

    public User(Long userId, String permissionName, String username, String password, String gender, String name, String email, String phone, Date createdAt) {
        this.userId = userId;
        this.permissionName = permissionName;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    public User() {
        super();
    }
}