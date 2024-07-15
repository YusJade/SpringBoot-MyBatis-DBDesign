package com.yusjade.libms.dao;

import com.yusjade.libms.pojo.Permission;
import org.apache.ibatis.jdbc.SQL;

public class PermissionSqlProvider {

    public String insertSelective(Permission record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_permission");
        
        if (record.getPermissionName() != null) {
            sql.VALUES("permission_name", "#{permissionName,jdbcType=VARCHAR}");
        }
        
        if (record.getPermissionDesc() != null) {
            sql.VALUES("permission_desc", "#{permissionDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getMaxBorrowDay() != null) {
            sql.VALUES("max_borrow_day", "#{maxBorrowDay,jdbcType=SMALLINT}");
        }
        
        if (record.getMaxBorrowBooks() != null) {
            sql.VALUES("max_borrow_books", "#{maxBorrowBooks,jdbcType=SMALLINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Permission record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_permission");
        
        if (record.getPermissionDesc() != null) {
            sql.SET("permission_desc = #{permissionDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getMaxBorrowDay() != null) {
            sql.SET("max_borrow_day = #{maxBorrowDay,jdbcType=SMALLINT}");
        }
        
        if (record.getMaxBorrowBooks() != null) {
            sql.SET("max_borrow_books = #{maxBorrowBooks,jdbcType=SMALLINT}");
        }
        
        sql.WHERE("permission_name = #{permissionName,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}