package com.yhzn.dao.common;

import org.apache.ibatis.annotations.Param;

import com.yhzn.model.common.CommonPhoto;

/**
 * 通用图片接口Dao
 * @author liany
 *
 */
public interface CommonPhotoDao {

	/**
	 * 插入图片信息
	 * @param commonPhoto
	 */
	public void insertCommonPhotoInfo(CommonPhoto commonPhoto);
	
	/**
	 * 删除图片信息
	 * @param id
	 */
	public void deleteCommonPhotoInfo(String id);
	
	/**
	 * 查询图片信息
	 * @param id
	 * @return
	 */
	public CommonPhoto findCommonPhotoById(String id);
/**
 * 根据lindid查询图片信息
 * @param parentId
 * @return
 */
	public CommonPhoto findCommonPhotoByparentId(@Param("parentId") String parentId);
	/**
	 * 根据linkids删除图片信息
	 * @param id
	 */
public void deleteCommonPhotoInfoByLinkId(String id);
}
