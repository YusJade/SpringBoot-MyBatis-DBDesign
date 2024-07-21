package com.yusjade.libms.dao;

import com.yusjade.libms.pojo.BorrowRecord;
import java.util.Date;
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

public interface BorrowRecordMapper {

  @Delete({
      "delete from tb_borrow_record",
      "where record_id = #{recordId,jdbcType=BIGINT}"
  })
  int deleteByPrimaryKey(Long recordId);

  @Insert({
      "insert into tb_borrow_record (user_id, ",
      "book_id, borrow_date, ",
      "ought_return_date, actual_return_date)",
      "values (#{userId,jdbcType=BIGINT}, ",
      "#{bookId,jdbcType=BIGINT}, #{borrowDate,jdbcType=TIMESTAMP}, ",
      "#{oughtReturnDate,jdbcType=TIMESTAMP}, #{actualReturnDate,jdbcType=TIMESTAMP})"
  })
  @Options(useGeneratedKeys = true, keyProperty = "recordId")
  int insert(BorrowRecord record);

  @InsertProvider(type = BorrowRecordSqlProvider.class, method = "insertSelective")
  int insertSelective(BorrowRecord record);

  @Select({
      "select",
      "record_id, user_id, book_id, borrow_date, ought_return_date, actual_return_date",
      "from tb_borrow_record",
      "where record_id = #{recordId,jdbcType=BIGINT}"
  })
  @ConstructorArgs({
      @Arg(column = "record_id", javaType = Long.class, jdbcType = JdbcType.BIGINT, id = true),
      @Arg(column = "user_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
      @Arg(column = "book_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
      @Arg(column = "borrow_date", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
      @Arg(column = "ought_return_date", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
      @Arg(column = "actual_return_date", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
  })
  BorrowRecord selectByPrimaryKey(Long recordId);

  @UpdateProvider(type = BorrowRecordSqlProvider.class, method = "updateByPrimaryKeySelective")
  int updateByPrimaryKeySelective(BorrowRecord record);

  @Update({
      "update tb_borrow_record",
      "set user_id = #{userId,jdbcType=BIGINT},",
      "book_id = #{bookId,jdbcType=BIGINT},",
      "borrow_date = #{borrowDate,jdbcType=TIMESTAMP},",
      "ought_return_date = #{oughtReturnDate,jdbcType=TIMESTAMP},",
      "actual_return_date = #{actualReturnDate,jdbcType=TIMESTAMP}",
      "where record_id = #{recordId,jdbcType=BIGINT}"
  })
  int updateByPrimaryKey(BorrowRecord record);

  @SelectProvider(type = BorrowRecordSqlProvider.class, method = "selectByParamSelective")
  @ConstructorArgs({
      @Arg(column = "record_id", javaType = Long.class, jdbcType = JdbcType.BIGINT, id = true),
      @Arg(column = "user_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
      @Arg(column = "book_id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
      @Arg(column = "borrow_date", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
      @Arg(column = "ought_return_date", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
      @Arg(column = "actual_return_date", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
  })
  List<BorrowRecord> selectByParamSelective(
      @Param("userId") Long userId,
      @Param("bookId") Long bookId,
      @Param("excludeFinished") Boolean excludeFinished);
}