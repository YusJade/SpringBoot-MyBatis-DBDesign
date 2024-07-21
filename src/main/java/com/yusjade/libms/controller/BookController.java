package com.yusjade.libms.controller;

import com.yusjade.libms.pojo.Book;
import com.yusjade.libms.service.BookService;
import com.yusjade.libms.utils.Response;
import com.yusjade.libms.utils.ResponseCode;
import jakarta.annotation.Resource;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/book")
public class BookController {

  @Resource
  BookService bookService;

  @GetMapping("/list")
  Response<List<Book>> queryBook(
      @RequestParam(name = "bookId", required = false) Long bookId,
      @RequestParam(name = "inventoryId", required = false) Long inventoryId,
      @RequestParam(name = "isBorrowed", required = false) Boolean isBorrowed,
      @RequestParam(name = "isDiscarded", required = false) Boolean isDiscarded) {
    try {
//      List<Book> res = bookService.listBookSelective(bookId, inventoryId, isBorrowed, isDiscarded);
      List<Book> res = bookService.list(new Book(bookId, inventoryId, isBorrowed, isDiscarded));
      return new Response<>(ResponseCode.SUCCESS.getCode(), "查询成功", res);
    }
    catch (Exception e) {
      log.warn(e.toString());
      return new Response<>(ResponseCode.ERROR.getCode(), "未知错误", null);
    }
  }

  @DeleteMapping("/{id}")
  Response<Integer> deleteBook(@PathVariable Long id) {
    try {
      if (bookService.removeBook(id) == 1) {
        return new Response<>(ResponseCode.SUCCESS.getCode(), "删除成功", bookService.removeBook(id));
      }
      return new Response<>(ResponseCode.ERROR.getCode(), "图书不存在", 0);
    } catch (Exception e) {
      log.warn(e.toString());
      return new Response<>(ResponseCode.ERROR.getCode(), "删除失败", 0);
    }
  }

  @PutMapping("/{id}")
  Response<Integer> modifyBook(@PathVariable Long id, @RequestBody Book record) {
    record.setBookId(id);
    try {
      return new Response<>(ResponseCode.SUCCESS.getCode(), "修改成功", bookService.updateBook(record));
    } catch (Exception e) {
      log.warn(e.toString());
      return new Response<>(ResponseCode.ERROR.getCode(), "修改失败", null);
    }
  }

  @GetMapping("/{id}")
  Response<Book> queryBookById(@PathVariable Long id) {
    try {
      return new Response<>(ResponseCode.SUCCESS.getCode(), "查询成功", bookService.getBookById(id));
    } catch (Exception e) {
      log.warn(e.toString());
      return new Response<>(ResponseCode.ERROR.getCode(), "查询失败", null);
    }
  }

  @PostMapping
  Response<Long> addBook(@RequestBody Book record) {
    try {
      Long id = bookService.saveBook(record);
      if (id == 0) {
        return new Response<>(ResponseCode.ERROR.getCode(), "保存失败", null);
      }
      return new Response<>(ResponseCode.SUCCESS.getCode(), "保存成功", id);
    } catch (Exception e) {
      log.warn(e.toString());
      return new Response<>(ResponseCode.ERROR.getCode(), "保存失败", null);
    }
  }
}