package com.yhzn.service.impl.projmanage;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.dao.projmanage.AnalysisDao;
import com.yhzn.dao.workbench.WorkbenchDao;
import com.yhzn.model.projmanage.Analysis;
import com.yhzn.model.workbench.Task;
import com.yhzn.service.projmanage.AnalysisService;

/**
 * 成本分析管理实现类
 * @author liany
 */
@Service
public class AnalysisServiceImpl implements AnalysisService {

	// 成本分析管理dao
	@Resource
	private AnalysisDao analysisDao;
	
	/**
	 * 查询成本分析信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	@Override
	public List<Analysis> queryAnalysisList(PageBounds bounds,	Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return analysisDao.queryAnalysisList(bounds, parameter);
	}
}
