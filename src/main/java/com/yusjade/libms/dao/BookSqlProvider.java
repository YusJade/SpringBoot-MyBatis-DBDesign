package com.yusjade.libms.dao;

import com.yusjade.libms.pojo.Book;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class BookSqlProvider {

  public String insertSelective(Book record) {
    SQL sql = new SQL();
    sql.INSERT_INTO("tb_book");

    if (record.getBookId() != null) {
      sql.VALUES("book_id", "#{bookId,jdbcType=BIGINT}");
    }

    if (record.getInventoryId() != null) {
      sql.VALUES("inventory_id", "#{inventoryId,jdbcType=BIGINT}");
    }

    if (record.getIsBorrowed() != null) {
      sql.VALUES("is_borrowed", "#{isBorrowed,jdbcType=BIT}");
    }

    if (record.getIsDiscarded() != null) {
      sql.VALUES("is_discarded", "#{isDiscarded,jdbcType=BIT}");
    }

    return sql.toString();
  }

  public String updateByPrimaryKeySelective(Book record) {
    SQL sql = new SQL();
    sql.UPDATE("tb_book");

    if (record.getInventoryId() != null) {
      sql.SET("inventory_id = #{inventoryId,jdbcType=BIGINT}");
    }

    if (record.getIsBorrowed() != null) {
      sql.SET("is_borrowed = #{isBorrowed,jdbcType=BIT}");
    }

    if (record.getIsDiscarded() != null) {
      sql.SET("is_discarded = #{isDiscarded,jdbcType=BIT}");
    }

    sql.WHERE("book_id = #{bookId,jdbcType=BIGINT}");

    return sql.toString();
  }

  public String selectByParamSelective(
      @Param("bookId") Long bookId,
      @Param("inventoryId") Long inventoryId,
      @Param("isBorrowed") Boolean isBorrowed,
      @Param("isDiscarded") Boolean isDiscarded) {
    SQL sql = new SQL();

    sql.SELECT("book_id, inventory_id, is_borrowed, is_discarded").FROM("tb_book");
    if (inventoryId != null) {
      sql.WHERE("inventory_id = #{inventoryId,jdbcType=BIGINT}");
    }
    if (bookId != null) {
      sql.WHERE("book_id = #{bookId,jdbcType=BIGINT}");
    }
    if (isBorrowed != null) {
      sql.WHERE("is_borrowed = #{isBorrowed,jdbcType=BIT}");
    }
    if (isDiscarded != null) {
      sql.WHERE("is_discarded = #{isDiscarded,jdbcType=BIT}");
    }

    return sql.toString();
  }
}