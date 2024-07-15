package com.yusjade.libms.dao;

import com.yusjade.libms.pojo.Book;
import java.util.List;
import java.util.Map;
import javax.swing.text.html.Option;
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

public interface BookMapper {
    @Delete({
        "delete from tb_book",
        "where book_id = #{bookId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long bookId);

    @Insert({
        "insert into tb_book (inventory_id, ",
        "is_borrowed, is_discarded)",
        "values (#{inventoryId,jdbcType=BIGINT}, ",
        "#{isBorrowed,jdbcType=BIT}, #{isDiscarded,jdbcType=BIT})"
    })
    @Options(keyProperty = "bookId", useGeneratedKeys = true)
    int insert(Book record);

    @InsertProvider(type=BookSqlProvider.class, method="insertSelective")
    int insertSelective(Book record);

    @Select({
        "select",
        "book_id, inventory_id, is_borrowed, is_discarded",
        "from tb_book",
        "where book_id = #{bookId,jdbcType=BIGINT}"
    })
    @ConstructorArgs({
        @Arg(column="book_id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="inventory_id", javaType=Long.class, jdbcType=JdbcType.BIGINT),
        @Arg(column="is_borrowed", javaType=Boolean.class, jdbcType=JdbcType.BIT),
        @Arg(column="is_discarded", javaType=Boolean.class, jdbcType=JdbcType.BIT)
    })
    Book selectByPrimaryKey(Long bookId);

    @UpdateProvider(type=BookSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Book record);

    @SelectProvider(type=BookSqlProvider.class, method="selectByParamSelective")
    @ConstructorArgs({
        @Arg(column="book_id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="inventory_id", javaType=Long.class, jdbcType=JdbcType.BIGINT),
        @Arg(column="is_borrowed", javaType=Boolean.class, jdbcType=JdbcType.BIT),
        @Arg(column="is_discarded", javaType=Boolean.class, jdbcType=JdbcType.BIT)
    })
    List<Book> selectByParamSelective(
        @Param("bookId") Long bookId,
        @Param("inventoryId") Long inventoryId,
        @Param("isBorrowed") Boolean isBorrowed,
        @Param("isDiscarded") Boolean isDiscarded);

    @Update({
        "update tb_book",
        "set inventory_id = #{inventoryId,jdbcType=BIGINT},",
          "is_borrowed = #{isBorrowed,jdbcType=BIT},",
          "is_discarded = #{isDiscarded,jdbcType=BIT}",
        "where book_id = #{bookId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Book record);
}