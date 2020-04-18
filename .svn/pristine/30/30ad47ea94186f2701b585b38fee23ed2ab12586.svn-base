package com.yhzn.service.impl.common;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.yhzn.dao.common.SysSequenceDao;
import com.yhzn.model.common.SysSequence;
import com.yhzn.model.security.User;
import com.yhzn.service.common.SysSequenceService;

/**
 * 通用编号接口实现类
 * @author liany
 *
 */
@Service
public class SysSequenceServiceImpl implements SysSequenceService{

	//通用编号dao
	@Resource
	private SysSequenceDao sysSequenceDao;
	
	public String getNextNo(String tableName,User user){
		// 序号生成格式代码
		DecimalFormat format = new DecimalFormat(StringUtils.repeat("0",3));
		String curDateStr =  getCurDteStr();
		Map paramMap = new HashMap();
		paramMap.put("tableName", tableName); //表名
		paramMap.put("curDate", curDateStr);// 获取当前年月
		SysSequence sysSequence = sysSequenceDao.findMaxSeqById(paramMap);
		String maxNo = null;
		// 如果序列表中不存在id记录,则新建
		if (sysSequence == null) {
			sysSequence = new SysSequence();
			sysSequence.setTableName(tableName);
			sysSequence.setCurDate(curDateStr);
			maxNo = format.format(1);
			sysSequence.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			sysSequence.setMaxSeq(maxNo);
			sysSequence.setCreateName(user.getTrueName());//创建人姓名
			//插入序号
			sysSequenceDao.insertSysSequence(sysSequence);
		} else {
			maxNo = sysSequence.getMaxSeq();
			maxNo = format.format(Long.parseLong(maxNo) + 1);
			sysSequence.setMaxSeq(maxNo);
			sysSequence.setModifyName(user.getTrueName());//修改人姓名
			//更新序号
			sysSequenceDao.updateMaxSeqById(sysSequence);
		}
		return curDateStr+maxNo;
	}
	
	/**
	 * 获取当前年月
	 * @return
	 */
	public String getCurDteStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Date date = new Date();
        return sdf.format(date);
	}
}
