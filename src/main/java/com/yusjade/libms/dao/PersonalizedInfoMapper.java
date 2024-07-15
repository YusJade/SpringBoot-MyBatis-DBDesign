package com.yusjade.libms.dao;

import com.yusjade.libms.pojo.PersonalizedInfo;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface PersonalizedInfoMapper {
    @Delete({
        "delete from tb_personalized_info",
        "where user_id = #{userId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long userId);

    @Insert({
        "insert into tb_personalized_info (user_id, signature, ",
        "avator)",
        "values (#{userId,jdbcType=BIGINT}, #{signature,jdbcType=VARCHAR}, ",
        "#{avator,jdbcType=LONGVARBINARY})"
    })
    int insert(PersonalizedInfo record);

    @InsertProvider(type=PersonalizedInfoSqlProvider.class, method="insertSelective")
    int insertSelective(PersonalizedInfo record);

    @Select({
        "select",
        "user_id, signature, avator",
        "from tb_personalized_info",
        "where user_id = #{userId,jdbcType=BIGINT}"
    })
    @ConstructorArgs({
        @Arg(column="user_id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="signature", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="avator", javaType=byte[].class, jdbcType=JdbcType.LONGVARBINARY)
    })
    PersonalizedInfo selectByPrimaryKey(Long userId);

    @UpdateProvider(type=PersonalizedInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PersonalizedInfo record);

    @Update({
        "update tb_personalized_info",
        "set signature = #{signature,jdbcType=VARCHAR},",
          "avator = #{avator,jdbcType=LONGVARBINARY}",
        "where user_id = #{userId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(PersonalizedInfo record);

    @Update({
        "update tb_personalized_info",
        "set signature = #{signature,jdbcType=VARCHAR}",
        "where user_id = #{userId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(PersonalizedInfo record);
}