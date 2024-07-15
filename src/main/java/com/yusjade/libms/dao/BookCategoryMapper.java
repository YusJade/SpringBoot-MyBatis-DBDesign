package com.yusjade.libms.dao;

import com.yusjade.libms.pojo.BookCategory;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface BookCategoryMapper {
    @Delete({
        "delete from tb_book_category",
        "where category_id = #{categoryId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long categoryId);

    @Insert({
        "insert into tb_book_category (category_id, name)",
        "values (#{categoryId,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR})"
    })
    int insert(BookCategory record);

    @InsertProvider(type=BookCategorySqlProvider.class, method="insertSelective")
    int insertSelective(BookCategory record);

    @Select({
        "select",
        "category_id, name",
        "from tb_book_category",
        "where category_id = #{categoryId,jdbcType=BIGINT}"
    })
    @ConstructorArgs({
        @Arg(column="category_id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    BookCategory selectByPrimaryKey(Long categoryId);

    @UpdateProvider(type=BookCategorySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(BookCategory record);

    @Update({
        "update tb_book_category",
        "set name = #{name,jdbcType=VARCHAR}",
        "where category_id = #{categoryId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(BookCategory record);
}