package com.yhzn.web.controller.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.yhzn.model.security.SysDict;
import com.yhzn.model.security.User;
import com.yhzn.service.security.SysDictService;
import com.yhzn.service.security.SysLogService;

/**
 * 
 * @author Administrator
 * 字典处理公共类
 */
 
@Controller
@RequestMapping("/sysDict")
public class SysDictController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(SysDictController.class);

	//角色管理接口
	@Autowired
	private SysDictService sysDictService;
	//日志管理接口
	@Autowired
	private SysLogService sysLogService;
	
	@RequestMapping(value="/getDictValue",method= RequestMethod.POST)
	public void getDictValue() throws IOException{
		String rootKey = this.getRequest().getParameter("rootKey");
		HashMap paraMap = new HashMap();
		String id = this.getRequest().getParameter("id");
		if(id == null){
			id = rootKey;
		}
		paraMap.put("rootKey", rootKey);
		paraMap.put("parentKey", id);
		List<SysDict> list = sysDictService.getDictValue(paraMap);
		//将数据处理成easyui的
		ArrayList tempList = new ArrayList();
		if(list != null){
			for(int i=0;i<list.size();i++){
				SysDict dict = list.get(i);
				HashMap resMap = new HashMap();
				resMap.put("id", dict.getId());
				resMap.put("text", dict.getText());
				if("1".equals(dict.getIsLeaf())){
					resMap.put("state", "open");
				}else{
					resMap.put("state", "closed");
				}
				tempList.add(resMap);
			}
		}
		JSONArray json = JSONArray.fromObject(tempList);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(json);
	}
	
	/**
	 * 查询字典信息列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/querySysDictList", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil querySysDictList(HttpServletRequest request){
		User user= (User) request.getSession().getAttribute("user");
		//日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("查询",user.getTrueName(),"查询字典列表信息",user.getLoginIp(),"/sysDict/querySysDictList");
		
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		String dictValue = request.getParameter("dictValue");//字典名称
		String parentKey = request.getParameter("parentKey");//父节点代号
		String rootKey = request.getParameter("rootKey");//根节点代号
		String dictLevel = request.getParameter("dictLevel");//字典等级
		
		Map<String,Object> parameter = new HashMap<String,Object>();
			parameter.put("dictValue", dictValue);
			parameter.put("parentKey", parentKey);
			parameter.put("rootKey", rootKey);
			parameter.put("dictLevel", dictLevel);
		PageBounds bounds = new PageBounds(page , rows );
		List<SysDict> list = sysDictService.querySysDictList(bounds, parameter);
		// 获得结果集条总数
		int total = ((PageList<SysDict>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);
		
		return result;

	}
	
	
	/**
	 * 查询子字典信息列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/querySysChildDictList", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil querySysChildDictList(HttpServletRequest request){
		User user= (User) request.getSession().getAttribute("user");
		//日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("查询",user.getTrueName(),"查询子字典列表信息",user.getLoginIp(),"/sysDict/querySysChildDictList");
		
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		String dictValue = request.getParameter("dictValue");//字典名称
		String parentKey = request.getParameter("parentKey");//父节点代号
		String rootKey = request.getParameter("rootKey");//根节点代号
		
		Map<String,Object> parameter = new HashMap<String,Object>();
			parameter.put("dictValue", dictValue);
			parameter.put("parentKey", parentKey);
			parameter.put("rootKey", rootKey);
			
		PageBounds bounds = new PageBounds(page , rows );
		List<SysDict> list = sysDictService.querySysDictList(bounds, parameter);
		// 获得结果集条总数
		int total = ((PageList<SysDict>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);
		
		return result;

	}
	
	/**
	 * 删除字典信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteSysDict")  
    @ResponseBody  
    public Map<String,Object> deleteSysDict(HttpServletRequest request) {  
        Map<String,Object> map = new HashMap<String,Object>();  
        String ids = request.getParameter("ids");
        User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("删除",user.getTrueName(),"删除字典id为 "+ids,user.getLoginIp(),"/sysDict/deleteSysDict");
        String[] idArray = ids.split(",");
        if(null!=ids && idArray.length>0){
        	for(int i=0;i<idArray.length;i++){
        		sysDictService.deleteSysDictById(idArray[i]); 
        	}
        }
        map.put("success", 1);  
        return map;  
    }  
	
	
	/**
	 * 新增用户信息
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addSysDict",method= RequestMethod.POST)
	@ResponseBody
	public String addSysDict(SysDict sysDict,HttpServletRequest request){
			
			User user= (User) request.getSession().getAttribute("user");
			//日志类型，操作人，操作内容，操作人IP,操作方法
			sysLogService.insertSysLog("新增",user.getTrueName(),"新增字典代码 "+sysDict.getDictValue(),user.getLoginIp(),"/sysDict/addSysDict");
			int flag = sysDictService.insertSysDict(sysDict,user);
			if (flag > 0) {
				return "1";
			} else {
				return "false";
			}
	}
	
	/**
	 * 修改用户信息
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editSysDict",method= RequestMethod.POST)
	@ResponseBody
	public String editSysDict(SysDict sysDict,HttpServletRequest request){
		User user= (User) request.getSession().getAttribute("user");
		//日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("修改",user.getTrueName(),"修改字典代码 "+sysDict.getDictValue(),user.getLoginIp(),"/sysDict/editSysDict");
		int flag = sysDictService.editSysDict(sysDict,user);
		
		if (flag > 0) {
			return "1";
		} else {
			return "false";
		}
	}
	
	
	/**
	 * 获取字典类别
	 * @param key
	 * @param parentKey
	 * @return
     * @throws IOException 
	 */
    @RequestMapping(value="/getDictList",method= RequestMethod.GET)
    @ResponseBody  
    public String getDictList(String key,String parentKey,HttpServletRequest request) throws IOException {
    	String str ="";  
        StringBuilder json = new StringBuilder();  
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("rootKey", parentKey);
        map.put("parentKey", parentKey);
        // 获取字典代码
        List<SysDict> sysDictList = sysDictService.findDictListByMap(map);
        // 遍历子节点下的子节点  
        if(sysDictList!=null && sysDictList.size()!=0){  
            json.append("[");  
            for (SysDict s : sysDictList) {  
                json.append("{\"id\":\"" +s.getDictValue()+ "\"");   
                json.append(",\"text\":\"" +s.getDictValue() + "\"");   
                if(s.getDictValue().equals(key)){
                	json.append(",\"selected\":"+true);  
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
