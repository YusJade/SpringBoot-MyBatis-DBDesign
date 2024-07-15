package com.yusjade.libms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yusjade.libms.utils.PageParam;
import java.util.List;

/**
 * 通用的分页接口 Service
 * @param <Param> 分页参数
 * @param <Result> 返回参数实体
 */
public interface BaseService<Param, Result> {

  default PageInfo<Result> page(PageParam<Param> param) {
    return PageHelper.startPage(param).doSelectPageInfo(() -> list(param.getParam()));
  }

  /**
   * 查询方法
   * @param param 查询参数
   * @return
   */
  List<Result> list(Param param);
}
