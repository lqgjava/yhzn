package com.yhzn.dao.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.common.Attachment;

public interface AttachmentDao {
	/**
	 * 新增附件
	 * 
	 * @param att
	 */
	void insertAtt(Attachment att);

	/**
	 * 查找附件
	 * 
	 * @param parentIds
	 * @return
	 */
	Attachment QueryOne(@Param("id") String id);

	/**
	 * 删除附件
	 * 
	 * @param id
	 */

	void delete(@Param("id") String id);



	/**
	 * 根据附件的parentId查找附件
	 * 
	 * @param parentId
	 * @param bounds
	 * @return
	 */
	List<Attachment> QueryFileByParentId(@Param("parentId") String parentId, PageBounds bounds);

	List<String> queryIdByParentId(String detailId);

	List<Attachment> QueryOneFileByParentId(@Param("parentId") String parentId);

	void deleteByParentId(@Param("id") String id);

}
