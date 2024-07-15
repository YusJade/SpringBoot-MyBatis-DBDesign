package com.yusjade.libms.dao;

import com.yusjade.libms.pojo.BookInventory;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class BookInventorySqlProvider {

  public String insertSelective(BookInventory record) {
    SQL sql = new SQL();
    sql.INSERT_INTO("tb_book_inventory");

    if (record.getInventoryId() != null) {
      sql.VALUES("inventory_id", "#{inventoryId,jdbcType=BIGINT}");
    }

    if (record.getCategoryId() != null) {
      sql.VALUES("category_id", "#{categoryId,jdbcType=BIGINT}");
    }

    if (record.getBookTitle() != null) {
      sql.VALUES("book_title", "#{bookTitle,jdbcType=VARCHAR}");
    }

    if (record.getAuthor() != null) {
      sql.VALUES("author", "#{author,jdbcType=VARCHAR}");
    }

    if (record.getPublisher() != null) {
      sql.VALUES("publisher", "#{publisher,jdbcType=VARCHAR}");
    }

    if (record.getQuantity() != null) {
      sql.VALUES("quantity", "#{quantity,jdbcType=BIGINT}");
    }

    return sql.toString();
  }

  public String updateByPrimaryKeySelective(BookInventory record) {
    SQL sql = new SQL();
    sql.UPDATE("tb_book_inventory");

    if (record.getCategoryId() != null) {
      sql.SET("category_id = #{categoryId,jdbcType=BIGINT}");
    }

    if (record.getBookTitle() != null) {
      sql.SET("book_title = #{bookTitle,jdbcType=VARCHAR}");
    }

    if (record.getAuthor() != null) {
      sql.SET("author = #{author,jdbcType=VARCHAR}");
    }

    if (record.getPublisher() != null) {
      sql.SET("publisher = #{publisher,jdbcType=VARCHAR}");
    }

    if (record.getQuantity() != null) {
      sql.SET("quantity = #{quantity,jdbcType=BIGINT}");
    }

    sql.WHERE("inventory_id = #{inventoryId,jdbcType=BIGINT}");

    return sql.toString();
  }

  public String selectByParamSelective(@Param("title") String title,
      @Param("author") String author, @Param("publisher") String publisher,
      @Param("keyword") String keyword) {
    SQL sql = new SQL();

    sql.SELECT("inventory_id, category_id, book_title, author, publisher, quantity")
        .FROM("tb_book_inventory");

    if (title != null && !title.isEmpty()) {
//            sql.WHERE("title = " + title);
        sql.WHERE("book_title = 'Test'");
    }
    if (author != null && !author.isEmpty()) {
      sql.WHERE("author = #{author,jdbcType=VARCHAR}");
    }
    if (publisher != null && !publisher.isEmpty()) {
      sql.WHERE("publisher = #{publisher,jdbcType=VARCHAR}");
    }
    if (keyword != null && !keyword.isEmpty()) {
      sql.WHERE("book_title LIKE CONCAT('%', #{keyword,jdbcType=VARCHAR}, '%')")
          .OR().WHERE("author LIKE CONCAT('%', #{keyword,jdbcType=VARCHAR}, '%')")
          .OR().WHERE("publisher LIKE CONCAT('%', #{keyword,jdbcType=VARCHAR}, '%')");
    }

    return sql.toString();
  }

}