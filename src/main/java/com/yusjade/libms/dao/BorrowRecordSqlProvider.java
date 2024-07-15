package com.yusjade.libms.dao;

import com.yusjade.libms.pojo.BorrowRecord;
import org.apache.ibatis.jdbc.SQL;

public class BorrowRecordSqlProvider {

    public String insertSelective(BorrowRecord record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_borrow_record");
        
        if (record.getRecordId() != null) {
            sql.VALUES("record_id", "#{recordId,jdbcType=BIGINT}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=BIGINT}");
        }
        
        if (record.getBookId() != null) {
            sql.VALUES("book_id", "#{bookId,jdbcType=BIGINT}");
        }
        
        if (record.getBorrowDate() != null) {
            sql.VALUES("borrow_date", "#{borrowDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOughtReturnDate() != null) {
            sql.VALUES("ought_return_date", "#{oughtReturnDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getActualReturnDate() != null) {
            sql.VALUES("actual_return_date", "#{actualReturnDate,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(BorrowRecord record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_borrow_record");
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=BIGINT}");
        }
        
        if (record.getBookId() != null) {
            sql.SET("book_id = #{bookId,jdbcType=BIGINT}");
        }
        
        if (record.getBorrowDate() != null) {
            sql.SET("borrow_date = #{borrowDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOughtReturnDate() != null) {
            sql.SET("ought_return_date = #{oughtReturnDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getActualReturnDate() != null) {
            sql.SET("actual_return_date = #{actualReturnDate,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("record_id = #{recordId,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}