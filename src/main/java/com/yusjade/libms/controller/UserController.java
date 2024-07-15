package com.yusjade.libms.controller;

import com.yusjade.libms.pojo.User;
import com.yusjade.libms.service.UserService;
import com.yusjade.libms.utils.Response;
import com.yusjade.libms.utils.ResponseCode;
import jakarta.annotation.Resource;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {
  @Resource
  UserService userService;

  @PostMapping("/login")
  Response<Long> Login(@RequestBody Map<String, String> requestBody) {
    Long res = userService.Login(requestBody);
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

  @PostMapping("/register")
  Response<Long> Register(@RequestBody Map<String, String> requestBody) {
    Long res = userService.Register(requestBody);
    if (res > 0) {
      return new Response<>(ResponseCode.SUCCESS.getCode(), "注册成功", res);
    }
    return new Response<>(ResponseCode.ERROR.getCode(), "注册失败", 0L);
  }

  @GetMapping("/{id}")
  Response<User> queryUserInfo(@PathVariable Long id) {
    User target = userService.getUserById(id);
    if (target == null) {
      return new Response<>(ResponseCode.ACCOUNT_NOT_FOUND.getCode(), "账户不存在", null);
    }
    return new Response<>(ResponseCode.SUCCESS.getCode(), "查询成功", target);
  }

  @PutMapping("/{id}")
  Response<Long> modifyUserInfo(@PathVariable Long id, @RequestBody User infoModified) {
//    log.info("modify user info: " + infoModified.toString());
    infoModified.setUserId(id);
    Integer res = userService.updateUserInfo(infoModified);
    if (res == 1) {
      return new Response<>(ResponseCode.SUCCESS.getCode(),"修改成功", id);
    }
    return new Response<>(ResponseCode.ERROR.getCode(), "未知错误", -1L);
  }

  @GetMapping("/id/{username}")
  Response<Long> queryId(@PathVariable String username) {
    User user = userService.getUserByUsername(username);
    if (user == null) {
      return new Response<>(ResponseCode.ACCOUNT_NOT_FOUND.getCode(), "账户不存在", -1L);
    }
    return new Response<>(ResponseCode.SUCCESS.getCode(), "查询成功", user.getUserId());
  }

  @PutMapping("/permission/{id}")
  Response<Long> modifyPermission(@PathVariable Long id, @RequestBody String permissionName) {
    return new Response<>(ResponseCode.ERROR.getCode(), "未知错误", -1L);
  }
}
