package com.yusjade.libms.dao;

import com.yusjade.libms.pojo.Admin;
import java.util.Date;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface AdminMapper {
    @Delete({
        "delete from tb_admin",
        "where admin_id = #{adminId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long adminId);

    @Insert({
        "insert into tb_admin (admin_id, username, ",
        "password, name, ",
        "phone, email, created_at)",
        "values (#{adminId,jdbcType=BIGINT}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{phone,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{createdAt,jdbcType=TIMESTAMP})"
    })
    int insert(Admin record);

    @InsertProvider(type=AdminSqlProvider.class, method="insertSelective")
    int insertSelective(Admin record);

    @Select({
        "select",
        "admin_id, username, password, name, phone, email, created_at",
        "from tb_admin",
        "where admin_id = #{adminId,jdbcType=BIGINT}"
    })
    @ConstructorArgs({
        @Arg(column="admin_id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="username", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="password", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="phone", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="created_at", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP)
    })
    Admin selectByPrimaryKey(Long adminId);

    @UpdateProvider(type=AdminSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Admin record);

    @Update({
        "update tb_admin",
        "set username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP}",
        "where admin_id = #{adminId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Admin record);
}