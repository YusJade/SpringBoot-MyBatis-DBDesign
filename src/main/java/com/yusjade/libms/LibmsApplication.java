package com.yusjade.libms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.yusjade.libms.dao")
@SpringBootApplication
public class LibmsApplication {

  public static void main(String[] args) {
    SpringApplication.run(LibmsApplication.class, args);
  }

}
