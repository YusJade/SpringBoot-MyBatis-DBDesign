package com.yusjade.libms.dao;

import com.yusjade.libms.pojo.User;
import java.util.Date;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {
    @Delete({
        "delete from tb_user",
        "where user_id = #{userId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long userId);

    @Insert({
        "insert into tb_user (permission_name, ",
        "username, password, ",
        "gender, name, email, ",
        "phone)",
        "values (#{permissionName,jdbcType=VARCHAR}, ",
        "#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{gender,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, ",
        "#{phone,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    int insertSelective(User record);

    @Select({
        "select",
        "user_id, permission_name, username, password, gender, name, email, phone, created_at",
        "from tb_user",
        "where user_id = #{userId,jdbcType=BIGINT}"
    })
    @ConstructorArgs({
        @Arg(column="user_id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="permission_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="username", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="password", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="gender", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="phone", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="created_at", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP)
    })
    User selectByPrimaryKey(Long userId);

    @Select({
        "select",
        "user_id, permission_name, username, password, gender, name, email, phone, created_at",
        "from tb_user",
        "where username = #{username,jdbcType=VARCHAR}"
    })
    @ConstructorArgs({
        @Arg(column="user_id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="permission_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="username", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="password", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="gender", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="phone", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="created_at", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP)
    })
    User selectByUsername(String username);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update tb_user",
        "set permission_name = #{permissionName,jdbcType=VARCHAR},",
          "username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "gender = #{gender,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP}",
        "where user_id = #{userId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(User record);
}