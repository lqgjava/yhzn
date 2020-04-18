package com.yhzn.service.impl.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.common.util.ConfigUtil;
import com.yhzn.dao.common.AttachmentDao;
import com.yhzn.model.common.Attachment;
import com.yhzn.service.common.AttachmentService;

@Service
public class AttachmentServiceImpl implements AttachmentService {
	@Resource
	private AttachmentDao attachmentDao;
	String uploadDir = ConfigUtil.getConfig("upload.dir");
	/**
	 * 删除附件
	 * 
	 * @param attId
	 * @return
	 * @throws FileNotFoundException
	 */
	@Override
	public boolean deleteFileById(String id) throws FileNotFoundException {
		Attachment att = attachmentDao.QueryOne(id);	
		if (att == null) {
			throw new FileNotFoundException("附件不存在！");
		}
		attachmentDao.delete(att.getId());
		File f = new File(uploadDir, att.getFilePath());
		if (!f.exists()) {
			throw new FileNotFoundException("文件不存在！");
		}
		// 删除
		f.deleteOnExit();
		
		return true;
	}
	@Override
	public List<Attachment> QueryFileByParentId(String parentId,PageBounds bounds) {
		
		return attachmentDao.QueryFileByParentId(parentId,bounds);
	}
	@Override
	public Attachment get(String id) {
		
		return  attachmentDao.QueryOne(id);
	}
	/**
	 * 删除附件
	 * 
	 * @param attId
	 * @return
	 * @throws FileNotFoundException
	 */
	@Override
	public boolean deleteFileByParentId(String parentId)  {
		List<Attachment> attList = attachmentDao.QueryOneFileByParentId(parentId);
		for (Attachment att : attList) {
			if (att == null) {
				try {
					throw new FileNotFoundException("附件不存在！");
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}
			File f = new File(uploadDir, att.getFilePath());
			// 删除
			f.deleteOnExit();
			attachmentDao.delete(att.getId());
		}
		
		return true;
	}
}
