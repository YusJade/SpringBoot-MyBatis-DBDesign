package com.yusjade.libms.utils;

import lombok.Getter;

@Getter
public class Response<T> {

  int code;
  String msg;
  T data;

  public static <T> Response<T> success(String msg, T data) {
    return new Response<>(ResponseCode.SUCCESS.getCode(), msg, data);
  }

  public static <T> Response<T> error(String msg) {
    return new Response<>(ResponseCode.ERROR.getCode(), msg, null);
  }

  public static <T> Response<T> error(String msg, int code) {
    return new Response<>(code, msg, null);
  }

  public Response(int code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }
}
