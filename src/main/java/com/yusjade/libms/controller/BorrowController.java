package com.yusjade.libms.controller;

import com.github.pagehelper.PageInfo;
import com.yusjade.libms.pojo.BorrowRecord;
import com.yusjade.libms.service.BorrowService;
import com.yusjade.libms.utils.PageParam;
import com.yusjade.libms.utils.Response;
import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/borrow")
public class BorrowController {

  @Resource
  BorrowService borrowService;

  @GetMapping("/{id}")
  Response<BorrowRecord> queryById(@PathVariable Long id) {
    try {
      BorrowRecord record = borrowService.getBorrowById(id);
      if (record == null) {
        return Response.error("查询失败");
      }
      return Response.success("查询成功", record);
    } catch (Exception e) {
      return Response.error("查询失败");
    }
  }

  @GetMapping("/list")
  Response<List<BorrowRecord>> queryRecordList(
      @RequestParam(required = false) Long recordId,
      @RequestParam(required = false) Long bookId,
      @RequestParam(required = false) Long userId,
      @RequestParam(required = false) Boolean excludeFinished) {
    try {
      Map<String, Object> param = new HashMap<>();
      param.put("recordId", recordId);
      param.put("bookId", bookId);
      param.put("userId", userId);
      param.put("excludeFinished", excludeFinished);
      return Response.success("查询成功", borrowService.list(param));
    } catch (Exception e) {
      log.warn(e.toString());
      return Response.error("查询失败");
    }
  }

  @GetMapping("/page")
  Response<PageInfo<BorrowRecord>> queryRecordPage(
      @RequestParam(required = false) Long recordId,
      @RequestParam(required = false) Long bookId,
      @RequestParam(required = false) Long userId,
      @RequestParam(required = false) Boolean excludeFinished,
      @RequestParam(required = false, defaultValue = "10") Integer pageSize,
      @RequestParam(required = false, defaultValue = "1") Integer pageNum) {
    try {
      Map<String, Object> param = new HashMap<>();
      param.put("recordId", recordId);
      param.put("bookId", bookId);
      param.put("userId", userId);
      param.put("excludeFinished", excludeFinished);
      PageParam<Map<String, Object>> pageParam = new PageParam<>();
      pageParam.setParam(param);
      pageParam.setPageSize(pageSize);
      pageParam.setPageNum(pageNum);
      return Response.success("查询成功", borrowService.page(pageParam));
    } catch (Exception e) {
      return Response.error("查询失败");
    }
  }

  /**
   * 添加借阅记录
   * @param record 借阅记录DTO（bookId,userId）
   * @return
   */
  @PostMapping
  Response<Long> add(@RequestBody BorrowRecord record) {
    try {
      Long recordId = borrowService.saveBorrow(record);
      if (recordId == 0) {
        return Response.error("借阅失败");
      }
      return Response.success("借阅成功", recordId);
    } catch (Exception e) {
      log.warn(e.toString());
      return Response.error("借阅失败");
    }
  }

  /**
   * 修改借阅记录，支持修改日期
   * @param id
   * @param record
   * @return
   */
  @PutMapping("/{id}")
  Response<Integer> modify(@PathVariable Long id, @RequestBody BorrowRecord record) {
    try {
      record.setRecordId(id);
      Integer code = borrowService.update(record);
      if (code == 1) {
        return Response.success("修改成功", code);
      }
      return Response.error("修改失败", code);
    } catch (Exception e) {
      return Response.error("修改失败");
    }
  }

  @PutMapping("/{id}/return")
  Response<Integer> returnBook(@PathVariable Long id) {
    try {
      Integer code = borrowService.returnBook(id);
      if (code == 0) {
        return Response.error("归还失败", code);
      }
      if (code == -1) {
        return Response.error("归还失败，已经逾期", code);
      }
      return Response.success("归还成功", code);
    } catch (Exception e) {
      return Response.error("归还失败");
    }
  }

  @PutMapping("/{id}/renew")
  Response<Integer> renewBook(@PathVariable Long id) {
    try {
      int code = borrowService.renewBorrow(id);
      if (code == 1) {
        return Response.success("续借成功", code);
      }
      if (code == 0) {
        return Response.error("续借失败");
      }
      if (code == -1) {
        return Response.error("已归还，无法续借");
      }
      if (code == -2) {
        return Response.error("已逾期，请先归还");
      }
      return Response.error("续借失败");
    } catch (Exception e) {
      log.warn(e.toString());
      return Response.error("续借失败");
    }
  }
}
