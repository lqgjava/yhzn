package com.yhzn.service.workbench;

import java.util.List;
import java.util.Map;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.security.User;
import com.yhzn.model.workbench.Task;
import com.yhzn.model.workbench.TaskReveice;

/**
 * 工作台管理接口
 * @author liany
 *
 */
public interface WorkbenchService {

	/**
	 * 查询任务信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	public List<Task> queryTaskList(PageBounds bounds, Map<String, Object> parameter);
	
	/**
	 * 查询我的任务信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	public List<Task> queryRecTaskList(PageBounds bounds, Map<String, Object> parameter);

	/**
	 * 删除任务信息
	 * @param id
	 */
	public void deleteTaskById(String id);
	
	/**
	 * 新增任务信息
	 * @param task
	 * @param user
	 * @return
	 */
	public void insertTaskInfo(Task task,User user);
	
	/**
	 * 修改任务信息
	 * @param task
	 * @param user
	 * @return
	 */
	public void editTaskInfo(Task task,User user);
	
	/**
	 * 根据ID查询任务信息
	 * @param id
	 */
	public Task queryTaskById(String id);
	
	/**
	 * 发送任务
	 * @param id
	 */
	public void sentTask(String id,User user);
	
	/**
	 * 更新任务状态
	 * @param map
	 */
	public int updateTaskReveice(Map<String,Object> map);
	
	/**
	 * 查询任务列表信息
	 * @param taskId
	 * @return
	 */
	public List<TaskReveice> findRecTaskList(String taskId);
}
