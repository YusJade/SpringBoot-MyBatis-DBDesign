package com.yusjade.libms.dao;

import com.yusjade.libms.pojo.PersonalizedInfo;
import org.apache.ibatis.jdbc.SQL;

public class PersonalizedInfoSqlProvider {

    public String insertSelective(PersonalizedInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_personalized_info");
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=BIGINT}");
        }
        
        if (record.getSignature() != null) {
            sql.VALUES("signature", "#{signature,jdbcType=VARCHAR}");
        }
        
        if (record.getAvatar() != null) {
            sql.VALUES("avator", "#{avator,jdbcType=LONGVARBINARY}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(PersonalizedInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_personalized_info");
        
        if (record.getSignature() != null) {
            sql.SET("signature = #{signature,jdbcType=VARCHAR}");
        }
        
        if (record.getAvatar() != null) {
            sql.SET("avator = #{avator,jdbcType=LONGVARBINARY}");
        }
        
        sql.WHERE("user_id = #{userId,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}