package com.yhzn.service.projmanage;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.common.Attachment;
import com.yhzn.model.projmanage.ProjectLinkModel;
import com.yhzn.model.projmanage.Schedule;
import com.yhzn.model.security.User;
import com.yhzn.model.supplier.SupplierModel;

/**
 * 项目进度管理接口
 * 
 * @author liany
 */
public interface ScheduleService {

	/**
	 * 查询项目进度信息列表
	 * 
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	public List<Schedule> queryScheduleList(PageBounds bounds, Map<String, Object> parameter);

	/**
	 * 删除项目信息
	 * 
	 * @param id
	 */
	public void deleteProjectById(String id);

	/**
	 * 新增项目信息
	 * 
	 * @param schedule
	 * @param user
	 */
	public void insertProjectInfo(Schedule schedule, User user);

	/**
	 * 项目前期管理列表
	 * 
	 * @param bounds
	 * @param schedule
	 * @return
	 */
	public List<Schedule> queryEarlyList(PageBounds bounds, Schedule schedule);

	/**
	 * 修改项目信息
	 * 
	 * @param schedule
	 * @param user
	 */
	public void updateProjectInfo(Schedule schedule, User user);

	/**
	 * 根据id查询项目信息
	 * 
	 * @param id
	 * @return
	 */
	public Schedule queryScheduleById(String id);

	/**
	 * 删除项目（前期）
	 * 
	 * @param ids
	 */
	public void deleteProjectByIds(String[] ids);

	/**
	 * 查询项目沟通记录
	 * 
	 * @param bounds
	 * @param id
	 * @return
	 */
	public List<ProjectLinkModel> linkdetails(PageBounds bounds, String id);

	/**
	 * 增加项目沟通记录
	 * 
	 * @param changeLink
	 * @param user
	 */
	public void insertLinkInfo(ProjectLinkModel link, List<Attachment> list);

	public void editLinkInfo(ProjectLinkModel link, List<Attachment> list);

	/**
	 * 删除项目沟通记录
	 * 
	 * @param ids
	 * @throws FileNotFoundException 
	 */
	public void deleteLinkById(String ids) throws FileNotFoundException;

	public int updateProjectStatusById(String id,int status);

	public void hideProjectByIds(String[] ids);

	public boolean updateStatus(String id, String zt);

}
