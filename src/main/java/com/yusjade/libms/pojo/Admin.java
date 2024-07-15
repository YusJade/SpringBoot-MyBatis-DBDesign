package com.yusjade.libms.pojo;

import java.util.Date;
import lombok.Data;

/**
 * tb_admin
 * @author 19179
 * @date yyyy-MM-dd HH:mm:ss
 */
@Data
public class Admin {
    /**
     * 管理员 id
     */
    private Long adminId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 创建日期
     */
    private Date createdAt;

    public Admin(Long adminId, String username, String password, String name, String phone, String email, Date createdAt) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Admin() {
        super();
    }
}