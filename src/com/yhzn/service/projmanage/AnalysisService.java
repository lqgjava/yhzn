package com.yhzn.service.projmanage;

import java.util.List;
import java.util.Map;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.projmanage.Analysis;

/**
 * 成本分析管理接口
 * @author liany
 */
public interface AnalysisService {

	/**
	 * 查询成本分析信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	public List<Analysis> queryAnalysisList(PageBounds bounds, Map<String, Object> parameter);

}
