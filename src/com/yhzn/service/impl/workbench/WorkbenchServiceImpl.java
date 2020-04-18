package com.yhzn.service.impl.workbench;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.dao.storehouse.EntryStockDao;
import com.yhzn.dao.workbench.WorkbenchDao;
import com.yhzn.model.security.User;
import com.yhzn.model.workbench.Task;
import com.yhzn.model.workbench.TaskReveice;
import com.yhzn.service.workbench.WorkbenchService;

/**
 * 工作台管理实现类
 * @author liany
 *
 */
@Service
public class WorkbenchServiceImpl implements WorkbenchService{

	//工作台管理dao
	@Resource
	private WorkbenchDao workbenchDao;
		
	/**
	 * 查询任务信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	@Override
	public List<Task> queryTaskList(PageBounds bounds,
			Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return workbenchDao.queryTaskList(bounds, parameter);
	}
	
	/**
	 * 查询我的任务信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	@Override
	public List<Task> queryRecTaskList(PageBounds bounds,
			Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return workbenchDao.queryRecTaskList(bounds, parameter);
	}

	/**
	 * 删除任务信息
	 * @param id
	 */
	@Override
	public void deleteTaskById(String id) {
		// TODO Auto-generated method stub
		workbenchDao.deleteTaskById(id);
	}

	/**
	 * 新增任务信息
	 * @param task
	 * @param user
	 * @return
	 */
	@Override
	public void insertTaskInfo(Task task, User user) {
		// TODO Auto-generated method stub
		task.setId(UUID.randomUUID().toString().replaceAll("-", ""));//id
		task.setCreateName(user.getTrueName());//创建人姓名
		task.setUserId(user.getPersonId());//创建人id
		workbenchDao.insertTaskInfo(task);
		//保存并下发任务
		if("0".equals(task.getStatus())){
			String ids[] = task.getReceiveId().split(",");
			String names[] = task.getReceiveName().split(",");
			
			for(int i=0;i<ids.length;i++){
				TaskReveice taskReveice = new TaskReveice();
				taskReveice.setId(UUID.randomUUID().toString().replaceAll("-", ""));//id
				taskReveice.setCreateName(user.getTrueName());//创建人姓名
				taskReveice.setTaskNo(task.getTaskNo());
				taskReveice.setTaskId(task.getId());
				taskReveice.setTaskName(task.getTaskName());
				taskReveice.setTaskContent(task.getTaskContent());
				taskReveice.setFinishDateStr(task.getFinishDateStr());
				taskReveice.setReward(task.getReward());
				taskReveice.setSentId(user.getPersonId());
				taskReveice.setUserId(ids[i]);
				taskReveice.setRemark(task.getRemark());
				taskReveice.setSingedr(names[i]);
				
				workbenchDao.insertTaskReveiceInfo(taskReveice);
			}
		}
	}

	/**
	 * 修改任务信息
	 * @param task
	 * @param user
	 * @return
	 */
	@Override
	public void editTaskInfo(Task task, User user) {
		// TODO Auto-generated method stub
		task.setModifyName(user.getTrueName());//修改人姓名
		
		workbenchDao.editTaskInfo(task);
	}

	/**
	 * 根据ID查询任务信息
	 * @param id
	 */
	@Override
	public Task queryTaskById(String id) {
		// TODO Auto-generated method stub
		return workbenchDao.queryTaskById(id);
	}


	/**
	 * 发送任务
	 * @param id
	 */
	@Override
	public void sentTask(String id,User user) {
		//查询出任务信息
		Task task = workbenchDao.queryTaskById(id);
		
		if(null != task){
			//下发任务
			String ids[] = task.getReceiveId().split(",");
			String names[] = task.getReceiveName().split(",");
			for(int i=0;i<ids.length;i++){
				TaskReveice taskReveice = new TaskReveice();
				taskReveice.setId(UUID.randomUUID().toString().replaceAll("-", ""));//id
				taskReveice.setCreateName(task.getCreateName());//创建人姓名
				taskReveice.setTaskNo(task.getTaskNo());
				taskReveice.setTaskId(task.getId());
				taskReveice.setTaskName(task.getTaskName());
				taskReveice.setTaskContent(task.getTaskContent());
				taskReveice.setFinishDateStr(task.getFinishDateStr());
				taskReveice.setReward(task.getReward());
				taskReveice.setSentId(task.getUserId());
				taskReveice.setUserId(ids[i]);
				taskReveice.setRemark(task.getRemark());
				taskReveice.setSingedr(names[i]);
				
				workbenchDao.insertTaskReveiceInfo(taskReveice);
			}
		}
		
		//更新任务状态
		Map map = new HashMap();
		map.put("id", id);
		map.put("status", "0");//待签收状态
		map.put("modifyName",user.getTrueName());
		
		workbenchDao.updateTaskStatus(map);
		
	}

	/**
	 * 签收任务
	 * @param map
	 */
	@Override
	public int updateTaskReveice(Map map) {
		// TODO Auto-generated method stub
		return workbenchDao.updateTaskReveice(map);
	}
	
	/**
	 * 查询任务列表信息
	 * @param taskId
	 * @return
	 */
	public List<TaskReveice> findRecTaskList(String taskId){
		return workbenchDao.findRecTaskList(taskId);
	}

}
