package com.yusjade.libms.service;

import com.yusjade.libms.dao.AdminMapper;
import com.yusjade.libms.pojo.Admin;
import com.yusjade.libms.pojo.User;
import com.yusjade.libms.utils.Response;
import com.yusjade.libms.utils.ResponseCode;
import jakarta.annotation.Resource;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class AdminService {

  @Resource
  AdminMapper adminMapper;

  public long login(Map<String, String> requestBody) {
    String username = requestBody.get("username");
    String password = requestBody.get("password");
    if (username == null) {
      return 0L; // 账户不存在
    }

    Admin adminLogin = adminMapper.selectByUsername(username);
    if (adminLogin == null) {
      return 0L; // 账户不存在
    }
    if (adminLogin.getPassword().equals(password)) {
      return adminLogin.getAdminId(); // 登录成功
    }
    return -1L; // 密码错误
  }

  public Admin query(Long id) {
    return adminMapper.selectByPrimaryKey(id);
  }

}
