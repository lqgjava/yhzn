package com.yhzn.service.impl.projmanage;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.dao.common.AttachmentDao;
import com.yhzn.dao.common.CommonPhotoDao;
import com.yhzn.dao.projmanage.ScheduleDao;
import com.yhzn.model.common.Attachment;
import com.yhzn.model.common.CommonPhoto;
import com.yhzn.model.projmanage.ProjectLinkModel;
import com.yhzn.model.projmanage.Schedule;
import com.yhzn.model.security.User;
import com.yhzn.service.common.AttachmentService;
import com.yhzn.service.common.SysSequenceService;
import com.yhzn.service.projmanage.ScheduleService;

/**
 * 项目进度管理实现类
 * 
 * @author liany
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

	// 项目进度管理dao
	@Resource
	private ScheduleDao scheduleDao;
	@Resource
	private AttachmentDao attachmentDao;

	@Autowired
	private SysSequenceService sysSequenceService;
	
	@Autowired
	private AttachmentService attachmentService;

	/**
	 * 查询项目进度信息列表
	 * 
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	@Override
	public List<Schedule> queryScheduleList(PageBounds bounds, Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return scheduleDao.queryScheduleList(bounds, parameter);
	}

	/**
	 * 删除项目信息
	 * 
	 * @param id
	 */
	@Override
	public void deleteProjectById(String id) {
		// TODO Auto-generated method stub
		scheduleDao.deleteProjectById(id);
	}

	/**
	 * 新增项目信息
	 * 
	 * @param schedule
	 * @param user
	 */
	@Override
	public void insertProjectInfo(Schedule schedule, User user) {
		// TODO Auto-generated method stub
		schedule.setProjNo("XM" + sysSequenceService.getNextNo("PROJECT", user));
		schedule.setId(UUID.randomUUID().toString().replaceAll("-", ""));// id
		schedule.setCreateName(user.getTrueName());// 创建人姓名

		scheduleDao.insertProjectInfo(schedule);
	}

	/**
	 * 修改项目信息
	 * 
	 * @param schedule
	 * @param user
	 */
	@Override
	public void updateProjectInfo(Schedule schedule, User user) {
		// TODO Auto-generated method stub
		schedule.setModifyName(user.getTrueName());// 修改人姓名
		scheduleDao.updateProjectInfo(schedule);
	}

	/**
	 * 根据id查询项目信息
	 * 
	 * @param id
	 */
	@Override
	public Schedule queryScheduleById(String id) {

		return scheduleDao.queryScheduleById(id);
	}

	/**
	 * 删除项目("前期")
	 */
	@Override
	public void deleteProjectByIds(String[] ids) {

		scheduleDao.deleteProjectByIds(ids);
	}

	@Override
	public List<ProjectLinkModel> linkdetails(PageBounds bounds, String id) {

		return scheduleDao.linkdetails(bounds, id);
	}

	@Override
	public void insertLinkInfo(ProjectLinkModel changeLink, List<Attachment>list) {
		changeLink.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		for (Attachment attachment : list) {
			attachment.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			attachment.setParentId(changeLink.getId());
			attachmentDao.insertAtt(attachment);
			
		}
		scheduleDao.insertProjectLinkInfo(changeLink);

	}


	 @Override
	 public void editLinkInfo(ProjectLinkModel link, List<Attachment> list) { 
		 for (Attachment attachment : list) {
				attachment.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				attachment.setParentId(link.getId());
				attachmentDao.insertAtt(attachment);
				
			}
	 scheduleDao.editLinkInfo(link); 
	 }
	 

	@Override
	public void deleteLinkById(String id) throws FileNotFoundException {
		  // 根据id数组删除沟通记录
		  scheduleDao.deletelinkById(id); // 根据linkId删除图片信息
		  attachmentService.deleteFileByParentId(id);// 删除附件
		 
	}

	@Override
	public List<Schedule> queryEarlyList(PageBounds bounds, Schedule schedule) {
		// TODO Auto-generated method stub
		return scheduleDao.queryEarlyList(bounds, schedule);
	}

	@Override
	public int updateProjectStatusById(String id,int status) {
		
		return scheduleDao.updateProjectStatusById(id,status);
	}

	@Override
	public void hideProjectByIds(String[] ids) {
		scheduleDao.hideProjectByIds(ids);
	}

	@Override
	public boolean updateStatus(String id, String zt) {
		
		return scheduleDao.updateStatus(id,zt);
	}

}
