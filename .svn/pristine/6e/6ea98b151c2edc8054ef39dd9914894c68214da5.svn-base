package com.yhzn.web.controller.customer;

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
import com.yhzn.model.customer.CustomerProj;
import com.yhzn.model.customer.PriceBill;
import com.yhzn.model.security.SysDict;
import com.yhzn.model.security.User;
import com.yhzn.model.storehouse.Product;
import com.yhzn.service.customer.CustomerProjService;
import com.yhzn.service.security.SysDictService;
import com.yhzn.service.security.SysLogService;
import com.yhzn.service.storehouse.ProductService;

/**
 * 
 * @author lin
 * 客户项目信息处理类
 */
 
@Controller
@RequestMapping("/customerProj")
public class CustomerProjController extends BaseController{

private static final Logger logger = LoggerFactory.getLogger(CustomerProjController.class);
	
	//日志管理接口
	@Autowired
	private SysLogService sysLogService;
	//客户管理接口
	@Autowired
	private CustomerProjService customerProjService;
	//字典管理接口
	@Autowired
	private SysDictService sysDictService;
	//产品基本信息接口
	@Autowired
	private ProductService productService;
	
	/**
	 * 客户项目信息列表查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryCustomerProjList", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil queryCustomerProjList(HttpServletRequest request){
		//获取登录人信息 
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("查询",user.getTrueName(),"查询客户项目信息列表 ",user.getLoginIp(),"/customer/queryCustomerProjList");

		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		String projectName = request.getParameter("projectName");//项目名称
		String projectNo = request.getParameter("projectNo");//项目编号
		String purchaseUnit = request.getParameter("purchaseUnit");//采购单位
		String customerId = request.getParameter("customerId");//客户id
		
		Map<String,Object> parameter = new HashMap<String,Object>();
			parameter.put("projectName", projectName);
			parameter.put("projectNo", projectNo);
			parameter.put("purchaseUnit", purchaseUnit);
			parameter.put("customerId", customerId);

		PageBounds bounds = new PageBounds(page , rows );
		List<CustomerProj> list = customerProjService.queryCustomerProjList(bounds, parameter);
		// 获得结果集条总数
		int total = ((PageList<CustomerProj>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);
		
		return result;
	}
	
	/**
	 * 删除客户项目信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteCustomerProj")  
    @ResponseBody  
    public Map<String,Object> deleteCustomerProjById(HttpServletRequest request) {  
        Map<String,Object> map = new HashMap<String,Object>();  
        //修改删除标记del为 1
        String ids = request.getParameter("ids");
        
		//获取登录人信息 
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("删除",user.getTrueName(),"删除客户项目信息: "+ids,user.getLoginIp(),"/customer/deleteCustomerProj");
		
        String[] idArray = ids.split(",");
        if(null!=ids && idArray.length>0){
        	for(int i=0;i<idArray.length;i++){
        		customerProjService.deleteCustomerProjById(idArray[i]); 
        	}
        }
        map.put("success", 1);  
        return map;  
    }
	
	
	/**
	 * 新增客户项目信息
	 * @param customer
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addCustomerProj",method= RequestMethod.POST)
	@ResponseBody
	public String addCustomerProj(CustomerProj customerProj,HttpServletRequest request){
	  Map<String,Object> map = new HashMap<String,Object>();  
      //获取登录人信息
	  User user= (User)request.getSession().getAttribute("user");
      //日志类型，操作人，操作内容，操作人IP,操作方法
	  sysLogService.insertSysLog("新增",user.getTrueName(),"新增客户项目信息： "+customerProj.getProjectName(),user.getLoginIp(),"/customer/addCustomerProj");
	  try {
		  customerProjService.insertCustomerProj(customerProj,user);
	    } catch (Exception e) {
			return "false";
	  }
      return "1";   
	}
	
	/**
	 * 修改客户项目信息
	 * @param customer
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editCustomerProj",method= RequestMethod.POST)
	@ResponseBody
	public String editCustomerProj(CustomerProj customerProj,HttpServletRequest request){
		
	   //获取登录人信息
	   User user= (User)request.getSession().getAttribute("user");
       //日志类型，操作人，操作内容，操作人IP,操作方法
	   sysLogService.insertSysLog("修改",user.getTrueName(),"修改客户项目信息： "+customerProj.getProjectName(),user.getLoginIp(),"/customer/editCustomerProj");
	   try {
		   customerProjService.editCustomerProj(customerProj,user);
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
                //材料不在报价清单中
            	if("D001".equals(s.getDictKey())){
            		continue;
            	}
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
            json.append(",\"text\":\"" +product.getName()+"("+product.getModel()+")\"");  
            json.append(",\"call\":\"" +product.getName()+"\"");     
            json.append(",\"state\":\"open\"");   
            json.append(",\"model\":\"" +product.getModel() + "\"");  
            json.append(",\"supplier\":\"" +product.getSupplier() + "\"");  
            json.append(",\"unit\":\"" +product.getUnit() + "\"");   
            json.append(",\"brand\":\"" +product.getBrand() + "\"");   
            json.append(",\"unitPrice\":\"" +product.getUnitPrice() + "\"");   
            json.append(",\"amount\":\"" +product.getAmount() + "\"");   
            json.append(",\"costPrice\":\"" +product.getCostPrice() + "\"");   
            json.append("},");  
        }  
        String str = json.toString();  
        str = str.substring(0, str.length()-1); 
        return str;  
    }  
    
    
    /**
	 * 查询项目报价清单信息
	 * @param customer
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryPriceBill",method= RequestMethod.POST)
	@ResponseBody
	public void queryPriceBill(String projId,HttpServletRequest request){
	 try {
        List<PriceBill> list = customerProjService.queryPriceBillList(projId);
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
	 * 新增项目报价清单信息
	 * @param customer
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addPriceBill",method= RequestMethod.POST)
	@ResponseBody
	public String addPriceBill(String entities,String projId,String status,HttpServletRequest request){
	  //获取登录人信息 
	  User user= (User) request.getSession().getAttribute("user");
	  //日志类型，操作人，操作内容，操作人IP,操作方法
	  sysLogService.insertSysLog("新增",user.getTrueName(),"新增项目报价清单",user.getLoginIp(),"/customer/addCustomerProj");
	  Map<String,String> map = new HashMap<String,String>();
	  try {
		  //转换清单产品信息为json数组对象
		  JSONArray jsonArray = JSONArray.fromObject(entities);
		  //json数组转成清单信息对象list
		  List<PriceBill> list = (List<PriceBill>) jsonArray.toCollection(jsonArray, PriceBill.class);
          map.put("modifyName", user.getTrueName());
          map.put("status", status);
          map.put("id", projId);
          customerProjService.addPriceBill(list,map);
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
	@RequestMapping(value="/exportBillExcel",method= RequestMethod.POST)
	@ResponseBody
	public String exportBillExcel(String entities,String projectNo,HttpServletRequest request) throws Exception{
		//转换清单产品信息为json数组对象
		 JSONArray jsonArray = JSONArray.fromObject(entities);
		 //json数组转成清单信息对象list
		 List<PriceBill> priceList = (List<PriceBill>) jsonArray.toCollection(jsonArray, PriceBill.class);
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
			WritableSheet ws = wwb.createSheet("清单信息", 0);
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
					PriceBill priceBill = (PriceBill) priceList.get(i);
					ws.addCell(new Label(0, i + 1, i+1+"", cellfmt1)); // 序号
					ws.addCell(new Label(1, i + 1, priceBill.getName(), cellfmt1)); // 产品名称
					ws.addCell(new Label(2, i + 1, priceBill.getBrand(), cellfmt1)); // 品牌
					ws.addCell(new Label(3, i + 1, priceBill.getModel(), cellfmt1)); // 型号
					ws.addCell(new Label(4, i + 1, priceBill.getUnit(), cellfmt1)); // 单位
					ws.addCell(new Label(5, i + 1, priceBill.getAmount(), cellfmt1)); // 数量
					ws.addCell(new Label(6, i + 1, priceBill.getUnitPrice(), cellfmt1)); // 单价
					ws.addCell(new Label(7, i + 1, priceBill.getTotalPrice(), cellfmt1)); // 总价
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
		String fileName= projectNo+".xls";   
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
