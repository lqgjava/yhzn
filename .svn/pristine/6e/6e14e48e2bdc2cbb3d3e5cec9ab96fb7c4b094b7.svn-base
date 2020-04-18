package com.yhzn.service.security;


import java.util.List;
import java.util.Set;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.security.Permission;


public interface PermissionService {

    /**
     * 根据用户id查询权限集合
     * @param userId
     * @return set
     */
    Set<String> findPermsByUserId(String userId);

    /**
     * 查询全部权限
     * @param status
     * @return list
     */
    List<Permission> selectAll(Integer status);

    /**
     * 查询全部菜单
     * @param status
     * @return list
     */
    List<Permission> selectAllMenuName(Integer status);

    /**
     * 根据用户id查询权限集合
     * @param userId
     * @return list
     */
    List<Permission> selectMenuByUserId(String userId);

    /**
     * 插入权限
     * @param permission
     * @return int
     */
    int insert(Permission permission);

    /**
     * 根据权限id更新状态
     * @param permissionId
     * @param status
     * @return int
     */
    int updateStatus(String id, Integer status);

    /**
     * 根据权限id查询权限
     * @param permissionId
     * @return permission
     */
    Permission findByPermissionId(String permissionId);

    /**
     * 根据id查询权限
     * @param id
     * @return permission
     */
    Permission findById(String id);

    /**
     * 更新权限
     * @param permission
     * @return int
     */
    int updateById(Permission permission);

    /**
     * 查询子权限条数
     * @param permissionId
     * @return int
     */
    int selectSubPermsByParentId(String id);

	List<Permission> selectAllMenu();

	List<Permission> selectAreaByParent(String id);

	List<Permission> selectButtonByParent(String id);
}
