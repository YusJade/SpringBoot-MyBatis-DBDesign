package com.yusjade.libms.controller;

import com.github.pagehelper.PageInfo;
import com.yusjade.libms.pojo.Permission;
import com.yusjade.libms.service.PermissionService;
import com.yusjade.libms.utils.PageParam;
import com.yusjade.libms.utils.Response;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PermissionController {
  @Resource
  PermissionService permissionService;


  @PostMapping
  public Response<String> add(@RequestBody Permission record) {
    try {
      Integer code = permissionService.savePermission(record);
      if (code == -1) {
        return Response.error("权限名重复", code);
      }
      if (code == 0) {
        return Response.error("添加失败", code);
      }
      return Response.success("添加成功", record.getPermissionName());
    } catch (Exception e) {
      log.warn(e.toString());
      return Response.error("系统错误");
    }
  }

  @DeleteMapping("/{name}")
  public Response<Integer> delete(@PathVariable String name) {
    try {
      int code = permissionService.removePermission(name);
      if (code == 0) {
        return Response.error("删除失败");
      }
      return Response.success("删除成功", 1);
    } catch (Exception e) {
      log.warn(e.toString());
      return Response.error("删除失败");
    }
  }

  @PutMapping("/{name}")
  public Response<Integer> modify(@RequestBody Permission record, @PathVariable String name) {
    try {
      record.setPermissionName(name);
      int code = permissionService.updatePermission(record);
      if (code == 0) {
        return Response.error("修改失败");
      }
      return Response.success("修改成功", code);
    } catch (Exception e) {
      log.warn(e.toString());
      return Response.error("修改失败");
    }
  }

  @GetMapping("/{name}")
  public Response<Permission> queryByName(@PathVariable String name) {
    try {
      return Response.success("查询成功", permissionService.getPermissionByName(name));
    } catch (Exception e) {
      log.warn(e.toString());
      return Response.error("查询失败");
    }
  }

  @GetMapping("/list")
  public Response<List<Permission>> queryList() {
    try {
      return Response.success("查询成功", permissionService.list(""));
    } catch (Exception e) {
      log.warn(e.toString());
      return Response.error("查询失败");
    }
  }

  @GetMapping("/page")
  public Response<PageInfo<Permission>> queryPage(
      @RequestParam(defaultValue = "10") Integer pageSize,
      @RequestParam(defaultValue = "1") Integer pageNum) {
    try {
      PageParam<String> pageParam = new PageParam<>();
      pageParam.setPageSize(pageSize);
      pageParam.setPageNum(pageNum);
      return Response.success("查询成功", permissionService.page(pageParam));
    } catch (Exception e) {
      log.warn(e.toString());
      return Response.error("查询失败");
    }
  }
}
