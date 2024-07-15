package com.yusjade.libms.pojo;

import lombok.Data;

/**
 * tb_permission
 * @author 19179
 * @date yyyy-MM-dd HH:mm:ss
 */
@Data
public class Permission {
    /**
     * 权限名
     */
    private String permissionName;

    /**
     * 权限描述
     */
    private String permissionDesc;

    /**
     * 最长借阅天数
     */
    private Short maxBorrowDay;

    /**
     * 最大借阅数目
     */
    private Short maxBorrowBooks;

    public Permission(String permissionName, String permissionDesc, Short maxBorrowDay, Short maxBorrowBooks) {
        this.permissionName = permissionName;
        this.permissionDesc = permissionDesc;
        this.maxBorrowDay = maxBorrowDay;
        this.maxBorrowBooks = maxBorrowBooks;
    }

    public Permission() {
        super();
    }
}