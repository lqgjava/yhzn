package com.yhzn.service.impl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.dao.security.PermissiondAO;
import com.yhzn.model.security.Permission;
import com.yhzn.service.security.PermissionService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissiondAO permissionMapper;
    @Override
	public List<Permission> selectAllMenu() {
		
		return permissionMapper.selectAllMenu();
	}
	@Override
	public List<Permission> selectAreaByParent(String id) {
		return permissionMapper.selectAreaByParent(id);
	}

    @Override
    public Set<String> findPermsByUserId(String userId) {
        return permissionMapper.findPermsByUserId(userId);
    }

    @Override
    public List<Permission> selectAll(Integer status) {
        return permissionMapper.selectAllPerms(status);
    }

    @Override
    public List<Permission> selectAllMenuName(Integer status) {
        return permissionMapper.selectAllMenuName(status);
    }

    @Override
    public List<Permission> selectMenuByUserId(String userId) {
        return permissionMapper.selectMenuByUserId(userId);
    }

    @Override
    public int insert(Permission permission) {
        permission.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        permission.setStatus(1);
        if(permission.getParentId()==null) {
        	permission.setParentId("");
        }
      //  permission.setCreateTime(date);
      //  permission.setUpdateTime(date);
        System.out.println(permission.toString());
        return permissionMapper.insert(permission);
    }

    @Override
    public int updateStatus(String  id,Integer status) {
        return permissionMapper.updateStatusById(id,status);
    }

    @Override
    public Permission findByPermissionId(String permissionId) {
        return permissionMapper.selectByPermissionId(permissionId);
    }

    @Override
    public Permission findById(String id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(Permission permission) {
        return permissionMapper.updateById(permission);
    }

    @Override
    public int selectSubPermsByParentId(String id) {
        return permissionMapper.selectSubPermsByParentId(id);
    }
	@Override
	public List<Permission> selectButtonByParent(String id) {
		// TODO Auto-generated method stub
		return permissionMapper.selectButtonByParent(id);
	}

	
}
