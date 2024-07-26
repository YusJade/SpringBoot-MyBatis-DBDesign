package com.yusjade.libms.service;

import com.yusjade.libms.dao.PermissionMapper;
import com.yusjade.libms.pojo.Permission;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PermissionService implements BaseService<String, Permission> {
 @Resource
 PermissionMapper permissionMapper;

  /**
   * 添加新的权限
   * @param record
   * @return -1: 权限名重复 0: 添加失败 1: 添加成功
   */
 public int savePermission(Permission record) {
   String name = record.getPermissionName();
   if (permissionMapper.selectByPrimaryKey(name) != null) {
      return -1;
   }
    return permissionMapper.insert(record);
  }

  public int removePermission(String name) {
    return permissionMapper.deleteByPrimaryKey(name);
  }

  public int updatePermission(Permission record) {
    return permissionMapper.updateByPrimaryKeySelective(record);
  }

  public Permission getPermissionByName(String name) {
    return permissionMapper.selectByPrimaryKey(name);
  }

  @Override
  public List<Permission> list(String n) {
    return permissionMapper.selectAll();
  }

}
