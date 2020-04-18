package com.yhzn.service.impl.common;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhzn.dao.common.CommonPhotoDao;
import com.yhzn.model.common.CommonPhoto;
import com.yhzn.service.common.CommonPhotoService;

/**
 * 通用图片接口实现类
 * @author liany
 *
 */
@Service
public class CommonPhotoServiceImpl implements CommonPhotoService {

	//通用图片dao
	@Resource
	private CommonPhotoDao commonPhotoDao;
	
	//查询图片信息
	@Override
	public CommonPhoto findCommonPhotoById(String id) {
		// TODO Auto-generated method stub
		return commonPhotoDao.findCommonPhotoById(id);
	}

	@Override
	public CommonPhoto findCommonPhotoByparentId(String id) {
		return commonPhotoDao.findCommonPhotoByparentId(id);
	}

}
