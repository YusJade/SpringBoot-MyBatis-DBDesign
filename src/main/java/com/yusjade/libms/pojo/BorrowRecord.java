package com.yusjade.libms.pojo;

import java.util.Date;
import lombok.Data;

/**
 * tb_borrow_record
 * @author 19179
 * @date yyyy-MM-dd HH:mm:ss
 */
@Data
public class BorrowRecord {
    /**
     * 借阅记录 id
     */
    private Long recordId;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 图书 id
     */
    private Long bookId;

    /**
     * 借阅日期
     */
    private Date borrowDate;

    /**
     * 理应归还日期
     */
    private Date oughtReturnDate;

    /**
     * 实际归还日期
     */
    private Date actualReturnDate;

    public BorrowRecord(Long recordId, Long userId, Long bookId, Date borrowDate, Date oughtReturnDate, Date actualReturnDate) {
        this.recordId = recordId;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.oughtReturnDate = oughtReturnDate;
        this.actualReturnDate = actualReturnDate;
    }

    public BorrowRecord() {
        super();
    }
}