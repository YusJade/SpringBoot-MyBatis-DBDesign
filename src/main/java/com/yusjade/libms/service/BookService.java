package com.yusjade.libms.service;

import com.yusjade.libms.dao.BookMapper;
import com.yusjade.libms.pojo.Book;
import jakarta.annotation.Resource;
import java.lang.management.LockInfo;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookService implements BaseService<Book, Book> {

  @Resource
  BookMapper bookMapper;

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
    return bookMapper.deleteByPrimaryKey(id);
  }

  public Long saveBook(Book record) {
    if (bookMapper.insert(record) == 1) {
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