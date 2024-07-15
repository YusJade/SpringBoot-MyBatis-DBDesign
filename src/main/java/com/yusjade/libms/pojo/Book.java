package com.yusjade.libms.pojo;

import lombok.Data;


@Data
public class Book {
    /**
     * 图书 id
     */
    private Long bookId;

    /**
     * 所属库存 id
     */
    private Long inventoryId;

    /**
     * 是否被借阅
     */
    private Boolean isBorrowed;

    /**
     * 是否被弃用
     */
    private Boolean isDiscarded;

    public Book(Long bookId, Long inventoryId, Boolean isBorrowed, Boolean isDiscarded) {
        this.bookId = bookId;
        this.inventoryId = inventoryId;
        this.isBorrowed = isBorrowed;
        this.isDiscarded = isDiscarded;
    }

    public Book() {
        super();
    }
}