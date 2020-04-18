package com.yhzn.web.controller.storehouse;


import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.yhzn.common.controller.BaseController;
import com.yhzn.common.page.PageUtil;
import com.yhzn.common.util.ConfigUtil;
import com.yhzn.common.util.ExportExcel;
import com.yhzn.common.util.FileUpload;
import com.yhzn.dao.common.AttachmentDao;
import com.yhzn.model.common.Attachment;
import com.yhzn.model.projmanage.Schedule;
import com.yhzn.model.security.SysDict;
import com.yhzn.model.security.User;
import com.yhzn.model.storehouse.OutBillModel;
import com.yhzn.model.storehouse.OutStock;
import com.yhzn.model.storehouse.OutStockModel;
import com.yhzn.model.storehouse.Product;
import com.yhzn.model.storehouse.PurBillModel;
import com.yhzn.service.projmanage.ScheduleService;
import com.yhzn.service.security.SysDictService;
import com.yhzn.service.security.SysLogService;
import com.yhzn.service.storehouse.OutStockService;
import com.yhzn.service.storehouse.ProductService;

/**
 * 
 * @author lin
 * 出库管理处理类
 */
 
@Controller
@RequestMapping("/outStock")
public class OutStockController extends BaseController{

	
	//日志管理接口
	@Autowired
	private SysLogService sysLogService;
	//产品基本信息接口
	@Autowired
	private ProductService productService;
	//字典管理接口
	@Autowired
	private SysDictService sysDictService;
	//出库管理接口
	@Autowired
	private OutStockService outStockService;
	//附件管理接口
	@Autowired
	private AttachmentDao attachmentdao;
	//项目管理接口
	@Autowired
	private ScheduleService scheduleService;

