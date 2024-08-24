package com.yusjade.libms.service;

import com.yusjade.libms.dao.BookInventoryMapper;
import com.yusjade.libms.dao.BookMapper;
import com.yusjade.libms.pojo.Book;
import com.yusjade.libms.pojo.BookInventory;
import jakarta.annotation.Resource;
import java.lang.management.LockInfo;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookService implements BaseService<Book, Book> {

  @Resource
  BookMapper bookMapper;
  @Resource
  BookInventoryMapper bookInventoryMapper;

  public List<Book> listBookSelective(Long bookId, Long inventoryId, Boolean isBorrowed,
      Boolean isDiscarded) {
    return bookMapper.selectByParamSelective(bookId, inventoryId, isBorrowed, isDiscarded);
  }

  public Book getBookById(Long id) {
    return bookMapper.selectByPrimaryKey(id);
  }

  @Override
  public List<Book> list(Book param) {
    return bookMapper.selectByParamSelective(param.getBookId(), param.getInventoryId(),
        param.getIsBorrowed(), param.getIsDiscarded());
  }

  public Integer removeBook(Long id) {
    Book record = bookMapper.selectByPrimaryKey(id);
    if (record == null) {
      return 0;
    }
    if (record.getInventoryId() != null) {
      // 更新书库数量
      BookInventory inventory = bookInventoryMapper.selectByPrimaryKey(record.getInventoryId());
      inventory.setQuantity(inventory.getQuantity() - 1);
      bookInventoryMapper.updateByPrimaryKeySelective(inventory);
    }
    return bookMapper.deleteByPrimaryKey(id);
  }

  public Long saveBook(Book record) {
    record.setBookId(null);
    record.setIsBorrowed(false);
    record.setIsDiscarded(false);
    if (bookMapper.insert(record) == 1) {
      // 更新书库数量
      BookInventory inventory = bookInventoryMapper.selectByPrimaryKey(record.getInventoryId());
      inventory.setQuantity(inventory.getQuantity() + 1);
      bookInventoryMapper.updateByPrimaryKeySelective(inventory);
      return record.getBookId();
    }
    return 0L;
  }

  public Integer updateBook(Book record) {
    if (record.getBookId() == null || bookMapper.selectByPrimaryKey(record.getBookId()) == null) {
      return 0;
    }
    return bookMapper.updateByPrimaryKeySelective(record);
  }
}