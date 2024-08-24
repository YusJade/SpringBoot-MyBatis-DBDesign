package com.yusjade.libms.controller;

import com.yusjade.libms.pojo.Admin;
import com.yusjade.libms.service.AdminService;
import com.yusjade.libms.utils.Response;
import com.yusjade.libms.utils.ResponseCode;
import jakarta.annotation.Resource;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

  @Resource
  AdminService adminService;

  @PostMapping("/login")
  Response<Long> Login(@RequestBody Map<String, String> requestBody) {
    Long res = adminService.login(requestBody);
    if (res == 0) {
      return new Response<>(ResponseCode.ACCOUNT_NOT_FOUND.getCode(), "账户不存在", 0L);
    }
    if (res > 0) {
      return new Response<>(ResponseCode.SUCCESS.getCode(), "登录成功", res);
    }
    if (res == -1) {
      return new Response<>(ResponseCode.PASSWORD_ERROR.getCode(), "密码错误", res);
    }
    return new Response<>(ResponseCode.ERROR.getCode(), "未知错误", 0L);
  }

  @GetMapping("/{id}")
  Response<Admin> queryUserInfo(@PathVariable Long id) {
    Admin target = adminService.query(id);
    if (target == null) {
      return new Response<>(ResponseCode.ACCOUNT_NOT_FOUND.getCode(), "账户不存在", null);
    }
    return new Response<>(ResponseCode.SUCCESS.getCode(), "查询成功", target);
  }
}
