package com.yusjade.libms.pojo;

import java.util.Date;
import lombok.Data;

/**
 * tb_inventory_record
 * @author 19179
 * @date yyyy-MM-dd HH:mm:ss
 */
@Data
public class InventoryRecord {
    /**
     * 库存记录 id
     */
    private Long recordId;

    /**
     * 创建该记录的管理员 id
     */
    private Long createdBy;

    /**
     * 库存 id
     */
    private Long inventoryId;

    /**
     * 创建日期
     */
    private Date createdAt;

    /**
     * 出入库数目
     */
    private Integer quantity;

    /**
     * 出入库说明
     */
    private String note;

    public InventoryRecord(Long recordId, Long createdBy, Long inventoryId, Date createdAt, Integer quantity, String note) {
        this.recordId = recordId;
        this.createdBy = createdBy;
        this.inventoryId = inventoryId;
        this.createdAt = createdAt;
        this.quantity = quantity;
        this.note = note;
    }

    public InventoryRecord() {
        super();
    }
}