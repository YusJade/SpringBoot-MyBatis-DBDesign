package com.yusjade.libms.utils;

import lombok.Getter;

@Getter
public class Response<T> {

  int code;
  String msg;
  T data;

  public Response(int code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }
}