	/**
	 * 出库清单信息列表查询
	 * @param request
	 * @return
	 */
	@RequiresPermissions("out:list")
	@RequestMapping(value="/queryOutStockList", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil queryOutStockList(HttpServletRequest request){
		//获取登录人信息 
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("查询",user.getTrueName(),"查询出库清单信息列表 ",user.getLoginIp(),"/outStock/queryOutStockList");

		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		String outNo = request.getParameter("outNo");//出库编号
		//String outName = request.getParameter("outName");//出库名称
		
		Map<String,Object> parameter = new HashMap<String,Object>();
			parameter.put("outNo", outNo);
			//parameter.put("outName", outName);

		PageBounds bounds = new PageBounds(page , rows );
		List<OutStock> list = outStockService.queryOutStockList(bounds, parameter);
		// 获得结果集条总数
		int total = ((PageList<OutStock>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);
		
		return result;
	}
	
	/**
	 * 删除出库清单信息
	 * @param id
	 * @return
	 */
	@RequiresPermissions("out:delete")
	@RequestMapping("/deleteOutStock")  
    @ResponseBody  
    public Map<String,Object> deleteOutStockById(HttpServletRequest request) {  
        Map<String,Object> map = new HashMap<String,Object>();  
        //修改删除标记del为 1
        String ids = request.getParameter("ids");
        
		//获取登录人信息 
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("删除",user.getTrueName(),"删除出库清单信息: "+ids,user.getLoginIp(),"/outStock/deleteOutStock");
		
        String[] idArray = ids.split(",");
        if(null!=ids && idArray.length>0){
        	for(int i=0;i<idArray.length;i++){
        		outStockService.deleteOutStockById(idArray[i]); 
        	}
        }
        map.put("success", 1);  
        return map;  
    }
	
	
	/**
	 * 新增出库清单信息
	 * @param OutStock
	 * @param request
	 * @return
	 */
	@RequiresPermissions("out:add")
	@RequestMapping(value="/addOutStock",method= RequestMethod.POST)
	@ResponseBody
	public String addOutStock(OutStock outStock,HttpServletRequest request){
      //获取登录人信息
	  User user= (User)request.getSession().getAttribute("user");
      //日志类型，操作人，操作内容，操作人IP,操作方法
	  sysLogService.insertSysLog("新增",user.getTrueName(),"新增出库清单信息： "+outStock.getOutName(),user.getLoginIp(),"/outStock/addOutStock");
	  try {
		  outStockService.insertOutStock(outStock,user);
	    } catch (Exception e) {
			return "false";
	  }
      return "1";   
	}
	
	/**
	 * 修改出库清单信息
	 * @param OutStock
	 * @param request
	 * @return
	 */
	@RequiresPermissions("our:edit")
	@RequestMapping(value="/editOutStock",method= RequestMethod.POST)
	@ResponseBody
	public String editOutStock(OutStock outStock,HttpServletRequest request){
		
	   //获取登录人信息
	   User user= (User)request.getSession().getAttribute("user");
       //日志类型，操作人，操作内容，操作人IP,操作方法
	   sysLogService.insertSysLog("修改",user.getTrueName(),"修改出库清单信息： "+outStock.getOutName(),user.getLoginIp(),"/outStock/editOutStock");
	   try {
		   outStockService.editOutStock(outStock,user);
		} catch (Exception e) {
			return "false";
		}
       return "1";  
	}
	
	/**
	 * 审核出库清单信息
	 * @param purchase
	 * @param request
	 * @return
	 */
	@RequiresPermissions("out:check")
	@RequestMapping(value="/checkOutStock",method= RequestMethod.POST)
	@ResponseBody
	public String checkOutStock(OutStock outStock,HttpServletRequest request){
      //获取登录人信息
	  User user= (User)request.getSession().getAttribute("user");
      //日志类型，操作人，操作内容，操作人IP,操作方法
	  sysLogService.insertSysLog("审核",user.getTrueName(),"审核出库清单信息： "+outStock.getOutNo(),user.getLoginIp(),"/outStock/checkOutStock");
	  try {
		  outStockService.checkOutStock(outStock,user);
	    } catch (Exception e) {
			return "false";
	  }
      return "1";   
	}
	
	/**
	 * 获取产品列表树
	 * @param response
	 */
	@RequestMapping(value="/getProductTree",method= RequestMethod.POST)
	@ResponseBody
	public String getProductTree(HttpServletRequest request){  
        String str ="";  
        StringBuilder json = new StringBuilder();  
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("rootKey", "CPDLDM");
        map.put("parentKey", "CPDLDM");
        // 获取根节点下的所有子节点  
        List<SysDict> sysDictList = sysDictService.findDictListByMap(map);
        // 遍历子节点下的子节点  
        if(sysDictList!=null && sysDictList.size()!=0){  
            json.append("[");  
            for (SysDict s : sysDictList) {  
             
                json.append("{\"id\":\"" +s.getId()+ "\"");   
                json.append(",\"text\":\"" +s.getDictValue() + "\"");   
                json.append(",\"state\":\"open\"");  
                json.append(",\"name\":\"parent\""); 
                map.put("type", s.getDictValue());
                // 该节点有子节点  
                // 设置为关闭状态,而从构造异步加载tree  
                List<Product> pList = productService.queryProductListByMap(map);  
                if(pList!=null && pList.size()!=0){// 存在子节点  
                     json.append(",\"children\":[");  
                     json.append(dealJsonFormat(pList));// 存在子节点的都放在一个工具类里面处理了  
                     json.append("]");  
                }  
                json.append("},");  
            }  
            str = json.toString();  
            str = str.substring(0, str.length()-1);  
            str+="]";  
              
        }  
       return str.replaceAll("null", "");
    }  
	
	/** 
     * 处理数据集合，将数据集合转为符合格式的json 
     * @param sList 参数 
     * @return json字符串 
     */  
    public String dealJsonFormat(List<Product> pList){  
        StringBuilder json = new StringBuilder();  
        for (Product product : pList) {  
            json.append("{\"id\":\"" +product.getId()+ "\"");   
            json.append(",\"text\":\"" +product.getName()+"("+product.getModel()+")\"");  
            json.append(",\"call\":\"" +product.getName()+"\""); 
            json.append(",\"state\":\"open\"");   
            json.append(",\"model\":\"" +product.getModel() + "\"");   
            json.append(",\"supplier\":\"" +product.getSupplier() + "\"");  
            json.append(",\"unit\":\"" +product.getUnit() + "\"");   
            json.append(",\"brand\":\"" +product.getBrand() + "\"");   
            json.append(",\"unitPrice\":\"" +product.getUnitPrice() + "\""); 
            if(StringUtils.isBlank(product.getAmount())){
            	json.append(",\"amount\":\"0\""); 
            }else{
            	json.append(",\"amount\":\"" +product.getAmount() + "\""); 
            }
            json.append(",\"costPrice\":\"" +product.getCostPrice() + "\"");  
            json.append(",\"type\":\"" +product.getType() + "\""); 
            json.append("},");  
        }  
        String str = json.toString();  
        str = str.substring(0, str.length()-1); 
        return str;  
    }  
    
    
    /**
	 * 清单出库
	 * @param id
	 * @param status
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updateOutBill",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,String> updateOutBill(String id,String status,HttpServletRequest request){
	  //获取登录人信息 
	  User user= (User) request.getSession().getAttribute("user");
	  //日志类型，操作人，操作内容，操作人IP,操作方法
	//  sysLogService.insertSysLog("出库",user.getTrueName(),"产品出库",user.getLoginIp(),"/outStock/updateOutBill");
	  Map<String,String> map = new HashMap<String,String>();
	  Map<String,String> map2 = new HashMap<String,String>();
          map.put("modifyName", user.getTrueName());
          map.put("outStatus", status);
          map.put("id", id);
          map2 = outStockService.updateOutBill(map,user);
	  return map2;  
	}
    
    /**
	 * 查询出库清单信息
	 * @param outId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryOutStockBill",method= RequestMethod.POST)
	@ResponseBody
	public void queryOutStockBill(String outId,HttpServletRequest request){
	 try {
		 List outList = new ArrayList();
        List<OutBillModel> list = outStockService.queryOutStockBillList(outId);
        //list转换为json 
        if(null != list && list.size() >0){
        	for(int i=0;i<list.size();i++){
        		OutBillModel obm=list.get(i);
        		obm.setOper("<a style=\"color:blue\" href=\"javascript:openLoc('"+obm.getId()+"')\" >打开存放位置<a>");
        		outList.add(obm);
        	}
        }
        JSONArray json = JSONArray.fromObject(outList);
        String str = "{\"total\":" + list.size() + ",\"rows\":" + json + "}";
 	 	response.setCharacterEncoding("utf-8");
 		PrintWriter out = response.getWriter();
 		out.print(str);
	     } catch (Exception e) {
	    	 System.out.println(e.toString());
	   }	
	}
    
	/**
	 * 新增出库清单信息
	 * @param entities
	 * @param outId
	 * @param outNo
	 * @param status
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addOutBill",method= RequestMethod.POST)
	@ResponseBody
	public String addOutStockBill(String entities,String outId,String outNo,String status,String projNo,String companyName,HttpServletRequest request){
	  //获取登录人信息 
	  User user= (User) request.getSession().getAttribute("user");
	  //日志类型，操作人，操作内容，操作人IP,操作方法
	  sysLogService.insertSysLog("新增",user.getTrueName(),"新增出库清单",user.getLoginIp(),"/outStock/addPurBill");
	  Map<String,String> map = new HashMap<String,String>();
	  try {
		  //转换清单产品信息为json数组对象
		  JSONArray jsonArray = JSONArray.fromObject(entities);
		  //json数组转成清单信息对象list
		  List<OutBillModel> list = (List<OutBillModel>) jsonArray.toCollection(jsonArray, OutBillModel.class);
          map.put("modifyName", user.getTrueName());
          map.put("status", status);
          map.put("id", outId);
          map.put("outNo", outNo);
          map.put("projNo",projNo);
          map.put("companyName",companyName);
          outStockService.addOutStockBill(list,map,user);
	     } catch (Exception e) {
	    	 System.out.println(e);
			return "false";
	   }	
       return "1";  
	}
	
	/**
	 * 修改出库清单信息
	 * @param entities
	 * @param outId
	 * @param outNo
	 * @param status
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editOutBill",method= RequestMethod.POST)
	@ResponseBody
	public String editOutStockBill(String entities,String outId,String outNo,String status,HttpServletRequest request){
	  //获取登录人信息 
	  User user= (User) request.getSession().getAttribute("user");
	  //日志类型，操作人，操作内容，操作人IP,操作方法
	  sysLogService.insertSysLog("新增",user.getTrueName(),"新增出库清单",user.getLoginIp(),"/outStock/editOutBill");
	  Map<String,String> map = new HashMap<String,String>();
	  try {
		  //转换清单产品信息为json数组对象
		  JSONArray jsonArray = JSONArray.fromObject(entities);
		  //json数组转成清单信息对象list
		  List<OutBillModel> list = (List<OutBillModel>) jsonArray.toCollection(jsonArray, OutBillModel.class);
          map.put("modifyName", user.getTrueName());
          map.put("status", status);
          map.put("id", outId);
          map.put("outNo", outNo);
          outStockService.editOutStockBill(list,map,user);
	     } catch (Exception e) {
			return "false";
	   }	
       return "1";  
	}
	
	
	/**
	 * 报价清单 生成EXCEL 
	 * @param priceList
	 * @return
	 */
	@RequestMapping(value="/exportExcel",method= RequestMethod.GET)
	@ResponseBody
	public String exportExcel(String entities,String outNo,HttpServletRequest request) throws Exception{
		 List<OutBillModel> priceList = outStockService.queryOutStockBillListByOutNo(outNo);
		 OutStockModel mapname=outStockService.findCompanyNameByOutId(outNo);
		List<PurBillModel> excellists = new ArrayList<PurBillModel>();
		for (int i = 0; i < priceList.size(); i++) {
			PurBillModel price = new PurBillModel();
			Map<String, Object> map = object2Map(priceList.get(i));
			Set<String> keys = map.keySet();
			

			for (String key : keys) {
				if (key.equals("serialNumber")) {//序号	
					price.setSerialNumber(map.get(key).toString());
				}
				if (key.equals("name")) {//名称	
					price.setName(map.get(key).toString());
				}

				if (key.equals("brand")) {//品牌	
					price.setBrand(map.get(key)==null?"":map.get(key).toString());
				}
				if (key.equals("model")) {//型号	
					price.setModel(map.get(key).toString());
				}
				if (key.equals("unit")) {//单位	
					price.setUnit(map.get(key).toString());
				}
				if (key.equals("amount")) {//数量	
					price.setAmount(map.get(key).toString());
				}if (key.equals("unitPrice")) {//单价	
					price.setUnitPrice(map.get(key)==null?"":map.get(key).toString());
				}if (key.equals("costPrice")) {//总价	
					price.setCostPrice(map.get(key)==null?"":map.get(key).toString());
				}

			}

			excellists.add(price);
		}
		String[] Title = { "序号", "名称", "品牌", "型号", "单位","数量","单价（元）","总价（元）" };
		ExportExcel.exportExcel( mapname.getCompanyName()+"("+mapname.getProjNo()+ ")出库清单.xls", Title, excellists, response, "", 0);
		 
		
	      
	    return "1";
	}
	/**
	 * 将实体类对象转化成map对象
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> object2Map(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (obj == null) {
			return map;
		}
		Class clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(obj));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	//上传出库清单凭证
	@RequestMapping(value="/addvoucher",method= RequestMethod.POST)
	@ResponseBody
	public void addvoucher(String id, @RequestParam("file") MultipartFile[] files,HttpServletResponse response) throws IOException {
		String uploadDir = ConfigUtil.getConfig("upload.dir");
		for (MultipartFile file : files) {
			if (StringUtils.isNotBlank(file.getOriginalFilename())) {
				Attachment att = FileUpload.upload(file, uploadDir, "outStock");
				att.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				att.setParentId(id);
				attachmentdao.insertAtt(att);
				
			}else {
				continue;
			}
		}
		
		try {
			
		} catch (Exception e) {
			response.getWriter().write(JSON.toJSONString("文件上传失败"));
		}
		response.getWriter().write(JSON.toJSONString(1));

	}
	@RequestMapping(value = "/queryScheduleList", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil queryScheduleList(HttpServletRequest request) {
		// 获取登录人信息
		User user = (User) request.getSession().getAttribute("user");
		// 日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("查询", user.getTrueName(), "查询项目进度信息列表 ", user.getLoginIp(),
				"/schedule/queryScheduleList");

		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));

		String projNo = request.getParameter("projNo");// 项目编号
		String projName = request.getParameter("projName");// 项目名称
		String projStatus = request.getParameter("projStatus");// 项目状态
		String beginDate = request.getParameter("beginDate");// 项目开始时间
		String endDate = request.getParameter("endDate");// 项目结束时间
		String fromDate = request.getParameter("fromDate");// 项目起始时间
		String toDate = request.getParameter("toDate");// 项目终止时间
		String unitName = request.getParameter("unitName");// 单位名称	

		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("projNo", projNo);
		parameter.put("projName", projName);
		parameter.put("projStatus", projStatus);
		parameter.put("beginDate", beginDate);
		parameter.put("endDate", endDate);
		parameter.put("fromDate", fromDate);
		parameter.put("toDate", toDate);
		parameter.put("unitName", unitName);

		PageBounds bounds = new PageBounds(page, rows);
		List<Schedule> list = scheduleService.queryScheduleList(bounds, parameter);
		// 获得结果集条总数
		int total = ((PageList<Schedule>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);

		return result;
	}
}
