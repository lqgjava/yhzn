package com.yhzn.web.controller.gxc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhzn.common.controller.BaseController;

@Controller
@RequestMapping("/main")
public class MainController extends BaseController {
	/* 只控制跳转 */
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping("/toMainData")
	public String intoMainData() {
		logger.info(">>>>>>>>>>>>>>>>进入首页");
		return "/business/mainData";
	}

	@RequestMapping("/toHomePage")
	public String intoHomePageList() {
		logger.info(">>>>>>>>>>>>>>>>进入首页");
		return "/homepage/homePageList";
	}

	@RequestMapping("/toWorkbench")
	public String intoWorkbenchList() {
		logger.info(">>>>>>>>>>>>>>>>进入我的工作台");
		return "/workbench/workbenchList";
	}

	@RequestMapping("/toTask")
	public String intoTaskList() {
		logger.info(">>>>>>>>>>>>>>>>进入我的任务");
		return "/workbench/taskList";
	}

	@RequestMapping("/toPerson")
	public String intoPersonList() {
		logger.info(">>>>>>>>>>>>>>>>进入人员管理");
		return "/person/personList";
	}

	@RequestMapping("/toEntryStock")
	public String intoEntryStockList() {
		logger.info(">>>>>>>>>>>>>>>>进入入库管理");
		return "/storehouse/entryStock/entryStockList";
	}

	@RequestMapping("/toOutStock")
	public String intoOutStockList() {
		logger.info(">>>>>>>>>>>>>>>>进入出库管理");
		return "/storehouse/outStock/outStockList";
	}

	@RequestMapping("/toPurchase")
	public String intoPurchaseList() {
		logger.info(">>>>>>>>>>>>>>>>进入采购管理");
		return "/storehouse/purchase/purchaseList";
	}

	@RequestMapping("/toStockCheck")
	public String intoStockCheckList() {
		logger.info(">>>>>>>>>>>>>>>>进入库存核算");
		return "/storehouse/stockCheck/stockCheckList";
	}

	@RequestMapping("/toSchedule")
	public String intoScheduleList() {
		logger.info(">>>>>>>>>>>>>>>>进入项目进度管理");
		return "/projmanage/schedule/scheduleList";
	}

	@RequestMapping("/toAnalysis")
	public String intoAnalysisList() {
		logger.info(">>>>>>>>>>>>>>>>进入成本分析管理");
		return "/projmanage/analysis/analysisList";
	}

	@RequestMapping("/toProduct")
	public String intoProductList() {
		logger.info(">>>>>>>>>>>>>>>>进入产品基本信息");
		return "/storehouse/product/productList";
	}

	@RequestMapping("/toCustPrice")
	public String intoCustPriceList() {
		logger.info(">>>>>>>>>>>>>>>>进入产品基本信息");
		return "/customer/customerPriceList";
	}

	@RequestMapping("/toCustomer")
	public String intoCustomerList() {
		logger.info(">>>>>>>>>>>>>>>>进入客户管理");
		return "/customer/customerList";
	}

	@RequestMapping("/toSysUser")
	public String intoSysUserList() {
		logger.info(">>>>>>>>>>>>>>>>进入用户管理");
		return "/sys/sysUser/userList";
	}

	@RequestMapping("/toSysRole")
	public String intoSysRoleList() {
		logger.info(">>>>>>>>>>>>>>>>进入角色管理");
		return "/sys/sysRole/roleList";
	}

	@RequestMapping("/toSysDict")
	public String intoSysDictList() {
		logger.info(">>>>>>>>>>>>>>>>进入字典管理");
		return "/sys/sysDict/dictList";
	}

	@RequestMapping("/toSysChildDict")
	public String intoSysChildDictList() {
		logger.info(">>>>>>>>>>>>>>>>进入子字典管理");
		return "/sys/sysDict/dictChildList";
	}

	@RequestMapping("/toSysLog")
	public String intoSysLogList() {
		logger.info(">>>>>>>>>>>>>>>>进入日志管理");
		return "/sys/sysLog/logList";
	}

	// 下载文件
	@RequestMapping("/toExportExcel")
	public String exportExcel() {
		return "/common/exportExcel";
	}

	@GetMapping("/supplier")
	public String supplier() {
		logger.info(">>>>>>>>>>>>>>>>进入供应商管理");
		return "/supplier/supplier";

	}


	/**
	 * 项目前期页面
	 * 
	 * @return
	 */
	@GetMapping("/earlyPorject")
	public String earlyProject() {
		logger.info(">>>>>>>>>>>>>>>>进入项目前期管理");
		return "/projmanage/schedule/earlyProject";
	}
	@GetMapping("/moduleList")
	public String sysModule() {
		return "/sys/sysModule/modulelist";
	}
}
