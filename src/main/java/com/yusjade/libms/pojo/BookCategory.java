package com.yusjade.libms.pojo;

import lombok.Data;

/**
 * tb_book_category
 * @author 19179
 * @date yyyy-MM-dd HH:mm:ss
 */
@Data
public class BookCategory {
    /**
     * 图书类别 id
     */
    private Long categoryId;

    /**
     * 图书类别名
     */
    private String name;

    public BookCategory(Long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public BookCategory() {
        super();
    }
}