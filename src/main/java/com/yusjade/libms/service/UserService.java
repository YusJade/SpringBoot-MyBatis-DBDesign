package com.yusjade.libms.service;

import com.yusjade.libms.dao.PersonalizedInfoMapper;
import com.yusjade.libms.dao.UserMapper;
import com.yusjade.libms.pojo.PersonalizedInfo;
import com.yusjade.libms.pojo.User;
import jakarta.annotation.Resource;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户服务层，提供登录、注册等功能。
 */
@Slf4j
@Service
public class UserService {

  @Resource
  UserMapper userMapper;
  @Resource
  PersonalizedInfoMapper personalizedInfoMapper;

  /**
   * 用户登录
   *
   * @param requestBody 请求体
   * @return 0:账户不存在 | n(>0):返回用户id，登陆成功 | -1:密码错误
   */
  public Long Login(Map<String, String> requestBody) {
    String username = requestBody.get("username");
    String password = requestBody.get("password");
    if (username == null) {
      return 0L; // 账户不存在
    }

    User targetUser = userMapper.selectByUsername(username);
    if (targetUser == null) {
      return 0L; // 账户不存在
    }
    if (targetUser.getPassword().equals(password)) {
      return targetUser.getUserId(); // 登录成功
    }
    return -1L; // 密码错误
  }

  /**
   * 用户注册
   * @param requestBody 请求体
   * @return 0: 注册失败 | n(n>0): 新用户的id，注册成功
   */
  public Long Register(Map<String, String> requestBody) {
    String username = requestBody.get("username");
    String password = requestBody.get("password");
    String name = requestBody.get("name");
    String gender = requestBody.get("gender");
    String email = requestBody.get("email");
    String phone = requestBody.get("phone");
    User newUser = new User(0L, null, username, password, gender, name, email,
        phone, null);
    // @Insert 注解在方法返回1表示插入成功
    return userMapper.insert(newUser) == 1 ? newUser.getUserId() : 0;
  }

  public User getUserByUsername(String username) {
     return userMapper.selectByUsername(username);
  }

  public User getUserById(Long id) {
    return userMapper.selectByPrimaryKey(id);
  }

  /**
   * 修改用户基本信息
   * @param record 被修改的信息
   * @return 1: 修改成功 | 0: 修改失败
   * todo 24/7/11: 允许修改 username 并检查是否重名
   */
  public Integer updateUserInfo(User record) {
    if (userMapper.selectByPrimaryKey(record.getUserId()) == null) {
      return 0;
    }
    record.setUsername(null);
    record.setCreatedAt(null);
    record.setPassword(null);
    record.setPermissionName(null);
    return userMapper.updateByPrimaryKeySelective(record);
  }

  /**
   * 修改用户头像
   * @param userId
   * @param avatar
   * @return
   * todo 24/7/10: 检测用户是否存在
   * todo 24/7/10: 限制图片大小
   */
  public Integer updateAvatar(Long userId, byte[] avatar) {
    PersonalizedInfo record = new PersonalizedInfo();
    record.setUserId(userId);
    record.setAvatar(avatar);
    return personalizedInfoMapper.updateByPrimaryKeySelective(record);
  }

  /**
   * 修改用户签名
   * @param userId
   * @param signature
   * @return
   */
  public Integer updateSignature(Long userId, String signature) {
    PersonalizedInfo record = new PersonalizedInfo();
    record.setUserId(userId);
    record.setSignature(signature);
    return personalizedInfoMapper.updateByPrimaryKeySelective(record);
  }

//  public Long getIdByUsername(String username) {
//    User user = userMapper.selectByUsername(username);
//    if (user == null) {
//      return 0L;
//    }
//    return user.getUserId();
//  }

}
