package com.yusjade.libms.dao;

import com.yusjade.libms.pojo.BookCategory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class BookCategorySqlProvider {

    public String insertSelective(BookCategory record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_book_category");
        
        if (record.getCategoryId() != null) {
            sql.VALUES("category_id", "#{categoryId,jdbcType=BIGINT}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(BookCategory record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_book_category");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("category_id = #{categoryId,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    public String selectByKeyword(@Param("keyword") String keyword) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("tb_book_category");
        if (keyword == null || keyword == "") {
            sql.WHERE("category_id = -1");
            return sql.toString();
        }
        sql.WHERE("name like CONCAT('%', #{keyword,jdbcType=VARCHAR},'%')");
        return sql.toString();
    }
}