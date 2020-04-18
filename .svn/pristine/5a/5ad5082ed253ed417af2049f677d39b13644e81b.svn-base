package com.yhzn.service.common;

import java.io.FileNotFoundException;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.common.Attachment;

public interface AttachmentService {
	boolean deleteFileById(String id) throws FileNotFoundException;
	List<Attachment> QueryFileByParentId(String parentId, PageBounds bounds);
	Attachment get(String id);
	boolean deleteFileByParentId(String id) ;
}
