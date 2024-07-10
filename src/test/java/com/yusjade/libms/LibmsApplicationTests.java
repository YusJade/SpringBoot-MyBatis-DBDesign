package com.yusjade.libms;

import com.yusjade.libms.dao.AdminMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@MapperScan("com.yusjade.libms.dao")
@SpringBootTest
class LibmsApplicationTests {

  @Resource
  AdminMapper adminMapper;
  @Test
  void contextLoads() {
    log.info("execute sql: " + adminMapper.selectByPrimaryKey(1L).toString());
  }

}
