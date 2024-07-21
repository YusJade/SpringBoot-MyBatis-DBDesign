package com.yusjade.libms.service;

import com.github.pagehelper.PageInfo;
import com.yusjade.libms.dao.BookCategoryMapper;
import com.yusjade.libms.pojo.BookCategory;
import com.yusjade.libms.utils.PageParam;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class CategoryService implements BaseService<String, BookCategory> {
  @Resource
  BookCategoryMapper categoryMapper;

  /**
   * 新增图书类别
   * @param record 新记录，id 留空
   * @return 记录 id（为 null 则保存失败）
   */
  public Long save(BookCategory record) {
    record.setCategoryId(null);
    categoryMapper.insert(record);
    return record.getCategoryId();
  }

  public int remove(Long id) {
    return categoryMapper.deleteByPrimaryKey(id);
  }

  public int update(BookCategory record) {
    return categoryMapper.updateByPrimaryKeySelective(record);
  }

  public BookCategory getById(Long id) {
    return categoryMapper.selectByPrimaryKey(id);
  }

  /**
   * 匹配关键词，获取分类列表
   * @param keyword 查询参数：关键词
   * @return 分类列表
   */
  @Override
  public List<BookCategory> list(String keyword) {
    return categoryMapper.selectByKeyword(keyword);
  }
}
