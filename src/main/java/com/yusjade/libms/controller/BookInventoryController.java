package com.yusjade.libms.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.yusjade.libms.pojo.BookInventory;
import com.yusjade.libms.service.BookInventoryService;
import com.yusjade.libms.utils.Response;
import com.yusjade.libms.utils.ResponseCode;
import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book-inventory")
public class BookInventoryController {

  @Resource
  private BookInventoryService bookInventoryService;

  @GetMapping("/{id}")
  public Response<BookInventory> queryInventoryById(@PathVariable Long id) {
    try {
      BookInventory res = bookInventoryService.getById(id);
      if (res == null) {
        return new Response<>(ResponseCode.ERROR.getCode(), "查询失败", null);
      }
      return new Response<>(ResponseCode.SUCCESS.getCode(), "查询成功", res);
    } catch (Exception e) {
      return new Response<>(ResponseCode.ERROR.getCode(), "查询失败", null);
    }
  }

  @GetMapping("/paged")
  public Response<PageInfo<BookInventory>> queryInventoryPaged(
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String author,
      @RequestParam(required = false) String publisher,
      @RequestParam(required = false) String keyword,
      @RequestParam(name = "page_num", required = false) Integer pageNum,
      @RequestParam(name = "page_size", required = false) Integer pageSize) {
    Map<String, Object> param = new HashMap<>();
    param.put("title", title);
    param.put("author", author);
    param.put("publisher", publisher);
    param.put("keyword", keyword);
    param.put("pageNum", pageNum);
    param.put("pageSize", pageSize);
    return new Response<>(ResponseCode.SUCCESS.getCode(), "分页查询成功",
        bookInventoryService.page(param));

  }

  @GetMapping("/list")
  public Response<List<BookInventory>> queryInventoryList(
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String author,
      @RequestParam(required = false) String publisher,
      @RequestParam(required = false) String keyword) {
    Map<String, Object> param = new HashMap<>();
    param.put("title", title);
    param.put("author", author);
    param.put("publisher", publisher);
    param.put("keyword", keyword);
    return new Response<>(ResponseCode.SUCCESS.getCode(), "列表查询成功",
        bookInventoryService.list(param));

  }

  @PutMapping("/{id}")
  Response<Integer> modifyInfo(@PathVariable Long id, @RequestBody BookInventory record) {
    try {
      if (bookInventoryService.update(record) == 0) {
        return new Response<>(ResponseCode.ERROR.getCode(), "修改失败", 0);
      }
      return new Response<>(ResponseCode.SUCCESS.getCode(), "修改成功", 1);
    } catch (Exception e) {
      return new Response<>(ResponseCode.ERROR.getCode(), "修改失败", null);
    }
  }

  @DeleteMapping("/{id}")
  Response<Integer> deleteInventory(@PathVariable Long id) {
    try {
      if (bookInventoryService.remove(id) == 0) {
        return new Response<>(ResponseCode.ERROR.getCode(), "删除失败", 0);
      }
      return new Response<>(ResponseCode.SUCCESS.getCode(), "删除成功", 1);
    } catch (Exception e) {
      return new Response<>(ResponseCode.ERROR.getCode(), "删除失败", null);
    }
  }

  @PostMapping
  Response<Long> addInventory(@RequestBody BookInventory record) {
    try {
      record.setInventoryId(null);
      record.setQuantity(0L);
      bookInventoryService.save(record);
      if (record.getInventoryId() == null) {
        return new Response<>(ResponseCode.ERROR.getCode(), "添加失败", null);
      }
      return new Response<>(ResponseCode.SUCCESS.getCode(), "添加成功", record.getInventoryId());
    } catch (Exception e) {
      return new Response<>(ResponseCode.ERROR.getCode(), "添加失败", null);
    }
  }
}