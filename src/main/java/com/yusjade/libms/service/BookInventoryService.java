package com.yusjade.libms.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yusjade.libms.dao.BookInventoryMapper;
import com.yusjade.libms.pojo.BookInventory;
import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookInventoryService {
  @Resource
  private BookInventoryMapper bookInventoryMapper;

  public PageInfo<BookInventory> page(Map<String, Object> param) {
    Integer pageNum = (Integer) param.getOrDefault("pageNum", 1);
    Integer pageSize = (Integer) param.getOrDefault("pageSize", 10);
    return new PageInfo<BookInventory>(list(param));
//    return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> list(param));
  }

  public List<BookInventory> list(Map<String, Object> param) {
    String title = (String) param.get("title");
    String author = (String) param.get("author");
    String publisher = (String) param.get("publisher");
    String keyword = (String) param.get("keyword");
    List<BookInventory> list = new ArrayList<>();
    return bookInventoryMapper.selectByParamSelective(title, author, publisher, keyword);
  }

  public BookInventory getById(Long id) {
    return bookInventoryMapper.selectByPrimaryKey(id);
  }

  public int remove(Long id) {
    if (bookInventoryMapper.selectByPrimaryKey(id) == null) {
      return 0;
    }
    return bookInventoryMapper.deleteByPrimaryKey(id);
  }

  public int update(BookInventory record) {
    if (record.getInventoryId() == null ||
        bookInventoryMapper.selectByPrimaryKey(record.getInventoryId()) == null) {
      return 0;
    }
    return bookInventoryMapper.updateByPrimaryKeySelective(record);
  }

  public Long save(BookInventory record) {
    if (record == null) {
      return 0L;
    }
    record.setInventoryId(null);
    if (bookInventoryMapper.insert(record) == 0) {
      return 0L;
    }
    return record.getInventoryId();
  }
}
