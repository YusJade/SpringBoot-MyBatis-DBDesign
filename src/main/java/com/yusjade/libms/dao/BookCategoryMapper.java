package com.yusjade.libms.dao;

import com.yusjade.libms.pojo.BookCategory;
import java.util.List;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
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
        "insert into tb_book_category (name)",
        "values (#{name,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "categoryId")
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

    @SelectProvider(type = BookCategorySqlProvider.class, method = "selectByKeyword")
    @ConstructorArgs({
        @Arg(column="category_id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    List<BookCategory> selectByKeyword(@Param("keyword") String keyword);

}