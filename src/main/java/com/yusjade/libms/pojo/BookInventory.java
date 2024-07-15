package com.yusjade.libms.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * tb_book_inventory
 * @author 19179
 * @date yyyy-MM-dd HH:mm:ss
 */
@Data
public class BookInventory {
    /**
     * 图书库存 id
     */
    private Long inventoryId;

    /**
     * 图书类别 id
     */
    private Long categoryId;

    /**
     * 图书标题
     */
    private String bookTitle;

    /**
     * 图书作者
     */
    private String author;

    /**
     * 出版社
     */
    private String publisher;

    /**
     * 在库数目
     */
    private Long quantity;

    public BookInventory(Long inventoryId, Long categoryId, String bookTitle, String author, String publisher, Long quantity) {
        this.inventoryId = inventoryId;
        this.categoryId = categoryId;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
    }

    public BookInventory() {
        super();
    }
}