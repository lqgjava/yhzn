package com.yhzn.web.controller.storehouse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.yhzn.common.controller.BaseController;
import com.yhzn.common.page.PageUtil;
import com.yhzn.model.security.SysDict;
import com.yhzn.model.security.User;
import com.yhzn.model.storehouse.EntryStock;
import com.yhzn.model.storehouse.Product;
import com.yhzn.model.storehouse.PurBillModel;
import com.yhzn.model.storehouse.Purchase;
import com.yhzn.service.security.SysDictService;
import com.yhzn.service.security.SysLogService;
import com.yhzn.service.storehouse.EntryStockService;
import com.yhzn.service.storehouse.ProductService;

/**
 * 
 * @author lin
 * 入库管理处理类
 */
 
@Controller
@RequestMapping("/entryStock")
public class EntryStockController extends BaseController{

private static final Logger logger = LoggerFactory.getLogger(EntryStockController.class);
	
	//日志管理接口
	@Autowired
	private SysLogService sysLogService;
	//产品基本信息接口
	@Autowired
	private ProductService productService;
	//字典管理接口
	@Autowired
	private SysDictService sysDictService;
	//入库管理接口
	@Autowired
	private EntryStockService entryStockService;

	/**
	 * 入库清单信息列表查询
	 * @param request
	 * @return
	 */
	@RequiresPermissions("entry:list")
	@RequestMapping(value="/queryEntryStockList", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil queryEntryStockList(HttpServletRequest request){
		//获取登录人信息 
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("查询",user.getTrueName(),"查询入库清单信息列表 ",user.getLoginIp(),"/entryStock/queryEntryStockList");

		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		String purNo = request.getParameter("purNo");//入库编号
		//String entryName = request.getParameter("entryName");//入库名称
		
		Map<String,Object> parameter = new HashMap<String,Object>();
			parameter.put("purNo", purNo);
			//parameter.put("entryName", entryName);

		PageBounds bounds = new PageBounds(page , rows );
		
		List<PurBillModel> list = entryStockService.queryPurBillList(bounds, parameter);
		// 获得结果集条总数
		int total = ((PageList<PurBillModel>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);
		
		return result;
	}
	
	/**
	 * 删除入库清单信息
	 * @param id
	 * @return
	 */
	@RequiresPermissions("entry:delete")
	@RequestMapping("/deleteEntryStock")  
    @ResponseBody  
    public Map<String,Object> deleteEntryStockById(HttpServletRequest request) {  
        Map<String,Object> map = new HashMap<String,Object>();  
        //修改删除标记del为 1
        String ids = request.getParameter("ids");
        
		//获取登录人信息 
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("删除",user.getTrueName(),"删除入库清单信息: "+ids,user.getLoginIp(),"/entryStock/deleteEntryStock");
		
        String[] idArray = ids.split(",");
        if(null!=ids && idArray.length>0){
        	for(int i=0;i<idArray.length;i++){
        		entryStockService.deleteEntryStockById(idArray[i]); 
        	}
        }
        map.put("success", 1);  
        return map;  
    }
	
	/**
	 * 审核入库清单信息
	 * @param entryStock
	 * @param request
	 * @return
	 */
	@RequiresPermissions("entry:check")
	@RequestMapping(value="/checkEntryStock",method= RequestMethod.POST)
	@ResponseBody
	public String checkEntryStock(EntryStock entryStock,HttpServletRequest request){
	  Map<String,Object> map = new HashMap<String,Object>();  
      //获取登录人信息
	  User user= (User)request.getSession().getAttribute("user");
      //日志类型，操作人，操作内容，操作人IP,操作方法
	  sysLogService.insertSysLog("审核",user.getTrueName(),"审核入库清单信息： "+entryStock.getEntryNo(),user.getLoginIp(),"/entryStock/checkEntryStock");
	  try {
		  entryStockService.checkEntryStock(entryStock,user);
	    } catch (Exception e) {
			return "false";
	  }
      return "1";   
	}
	
	/**
	 * 新增入库清单信息
	 * @param entryStock
	 * @param request
	 * @return
	 */
	@RequiresPermissions("entry:add")
	@RequestMapping(value="/addEntryStock",method= RequestMethod.POST)
	@ResponseBody
	public String addEntryStock(EntryStock entryStock,HttpServletRequest request){
	  Map<String,Object> map = new HashMap<String,Object>();  
      //获取登录人信息
	  User user= (User)request.getSession().getAttribute("user");
      //日志类型，操作人，操作内容，操作人IP,操作方法
	  sysLogService.insertSysLog("新增",user.getTrueName(),"新增入库清单信息： "+entryStock.getEntryName(),user.getLoginIp(),"/entryStock/addPurchase");
	  try {
		  entryStockService.insertEntryStock(entryStock,user);
	    } catch (Exception e) {
			return "false";
	  }
      return "1";   
	}
	
	/**
	 * 修改入库清单信息
	 * @param entryStock
	 * @param request
	 * @return
	 */
	@RequiresPermissions("entry:edit")
	@RequestMapping(value="/editEntryStock",method= RequestMethod.POST)
	@ResponseBody
	public String editEntryStock(EntryStock entryStock,HttpServletRequest request){
		
	   //获取登录人信息
	   User user= (User)request.getSession().getAttribute("user");
       //日志类型，操作人，操作内容，操作人IP,操作方法
	   sysLogService.insertSysLog("修改",user.getTrueName(),"修改入库清单信息： "+entryStock.getEntryName(),user.getLoginIp(),"/entryStock/editPurchase");
	   try {
		   entryStockService.editEntryStock(entryStock,user);
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
       return str;
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
            json.append(",\"text\":\"" +product.getName() + "\"");   
            json.append(",\"state\":\"open\"");   
            json.append(",\"model\":\"" +product.getModel() + "\"");   
            json.append(",\"supplier\":\"" +product.getSupplier() + "\"");  
            json.append(",\"unit\":\"" +product.getUnit() + "\"");   
            json.append(",\"brand\":\"" +product.getBrand() + "\"");   
            json.append(",\"unitPrice\":\"" +product.getUnitPrice() + "\"");   
            json.append(",\"amount\":\"" +product.getAmount() + "\"");   
            json.append(",\"costPrice\":\"" +product.getCostPrice() + "\"");  
            json.append(",\"standard\":\"" +product.getStandard() + "\"");  
            json.append(",\"type\":\"" +product.getType() + "\""); 
            json.append("},");  
        }  
        String str = json.toString();  
        str = str.substring(0, str.length()-1); 
        return str;  
    }  
    
    
    /**
	 * 查询入库清单信息
	 * @param customer
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryPurBill",method= RequestMethod.POST)
	@ResponseBody
	public void queryPurBill(String purId,HttpServletRequest request){
	 try {
        List<PurBillModel> list = entryStockService.queryPurBillList(purId);
        //list转换为json 
        JSONArray json = JSONArray.fromObject(list);
        String str = "{\"total\":" + list.size() + ",\"rows\":" + json + "}";
 	 	response.setCharacterEncoding("utf-8");
 		PrintWriter out = response.getWriter();
 		out.print(str);
	     } catch (Exception e) {
	    	 System.out.println(e.toString());
	   }	
	}
    
	/**
	 * 新增入库清单信息
	 * @param customer
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addPurBill",method= RequestMethod.POST)
	@ResponseBody
	public String addPurBill(String entities,String purId,String status,HttpServletRequest request){
	  //获取登录人信息 
	  User user= (User) request.getSession().getAttribute("user");
	  //日志类型，操作人，操作内容，操作人IP,操作方法
	  sysLogService.insertSysLog("新增",user.getTrueName(),"新增入库清单",user.getLoginIp(),"/entryStock/addPurBill");
	  Map<String,String> map = new HashMap<String,String>();
	  try {
		  //转换清单产品信息为json数组对象
		  JSONArray jsonArray = JSONArray.fromObject(entities);
		  //json数组转成清单信息对象list
		  List<PurBillModel> list = (List<PurBillModel>) jsonArray.toCollection(jsonArray, PurBillModel.class);
          map.put("modifyName", user.getTrueName());
          map.put("status", status);
          map.put("id", purId);
          entryStockService.addPurBill(list,map);
	     } catch (Exception e) {
			return "false";
	   }	
       return "1";  
	}
	
	
	/**
	 * 清单入库
	 * @param id
	 * @param status
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updatePurBill",method= RequestMethod.POST)
	@ResponseBody
	public String updatePurBill(String id,String entrycount,HttpServletRequest request){
	  //获取登录人信息 
	  User user= (User) request.getSession().getAttribute("user");
	  //日志类型，操作人，操作内容，操作人IP,操作方法
	  sysLogService.insertSysLog("入库",user.getTrueName(),"产品入库",user.getLoginIp(),"/entryStock/updatePurBill");
	  Map<String,String> map = new HashMap<String,String>();
	
          map.put("modifyName", user.getTrueName());
          map.put("entrycount", entrycount);
          map.put("id", id);
          
          entryStockService.updatePurBill(map,user);
	    
       return "1";  
	}
	
	/**
	 * 清单全部入库
	 * @param ids
	 * @param status
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updatePurBillAll",method= RequestMethod.POST)
	@ResponseBody
	public String updatePurBillAll(String [] ids,String status){
		//获取登录人信息 
	  User user= (User) request.getSession().getAttribute("user");
	  //日志类型，操作人，操作内容，操作人IP,操作方法
	  sysLogService.insertSysLog("入库",user.getTrueName(),"产品入库",user.getLoginIp(),"/entryStock/updatePurBillAll");
	  Map<String,Object> map = new HashMap<String,Object>();
	 
          entryStockService.updatePurBillAll(ids,status,user.getTrueName());
	    
       return "1";  
	}
	
	
	/**
	 * 报价清单 生成EXCEL 
	 * @param priceList
	 * @return
	 */
	@RequestMapping(value="/exportExcel",method= RequestMethod.POST)
	@ResponseBody
	public String exportExcel(String entities,String purNo,HttpServletRequest request) throws Exception{
		//转换清单产品信息为json数组对象
		 JSONArray jsonArray = JSONArray.fromObject(entities);
		 //json数组转成清单信息对象list
		 List<PurBillModel> priceList = (List<PurBillModel>) jsonArray.toCollection(jsonArray, PurBillModel.class);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			WritableFont font = new WritableFont(WritableFont.createFont("宋体".trim()), 10, WritableFont.BOLD);
			WritableCellFormat cellfmt = new WritableCellFormat(font);
			WritableCellFormat cellfmt1 = new WritableCellFormat();
			cellfmt.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN);
			cellfmt.setBackground(Colour.GREY_25_PERCENT);
			cellfmt.setAlignment(jxl.format.Alignment.CENTRE);
			cellfmt.setWrap(true);
			cellfmt1.setWrap(true);// 自动换行
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			WritableSheet ws = wwb.createSheet("入库清单信息", 0);
			ws.getSettings().setDefaultColumnWidth(15);// 默认单元格宽度
			//ws.getSettings().setDefaultRowHeight(8);
			// Label x,y,z,e---对应第x列,第y行,显示z字符,使用e格式
			ws.addCell(new Label(0, 0, "序号", cellfmt)); 
			ws.addCell(new Label(1, 0, "名称", cellfmt)); 
			ws.addCell(new Label(2, 0, "品牌", cellfmt));
			ws.addCell(new Label(3, 0, "型号", cellfmt));
			ws.addCell(new Label(4, 0, "单位", cellfmt));
			ws.addCell(new Label(5, 0, "数量", cellfmt));
			ws.addCell(new Label(6, 0, "单价(元)", cellfmt));
			ws.addCell(new Label(7, 0, "总价(元)", cellfmt));
			if (priceList != null && priceList.size() > 0) {
				for (int i = 0; i < priceList.size(); i++) {
					PurBillModel purBillModel = (PurBillModel) priceList.get(i);
					ws.addCell(new Label(0, i + 1, purBillModel.getSerialNumber(), cellfmt1)); // 序号
					ws.addCell(new Label(1, i + 1, purBillModel.getName(), cellfmt1)); // 产品名称
					ws.addCell(new Label(2, i + 1, purBillModel.getBrand(), cellfmt1)); // 品牌
					ws.addCell(new Label(3, i + 1, purBillModel.getModel(), cellfmt1)); // 型号
					ws.addCell(new Label(4, i + 1, purBillModel.getUnit(), cellfmt1)); // 单位
					ws.addCell(new Label(5, i + 1, purBillModel.getAmount(), cellfmt1)); // 数量
					ws.addCell(new Label(6, i + 1, purBillModel.getUnitPrice(), cellfmt1)); // 单价
					ws.addCell(new Label(7, i + 1, purBillModel.getTotalPrice(), cellfmt1)); // 总价
				}
			}
			wwb.write();
			wwb.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		} 
		InputStream inputStream = new ByteArrayInputStream(out.toByteArray());
		
		//文件名称
		String fileName= purNo+".xls";   
		//fileName放入session,下载文件时候用
		request.getSession().setAttribute("filename", fileName);
		//在本地创建文件
		String filepath = request.getSession().getServletContext().getRealPath("/download/"+ fileName);
		File file = new File(filepath);
		if(!file.exists()){
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		//写入文件
		byte[] b = new byte[10240];
		int len;
		while((len=inputStream.read(b)) >0){
			fos.write(b,0,len);  
		}
		inputStream.close();
		fos.close();
	      
	    return "1";
	}
}