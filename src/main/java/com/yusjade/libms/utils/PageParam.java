package com.yusjade.libms.utils;

import com.github.pagehelper.IPage;
import lombok.Data;
import org.apache.ibatis.annotations.Param;

@Data
public class PageParam<T> implements IPage {

  /**
   *  分页大小，默认为 10
   */
  private Integer pageSize = 10;
  /**
   * 页码，默认为 1
   */
  private Integer pageNum = 1;
  /**
   * 排序方式
   */
  private String orderBy;
  /**
   * 查询参数
   */
  private T param;

  PageParam<T> setOrderBy(String orderBy) {
    this.orderBy = orderBy;
    return this;
  }
}
