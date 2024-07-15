package com.yusjade.libms.dao;

import com.yusjade.libms.pojo.InventoryRecord;
import org.apache.ibatis.jdbc.SQL;

public class InventoryRecordSqlProvider {

    public String insertSelective(InventoryRecord record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_inventory_record");
        
        if (record.getRecordId() != null) {
            sql.VALUES("record_id", "#{recordId,jdbcType=BIGINT}");
        }
        
        if (record.getCreatedBy() != null) {
            sql.VALUES("created_by", "#{createdBy,jdbcType=BIGINT}");
        }
        
        if (record.getInventoryId() != null) {
            sql.VALUES("inventory_id", "#{inventoryId,jdbcType=BIGINT}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.VALUES("created_at", "#{createdAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getQuantity() != null) {
            sql.VALUES("quantity", "#{quantity,jdbcType=INTEGER}");
        }
        
        if (record.getNote() != null) {
            sql.VALUES("note", "#{note,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(InventoryRecord record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_inventory_record");
        
        if (record.getCreatedBy() != null) {
            sql.SET("created_by = #{createdBy,jdbcType=BIGINT}");
        }
        
        if (record.getInventoryId() != null) {
            sql.SET("inventory_id = #{inventoryId,jdbcType=BIGINT}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.SET("created_at = #{createdAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getQuantity() != null) {
            sql.SET("quantity = #{quantity,jdbcType=INTEGER}");
        }
        
        if (record.getNote() != null) {
            sql.SET("note = #{note,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("record_id = #{recordId,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}