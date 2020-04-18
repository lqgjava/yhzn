package com.yhzn.web.controller.storehouse;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.yhzn.common.controller.BaseController;
import com.yhzn.common.page.PageUtil;
import com.yhzn.model.security.User;
import com.yhzn.model.storehouse.OutBillModel;
import com.yhzn.model.storehouse.Product;
import com.yhzn.model.storehouse.PurBillModel;
import com.yhzn.model.storehouse.Purchase;
import com.yhzn.model.storehouse.StockCheck;
import com.yhzn.service.security.SysDictService;
import com.yhzn.service.security.SysLogService;
import com.yhzn.service.storehouse.ProductService;
import com.yhzn.service.storehouse.PurchaseService;
import com.yhzn.service.storehouse.StockCheckService;

/**
 * 
 * @author lin
 * 库存核算处理类
 */
 
@Controller
@RequestMapping("/stockCheck")
public class StockCheckController extends BaseController{

private static final Logger logger = LoggerFactory.getLogger(StockCheckController.class);
	
	//日志管理接口
	@Autowired
	private SysLogService sysLogService;
	//产品基本信息接口
	@Autowired
	private ProductService productService;
	//字典管理接口
	@Autowired
	private SysDictService sysDictService;
	//库存核算管理接口
	@Autowired
	private StockCheckService stockCheckService;

	/**
	 * 库存核算信息列表查询
	 * @param request
	 * @return
	 */
	@RequiresPermissions("check:list")
	@RequestMapping(value="/queryStockCheckList", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil queryStockCheckList(HttpServletRequest request){
		//获取登录人信息 
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("查询",user.getTrueName(),"查询库存核算信息列表 ",user.getLoginIp(),"/stockCheck/queryStockCheckList");

		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		String type = request.getParameter("type");//类别
		String name = request.getParameter("name");//名称
		String standard = request.getParameter("standard");//规格
		String model = request.getParameter("model");//型号
		String brand = request.getParameter("brand");//品牌
		String supplier = request.getParameter("supplier");//供应商
		
		Map<String,Object> parameter = new HashMap<String,Object>();
			parameter.put("type", type);
			parameter.put("name", name);
			parameter.put("standard", standard);
			parameter.put("model", model);
			parameter.put("brand", brand);
			parameter.put("supplier", supplier);

		PageBounds bounds = new PageBounds(page , rows );
		List<StockCheck> list = stockCheckService.queryStockCheckList(bounds, parameter);
		// 获得结果集条总数
		int total = ((PageList<StockCheck>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);
		
		return result;
	}
	
	
	/**
	 * 修改库存核算信息
	 * @param stockCheck
	 * @param request
	 * @return
	 */
	@RequiresPermissions("check:edit")
	@RequestMapping(value="/editStockCheck",method= RequestMethod.POST)
	@ResponseBody
	public String editStockCheck(StockCheck stockCheck,HttpServletRequest request){
		
	   Map<String,Object> map = new HashMap<String,Object>();  
	   //获取登录人信息
	   User user= (User)request.getSession().getAttribute("user");
       //日志类型，操作人，操作内容，操作人IP,操作方法
	   sysLogService.insertSysLog("修改",user.getTrueName(),"修改库存核算信息： "+stockCheck.getName(),user.getLoginIp(),"/stockCheck/editStockCheck");
	   try {
		   stockCheckService.editStockCheckInfo(stockCheck,user);
		} catch (Exception e) {
			return "false";
		}
	   return "1";   
	}
	
	
	/**
	 * 查询出库清单信息
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryOutRecord",method= RequestMethod.POST)
	@ResponseBody
	public void queryOutRecord(String id,HttpServletRequest request){
	 try {
		    String str ="1";
	        List<OutBillModel> list = stockCheckService.queryOutRecordList(id);
	        if(null != list && list.size()>0){
		        //list转换为json 
		        JSONArray json = JSONArray.fromObject(list);
		        str = "{\"total\":" + list.size() + ",\"rows\":" + json + "}";
	        }
	 	 	response.setCharacterEncoding("utf-8");
	 		PrintWriter out = response.getWriter();
	 		out.print(str);
	     } catch (Exception e) {
	    	 System.out.println(e.toString());
	   }	
	}
    
	
	/**
	 * 查询出库清单信息
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryEntryRecord",method= RequestMethod.POST)
	@ResponseBody
	public void queryEntryRecord(String id,HttpServletRequest request){
	 try {
		    String str ="1";
	        List<PurBillModel> list = stockCheckService.queryEntryRecordList(id);
	        if(null != list && list.size()>0){
	        	//list转换为json 
		        JSONArray json = JSONArray.fromObject(list);
		        str = "{\"total\":" + list.size() + ",\"rows\":" + json + "}";
	        }
	 	 	response.setCharacterEncoding("utf-8");
	 		PrintWriter out = response.getWriter();
	 		out.print(str);
	     } catch (Exception e) {
	    	 System.out.println(e.toString());
	   }	
	}
	
	
	
}
