package com.yusjade.libms.controller;

import com.github.pagehelper.PageInfo;
import com.yusjade.libms.pojo.BookCategory;
import com.yusjade.libms.service.CategoryService;
import com.yusjade.libms.utils.PageParam;
import com.yusjade.libms.utils.Response;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
  @Resource
  CategoryService categoryService;

  @GetMapping("/list")
  public Response<List<BookCategory>> queryListByKeyword(@RequestParam String keyword) {
    try {
      return Response.success("查询成功", categoryService.list(keyword));
    } catch (Exception e) {
      log.warn(e.toString());
      return Response.error("查询失败");
    }
  }

  @GetMapping("/{id}")
  public Response<BookCategory> query(@PathVariable Long id) {
    try {
      return Response.success("查询成功", categoryService.getById(id));
    } catch (Exception e) {
      log.warn(e.toString());
      return Response.error("查询失败");
    }
  }

  @GetMapping("/page")
  public Response<PageInfo<BookCategory>> queryPageByKeyword(
      @RequestParam String keyword,
      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
      @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum) {
    try {
      PageParam<String> pageParam = new PageParam<>();
      pageParam.setPageNum(pageNum);
      pageParam.setPageSize(pageSize);
      pageParam.setParam(keyword);
      return Response.success("查询成功", categoryService.page(pageParam));
    } catch (Exception e) {
      log.warn(e.toString());
      return Response.error("查询失败");
    }
  }

  @PostMapping
  public Response<Long> addCategory(@RequestBody BookCategory record) {
    try {
      Long code = categoryService.save(record);
      if (code == null) {
        return Response.error("添加失败");
      }
      return Response.success("添加成功", record.getCategoryId());
    } catch (Exception e) {
      log.warn(e.toString());
      return Response.error("添加失败");
    }
  }

  @DeleteMapping("/{id}")
  public Response<Integer> delete(@PathVariable Long id) {
    try {
      return Response.success("删除成功", categoryService.remove(id));
    } catch (Exception e) {
      log.warn(e.toString());
      return Response.error("删除失败");
    }
  }

  @PutMapping("/{id}")
  public Response<Integer> modify(@PathVariable Long id, @RequestBody BookCategory record) {
    try {
      record.setCategoryId(id);
      int code = categoryService.update(record);
      if (code == 0) {
        return Response.error("修改失败");
      }
      return Response.success("修改成功", code);
    } catch (Exception e) {
      log.warn(e.toString());
      return Response.error("修改失败");
    }
  }
}
