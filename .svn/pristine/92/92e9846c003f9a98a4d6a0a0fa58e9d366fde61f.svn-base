package com.yhzn.web.controller.storehouse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.yhzn.model.person.Person;
import com.yhzn.model.security.SysDict;
import com.yhzn.model.security.User;
import com.yhzn.model.storehouse.Product;
import com.yhzn.service.security.SysDictService;
import com.yhzn.service.security.SysLogService;
import com.yhzn.service.storehouse.ProductService;

/**
 * 产品基本信息处理类
 * @author liany
 *
 */

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	//日志管理接口
	@Autowired
	private SysLogService sysLogService;
	//产品基本信息接口
	@Autowired
	private ProductService productService;
	//字典管理接口
	@Autowired
	private SysDictService sysDictService;
	@RequiresPermissions("product:list")
	@RequestMapping(value="/queryProductList", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil queryProductList(HttpServletRequest request){
		//获取登录人信息 
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("查询",user.getTrueName(),"查询产品基本信息列表 ",user.getLoginIp(),"/product/queryProductList");

		
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
		List<Product> list = productService.queryProductList(bounds, parameter);
		// 获得结果集条总数
		int total = ((PageList<Product>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);
		
		return result;

	}
	
	
	/**
	 * 删除产品信息
	 * @param id
	 * @return
	 */
	@RequiresPermissions("product:delete")
	@RequestMapping("/deleteProduct")  
    @ResponseBody  
    public Map<String,Object> deleteProductById(HttpServletRequest request) {  
		 Map<String,Object> map = new HashMap<String,Object>();  
	    //修改删除标记del为 1
	    String ids = request.getParameter("ids");
	    //获取登录人信息    
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("删除",user.getTrueName(),"删除产品基本信息: "+ids,user.getLoginIp(),"/product/deleteProduct");
       
        String[] idArray = ids.split(",");
        if(null!=ids && idArray.length>0){
        	for(int i=0;i<idArray.length;i++){
        		productService.deleteProductById(idArray[i]); 
        	}
        }
        map.put("success", 1);  
        return map;  
    }
	
	/**
	 * 新增产品信息
	 * @param person
	 * @param request
	 * @return
	 */
	@RequiresPermissions("product:add")
	@RequestMapping(value="/addProduct",method= RequestMethod.POST)
	@ResponseBody
	public String addProduct(Product product,HttpServletRequest request){
	  //获取登录人信息
	  User user= (User) request.getSession().getAttribute("user");
      //日志类型，操作人，操作内容，操作人IP,操作方法
	  sysLogService.insertSysLog("新增",user.getTrueName(),"新增产品基本信息： "+product.getName(),user.getLoginIp(),"/product/addProduct");

	  Map<String,Object> map = new HashMap<String,Object>();  
	  try {
		  productService.insertProductInfo(product,user);
	  } catch (Exception e) {
			return "false";
		}
     return "1";  
	}
	
	/**
	 * 修改产品信息
	 * @param person
	 * @param request
	 * @return
	 */
	@RequiresPermissions("product:edit")
	@RequestMapping(value="/editProduct",method= RequestMethod.POST)
	@ResponseBody
	public String editProduct(Product product,HttpServletRequest request){
		
	   Map<String,Object> map = new HashMap<String,Object>();  
	   //获取登录人信息
	   User user= (User)request.getSession().getAttribute("user");
       //日志类型，操作人，操作内容，操作人IP,操作方法
	   sysLogService.insertSysLog("修改",user.getTrueName(),"修改产品基本信息： "+product.getName(),user.getLoginIp(),"/product/editProduct");
	   try {
		   productService.editProductInfo(product,user);
		} catch (Exception e) {
			return "false";
		}
	   return "1";   
	}
	
	/**
	 * 获取产品类别
	 * @param type
	 * @return
     * @throws IOException 
	 */
    @RequestMapping(value="/getProductTypeList",method= RequestMethod.POST)
    @ResponseBody  
    public String getProductTypeList(String type,String rootKey,String parentKey,HttpServletRequest request) throws IOException {
    	String str ="";  
        StringBuilder json = new StringBuilder();  
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("rootKey", rootKey);
        map.put("parentKey", parentKey);
        // 获取客户行业类别字典代码
        List<SysDict> sysDictList = sysDictService.findDictListByMap(map);
        // 遍历子节点下的子节点  
        if(sysDictList!=null && sysDictList.size()!=0){  
            json.append("[");  
            for (SysDict s : sysDictList) {  
                json.append("{\"id\":\"" +s.getDictValue()+ "\"");   
                json.append(",\"text\":\"" +s.getDictValue() + "\"");   
                if(s.getDictValue().equals(type)){
                	json.append(",\"checked\":"+true);  
                }
                json.append("},");  
            }  
            str = json.toString();  
            str = str.substring(0, str.length()-1);  
            str+="]";  
              
        }  
       return str;
    } 
		
}
