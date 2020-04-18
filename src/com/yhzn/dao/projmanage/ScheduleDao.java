package com.yhzn.dao.projmanage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.projmanage.ProjectLinkModel;
import com.yhzn.model.projmanage.Schedule;

/**
 * 项目进度管理dao
 * 
 * @author liany
 */
public interface ScheduleDao {

	/**
	 * 查询项目进度信息列表
	 * 
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	 List<Schedule> queryScheduleList(PageBounds bounds, Map<String, Object> parameter);

	/**
	 * 删除项目信息
	 * 
	 * @param id
	 */
	 void deleteProjectById(String id);

	/**
	 * 新增项目信息
	 * 
	 * @param schedule
	 */
	 void insertProjectInfo(Schedule schedule);

	/**
	 * 修改项目信息
	 * 
	 * @param schedule
	 */
	 void updateProjectInfo(Schedule schedule);

	/**
	 * 根据id查询项目信息
	 * 
	 * @param id
	 * @return
	 */
	 Schedule queryScheduleById(@Param("id") String id);

	/**
	 * 删除项目信息(前期)
	 * 
	 * @param id
	 */
	 void deleteProjectByIds(@Param("ids") String[] ids);

	/**
	 * 查询项目沟通记录
	 * 
	 * @param bounds
	 * @param id
	 * @return
	 */
	 List<ProjectLinkModel> linkdetails(PageBounds bounds, @Param("id") String id);

	/**
	 * 新增项目沟通项目
	 * 
	 * @param changeLink
	 */
	 void insertProjectLinkInfo(ProjectLinkModel link);

	 void editLinkInfo(ProjectLinkModel link);

	 void deletelinkById(String id);

	/**
	 * 项目前期列表查询
	 * 
	 * @param bounds
	 * @param schedule
	 * @return
	 */
	 List<Schedule> queryEarlyList(PageBounds bounds, Schedule schedule);

	 int updateProjectStatusById(@Param("id") String id,@Param("status") int status);//根据id更改项目状态

	void hideProjectByIds(@Param("ids") String[] ids);

	boolean updateStatus(@Param("id") String id,@Param("zt") String zt);

}
