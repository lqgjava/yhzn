package com.yhzn.service.impl.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.common.util.zncb.Connections;
import com.yhzn.dao.security.SysDictDao;
import com.yhzn.model.security.SysDict;
import com.yhzn.model.security.User;
import com.yhzn.service.security.SysDictService;

@Service
public class SysDictServiceImpl implements SysDictService{
	
	//字典接口dao
	@Resource
	public SysDictDao sysDictDao;

	/**
	 * 获取字典列表
	 * @param paraMap
	 * @return
	 */
	@Override
	public List<SysDict> getDictValue(HashMap paraMap) {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		List<SysDict> list = new ArrayList<SysDict>();
		try{
			//conn = Connections.getConnectionXzyw();
			String sql = "select dict_key,dict_value1,parent_key,leaf_flag " +
					"from sys_dict where 1=1 and root_key = ? and parent_key = ? order by dict_key asc";
			pre = conn.prepareStatement(sql);
			pre.setString(1, String.valueOf(paraMap.get("rootKey")));
			pre.setString(2, String.valueOf(paraMap.get("parentKey")));
			result = pre.executeQuery();
			while(result.next()){
				SysDict dict = new SysDict();
				dict.setId(result.getString("dict_key"));
				dict.setText(result.getString("dict_value1"));
				dict.setParentKey(result.getString("parent_key"));
				dict.setIsLeaf(result.getString("leaf_flag"));
				list.add(dict);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(result != null){
					result.close();
				}
				if(pre != null){
					pre.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
	}


	/**
	 * 获取字典中文值
	 * @param map
	 * @return
	 */
	
	@Override
	public String getDictValueByDictKey(String organId) {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		String organName = "";
		try{
		//	conn = Connections.getConnectionXzyw();
			String sql = "select dict_value1 from sys_dict where 1=1 and dict_key = ? and root_key = 'GXSDM'";
			pre = conn.prepareStatement(sql);
			pre.setString(1, organId);
			result = pre.executeQuery();
			while(result.next()){
				organName = result.getString("dict_value1");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(result != null){
					result.close();
				}
				if(pre != null){
					pre.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return organName;
	}

	/**
	 * 查询字典信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	@Override
	public List<SysDict> querySysDictList(PageBounds bounds,
			Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return sysDictDao.querySysDictList(bounds,parameter);
	}



	/**
	 * 查询字典信息列表
	 * @param paraMap
	 * @return
	 */
	public List<SysDict> findDictListByMap(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return sysDictDao.findDictListByMap(map);
	}


	/**
	 * 新增字典
	 * @param sysDict
	 */
	public int insertSysDict(SysDict sysDict, User user) {
		sysDict.setId(UUID.randomUUID().toString().replaceAll("-", ""));//id
		sysDict.setCreateName(user.getTrueName());//创建人姓名
		//sysDict.setDeleteFlag("0");//删除标示
		//字典排序
	    if(StringUtils.isNotBlank(sysDict.getDictSortStr())){
	    	sysDict.setDictSort(Integer.parseInt(sysDict.getDictSortStr()));
	    }
	    //字典等级
	    if(StringUtils.isNotBlank(sysDict.getDictLevel())){
	    	//新增字典时，字典等级自动加1
	    	int level = Integer.parseInt(sysDict.getDictLevel())+Integer.parseInt("1");
	    	sysDict.setDictLevel(level+"");
	    }else{
	    	sysDict.setDictLevel("0");//设置为根节点节点
	    	sysDict.setRootKey(sysDict.getDictKey());//新增根节点时候 dictKey同rootKey一样
	    }
	    return sysDictDao.insertSysDict(sysDict);
	}

	/**
	 * 修改字典
	 * @param sysDict
	 */
	public int editSysDict(SysDict sysDict, User user) {
		//字典排序
	    if(StringUtils.isNotBlank(sysDict.getDictSortStr())){
	    	sysDict.setDictSort(Integer.parseInt(sysDict.getDictSortStr()));
	    }
		sysDict.setModifyName(user.getTrueName());//创建人姓名
		return sysDictDao.editSysDict(sysDict);
	}

	/**
     * 删除字典信息
     * @param id
     */
	public void deleteSysDictById(String id) {
		// TODO Auto-generated method stub
		sysDictDao.deleteSysDictById(id);
	}

}
