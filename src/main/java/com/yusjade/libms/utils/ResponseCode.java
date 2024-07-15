package com.yusjade.libms.utils;

import lombok.Getter;

@Getter
public enum ResponseCode {

  // 账户相关： 登录成功、登录失败（账户不存在、密码错误）
  ACCOUNT_NOT_FOUND(0), PASSWORD_ERROR(-1),
  // 通用
  SUCCESS(1), ERROR(-100);

  final int code;

  ResponseCode(int code) {
    this.code = code;
  }
}
