package com.yusjade.libms.dao;

import com.yusjade.libms.pojo.InventoryRecord;
import java.util.Date;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface InventoryRecordMapper {
    @Delete({
        "delete from tb_inventory_record",
        "where record_id = #{recordId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long recordId);

    @Insert({
        "insert into tb_inventory_record (record_id, created_by, ",
        "inventory_id, created_at, ",
        "quantity, note)",
        "values (#{recordId,jdbcType=BIGINT}, #{createdBy,jdbcType=BIGINT}, ",
        "#{inventoryId,jdbcType=BIGINT}, #{createdAt,jdbcType=TIMESTAMP}, ",
        "#{quantity,jdbcType=INTEGER}, #{note,jdbcType=VARCHAR})"
    })
    int insert(InventoryRecord record);

    @InsertProvider(type=InventoryRecordSqlProvider.class, method="insertSelective")
    int insertSelective(InventoryRecord record);

    @Select({
        "select",
        "record_id, created_by, inventory_id, created_at, quantity, note",
        "from tb_inventory_record",
        "where record_id = #{recordId,jdbcType=BIGINT}"
    })
    @ConstructorArgs({
        @Arg(column="record_id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="created_by", javaType=Long.class, jdbcType=JdbcType.BIGINT),
        @Arg(column="inventory_id", javaType=Long.class, jdbcType=JdbcType.BIGINT),
        @Arg(column="created_at", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="quantity", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="note", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    InventoryRecord selectByPrimaryKey(Long recordId);

    @UpdateProvider(type=InventoryRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(InventoryRecord record);

    @Update({
        "update tb_inventory_record",
        "set created_by = #{createdBy,jdbcType=BIGINT},",
          "inventory_id = #{inventoryId,jdbcType=BIGINT},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP},",
          "quantity = #{quantity,jdbcType=INTEGER},",
          "note = #{note,jdbcType=VARCHAR}",
        "where record_id = #{recordId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(InventoryRecord record);
}