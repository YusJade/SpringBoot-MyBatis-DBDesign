package com.yusjade.libms.dao;

import com.yusjade.libms.pojo.Permission;
import java.util.List;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface PermissionMapper {
    @Delete({
        "delete from tb_permission",
        "where permission_name = #{permissionName,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String permissionName);

    @Insert({
        "insert into tb_permission (permission_name, permission_desc, ",
        "max_borrow_day, max_borrow_books)",
        "values (#{permissionName,jdbcType=VARCHAR}, #{permissionDesc,jdbcType=VARCHAR}, ",
        "#{maxBorrowDay,jdbcType=SMALLINT}, #{maxBorrowBooks,jdbcType=SMALLINT})"
    })
    int insert(Permission record);

    @InsertProvider(type=PermissionSqlProvider.class, method="insertSelective")
    int insertSelective(Permission record);

    @Select({
        "select",
        "permission_name, permission_desc, max_borrow_day, max_borrow_books",
        "from tb_permission",
        "where permission_name = #{permissionName,jdbcType=VARCHAR}"
    })
    @ConstructorArgs({
        @Arg(column="permission_name", javaType=String.class, jdbcType=JdbcType.VARCHAR, id=true),
        @Arg(column="permission_desc", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="max_borrow_day", javaType=Short.class, jdbcType=JdbcType.SMALLINT),
        @Arg(column="max_borrow_books", javaType=Short.class, jdbcType=JdbcType.SMALLINT)
    })
    Permission selectByPrimaryKey(String permissionName);

    @UpdateProvider(type=PermissionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Permission record);

    @Update({
        "update tb_permission",
        "set permission_desc = #{permissionDesc,jdbcType=VARCHAR},",
          "max_borrow_day = #{maxBorrowDay,jdbcType=SMALLINT},",
          "max_borrow_books = #{maxBorrowBooks,jdbcType=SMALLINT}",
        "where permission_name = #{permissionName,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Permission record);

    @Select({
        "select",
        "permission_name, permission_desc, max_borrow_day, max_borrow_books",
        "from tb_permission"
    })
    @ConstructorArgs({
        @Arg(column="permission_name", javaType=String.class, jdbcType=JdbcType.VARCHAR, id=true),
        @Arg(column="permission_desc", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="max_borrow_day", javaType=Short.class, jdbcType=JdbcType.SMALLINT),
        @Arg(column="max_borrow_books", javaType=Short.class, jdbcType=JdbcType.SMALLINT)
    })
    List<Permission> selectAll();
}