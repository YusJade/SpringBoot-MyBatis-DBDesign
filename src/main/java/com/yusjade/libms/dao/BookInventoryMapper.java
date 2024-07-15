package com.yusjade.libms.dao;

import com.yusjade.libms.pojo.BookInventory;
import java.util.List;
import java.util.Map;
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

public interface BookInventoryMapper {

  @Delete({
      "delete from tb_book_inventory",
      "where inventory_id = #{inventoryId,jdbcType=BIGINT}"
  })
  int deleteByPrimaryKey(Long inventoryId);

  @Insert({
      "insert into tb_book_inventory (inventory_id, category_id, ",
      "book_title, author, ",
      "publisher, quantity)",
      "values (#{inventoryId,jdbcType=BIGINT}, #{categoryId,jdbcType=BIGINT}, ",
      "#{bookTitle,jdbcType=VARCHAR}, #{author,jdbcType=VARCHAR}, ",
      "#{publisher,jdbcType=VARCHAR}, #{quantity,jdbcType=BIGINT})"
  })
  int insert(BookInventory record);

  @InsertProvider(type = BookInventorySqlProvider.class, method = "insertSelective")
  int insertSelective(BookInventory record);

  @Select({
      "select",
      "inventory_id, category_id, book_title, author, publisher, quantity",
      "from tb_book_inventory",
      "where inventory_id = #{inventoryId,jdbcType=BIGINT}"
  })
  @ConstructorArgs({
      @Arg(column = "inventory_id", javaType = Long.class, jdbcType = JdbcType.BIGINT, id = true),
      @Arg(column = "category_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
      @Arg(column = "book_title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Arg(column = "author", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Arg(column = "publisher", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Arg(column = "quantity", javaType = Long.class, jdbcType = JdbcType.BIGINT)
  })
  BookInventory selectByPrimaryKey(Long inventoryId);

  @SelectProvider(type = BookInventorySqlProvider.class, method = "selectByParamSelective")
  @ConstructorArgs({
      @Arg(column = "inventory_id", javaType = Long.class, jdbcType = JdbcType.BIGINT, id = true),
      @Arg(column = "category_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
      @Arg(column = "book_title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Arg(column = "author", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Arg(column = "publisher", javaType = String.class, jdbcType = JdbcType.VARCHAR),
      @Arg(column = "quantity", javaType = Long.class, jdbcType = JdbcType.BIGINT)
  })
  List<BookInventory> selectByParamSelective(@Param("title") String title,
      @Param("author") String author, @Param("publisher") String publisher,
      @Param("keyword") String keyword);

  @UpdateProvider(type = BookInventorySqlProvider.class, method = "updateByPrimaryKeySelective")
  int updateByPrimaryKeySelective(BookInventory record);

  @Update({
      "update tb_book_inventory",
      "set category_id = #{categoryId,jdbcType=BIGINT},",
      "book_title = #{bookTitle,jdbcType=VARCHAR},",
      "author = #{author,jdbcType=VARCHAR},",
      "publisher = #{publisher,jdbcType=VARCHAR},",
      "quantity = #{quantity,jdbcType=BIGINT}",
      "where inventory_id = #{inventoryId,jdbcType=BIGINT}"
  })
  int updateByPrimaryKey(BookInventory record);
}