package com.yusjade.libms.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.yusjade.libms.pojo.BookInventory;
import com.yusjade.libms.service.BookInventoryService;
import com.yusjade.libms.utils.Response;
import com.yusjade.libms.utils.ResponseCode;
import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book-inventory")
public class BookInventoryController {

  @Resource
  private BookInventoryService bookInventoryService;

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
    param.put("page_num", pageNum);
    param.put("page_size", pageSize);
    return new Response<>(ResponseCode.SUCCESS.getCode(), "分页查询成功",
        bookInventoryService.page(param));

  }

}