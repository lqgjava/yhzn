package com.yhzn.web.controller.person;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.yhzn.common.controller.BaseController;
import com.yhzn.common.page.PageUtil;
import com.yhzn.model.common.CommonPhoto;
import com.yhzn.model.person.Person;
import com.yhzn.model.security.SysDict;
import com.yhzn.model.security.User;
import com.yhzn.service.common.CommonPhotoService;
import com.yhzn.service.person.PersonService;
import com.yhzn.service.security.SysDictService;
import com.yhzn.service.security.SysLogService;


/**
 * 
 * @author lin
 * 人员管理处理类
 */
 
@Controller
@RequestMapping("/person")
public class PersonController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	//日志管理接口
	@Autowired
	private SysLogService sysLogService;
	//人员管理接口
	@Autowired
	private PersonService personService;
	//通用图片管理接口
	@Autowired
	private CommonPhotoService commonPhotoService;
	//字典管理接口
	@Autowired
	private SysDictService sysDictService;

	
	@RequestMapping(value="/queryPersonList", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil queryPersonList(HttpServletRequest request){
		//获取登录人信息 
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("查询",user.getTrueName(),"查询人员信息列表 ",user.getLoginIp(),"/person/queryPersonList");

		
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		String name = request.getParameter("name");//姓名
		String dept = request.getParameter("dept");//部门
		String idCard = request.getParameter("idCard");//身份证号
		String ifJob = request.getParameter("ifJob");//在岗情况
		String beginDate = request.getParameter("beginDate");//开始时间
		String endDate = request.getParameter("endDate");//结束时间
		
		Map<String,Object> parameter = new HashMap<String,Object>();
			parameter.put("name", name);
			parameter.put("dept", dept);
			parameter.put("idCard", idCard);
			parameter.put("ifJob", ifJob);
			parameter.put("beginDate", beginDate);
			parameter.put("endDate", endDate);

		PageBounds bounds = new PageBounds(page , rows );
		List<Person> list = personService.queryPersonList(bounds, parameter);
		// 获得结果集条总数
		int total = ((PageList<Person>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);
		
		return result;

	}
	
	
	/**
	 * 删除员工信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletePerson")  
    @ResponseBody  
    public Map<String,Object> deletePersonById(HttpServletRequest request) {  
		 Map<String,Object> map = new HashMap<String,Object>();  
	    //修改删除标记del为 1
	    String ids = request.getParameter("ids");
	    //获取登录人信息    
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("删除",user.getTrueName(),"删除人员信息: "+ids,user.getLoginIp(),"/person/deletePerson");
       
        String[] idArray = ids.split(",");
        if(null!=ids && idArray.length>0){
        	for(int i=0;i<idArray.length;i++){
        		personService.deletePersonById(idArray[i]); 
        	}
        }
        map.put("success", 1);  
        return map;  
    }
	
	/**
	 * 新增员工信息
	 * @param person
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/addPerson",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addPerson(Person person,HttpServletRequest request) throws IOException{
	  //获取登录人信息
	  User user= (User) request.getSession().getAttribute("user");
      //日志类型，操作人，操作内容，操作人IP,操作方法
	  sysLogService.insertSysLog("新增",user.getTrueName(),"新增人员信息： "+person.getName(),user.getLoginIp(),"/person/addPerson");
	  Map<String,Object> map = new HashMap<String,Object>();  
      //新增人员信息
	  personService.insertPersonInfo(person,user);
      map.put("success", 1);  
      return map;  
	}
	
	/**
	 * 修改员工信息
	 * @param person
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editPerson",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> editPerson(Person person,HttpServletRequest request){
		
	   Map<String,Object> map = new HashMap<String,Object>();  
	   //获取登录人信息
	   User user= (User)request.getSession().getAttribute("user");
       //日志类型，操作人，操作内容，操作人IP,操作方法
	   sysLogService.insertSysLog("修改",user.getTrueName(),"修改人员信息： "+person.getName(),user.getLoginIp(),"/person/editPerson");
	   //changePerson(person,request);
	   personService.editPersonInfo(person,user);
       map.put("success", 1);  
       return map;  
	}
	
	/**
	 * 获取数据库图片
	 * @param id
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/getPhoto",method= RequestMethod.GET)
	@ResponseBody
	public void getPhoto(String id,HttpServletRequest request,HttpServletResponse response) throws IOException{
		CommonPhoto commonPhoto = commonPhotoService.findCommonPhotoById(id);
		Blob blob = (Blob) commonPhoto.getImage();
		InputStream ips=null;
        ServletOutputStream out=null;
            try {
            	ips=blob.getBinaryStream();
            	out = response.getOutputStream();
    			response.setContentType("multipart/form-data");
    			response.setCharacterEncoding("utf-8");
    			int len=0;
				byte[] buffer = new byte[1024 * 5];
				while ((len = ips.read(buffer)) != -1){
					out.write(buffer,0,len);
				}
				out.flush();
    			}catch (Exception e){
    				e.printStackTrace();
    			}finally {
    				out.close();
    				ips.close();
    			}
	}
	
	/**
 	 * 将blob转化为byte[],可以转化二进制流的
 	 * 
 	 * @param blob
 	 * @return
 	 */
 	private byte[] blobToBytes(Blob blob) {
 		InputStream is = null;
 		byte[] b = null;
 		try {
 			is = blob.getBinaryStream();
 			b = new byte[(int) blob.length()];
 			is.read(b);
 			return b;
 		} catch (Exception e) {
 			e.printStackTrace();
 		} finally {
 			try {
 				is.close();
 				is = null;
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 		}
 		return b;
 	}
	
 	
	/**
	 * 获取数据库图片
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getFile",method= RequestMethod.POST)
	@ResponseBody
	public void getFile(MultipartFile uploadFile,HttpServletRequest request){
		System.out.println(uploadFile.getName());
	}
	
	 /**
		 * 获取部门类别
		 * @param type
		 * @return
	     * @throws IOException 
		 */
	    @RequestMapping(value="/getPersonTypeList",method= RequestMethod.GET)
	    @ResponseBody  
	    public String getPersonTypeList(String type,HttpServletRequest request) throws IOException {
	    	String str ="";  
	        StringBuilder json = new StringBuilder();  
	        HashMap<String,Object> map = new HashMap<String,Object>();
	        map.put("rootKey", "BMLBDM");
	        map.put("parentKey", "BMLBDM");
	        // 获取客户行业类别字典代码
	        List<SysDict> sysDictList = sysDictService.findDictListByMap(map);
	        // 遍历子节点下的子节点  
	        if(sysDictList!=null && sysDictList.size()!=0){  
	            json.append("[");  
	            for (SysDict s : sysDictList) {  
	                json.append("{\"id\":\"" +s.getDictValue()+ "\"");   
	                json.append(",\"text\":\"" +s.getDictValue() + "\"");   
	                if(s.getDictValue().equals(type)){
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
	    
	    /**
	     * 页面获取的表单数据转换成对象
	     * @param person
	     * @param request
	     * @return
	     */
	    public Person changePerson(Person person,HttpServletRequest request){
	    	 try {
	 		  Map<String,Object> map = new HashMap<String,Object>();  
	 		  String fieldName="";//表单中的空间name值
	 		  String fieldValue="";//该name值空间中的value值
	           DiskFileItemFactory factroy = new DiskFileItemFactory();
	           //创建文件上传解析器
	           ServletFileUpload upload = new ServletFileUpload(factroy);
	           //判断前端form表单是否为enctype="multipart/form-data"属性
	           boolean isF = ServletFileUpload.isMultipartContent(request);
	           //如果前端传过来的是表单是enctype="multipart/form-data"属性
	           if (isF) {
	               //使用解析器解析上传的表单数据，每个FileItem对应一个表单项
	               List<FileItem> fileItemList = upload.parseRequest(request);
	               
	               for (FileItem fileItem : fileItemList) {
	                   if (!fileItem.isFormField()) {
	                       //不是普通的表单项，即是上传的是文件
	                       //获取文件名称
	                       String fileName = fileItem.getName();
	                       if (fileName.isEmpty()) {
	                           //如果文件名为空
	                           continue;
	                       }
	                       //文件名称
	                       person.setFileName(fileName);
	                       //获取上传文件输入流
	                       InputStream in = fileItem.getInputStream();
	                       ByteArrayOutputStream output = new ByteArrayOutputStream();
	                       byte[] buffer = new byte[4096];
	                       int n = 0;
	                       while (-1 != (n = in.read(buffer))) {
	                           output.write(buffer, 0, n);
	                       }
	                       output.close();
	                       in.close();
	                       //转换为二进制
	                       person.setImage(output.toByteArray());
	                       //FileItem对象被垃圾收集器收集时会自动清除临时文件，但及时调用delete方法可以更早的清除临时文件，释放系统存储资源。
	                       // 另外，当系统出现异常时，仍有可能造成有的临时文件被永久保存在了硬盘中。
	                       fileItem.delete();
	                   } else {
	                       //获取表单中的非文件值
	                 	  fieldName=fileItem.getFieldName();
	                 	  fieldValue=fileItem.getString("UTF-8");
//	                 	  System.out.println(fieldName);
//	                 	  System.out.println(fieldValue);
	                 	  //判断fieldName的值，为对象中的属性赋值
	                 	 if(("id").equals(fieldName)){//id
	                 		  person.setId(fieldValue);
	                 	  }else if(("personNo").equals(fieldName)){//人员编号
	                 		  person.setPersonNo(fieldValue);
	                 	  }else if(("photoId").equals(fieldName)){//照片id
//	                 		  System.out.println(fieldName+"    "+fieldValue);
	                 		  person.setPhotoId(fieldValue);
	                 	  }else if(("name").equals(fieldName)){//姓名
	                 		  person.setName(fieldValue);
	                 	  }else if(("sex").equals(fieldName)){//性别
	                 		  person.setSex(fieldValue);
	                 	  }else if(("idCard").equals(fieldName)){//身份证号
	                 		  person.setIdCard(fieldValue);
	                 	  }else if(("volk").equals(fieldName)){//名族
	                 		  person.setVolk(fieldValue);
	                 	  }else if(("nativePlace").equals(fieldName)){//籍贯
	                 		  person.setNativePlace(fieldValue);
	                 	  }else if(("birthDateStr").equals(fieldName)){//出生日期
	                 		  person.setBirthDateStr(fieldValue);
	                 	  }else if(("phoneNo").equals(fieldName)){//电话号码
	                 		  person.setPhoneNo(fieldValue);
	                 	  }else if(("paymentStr").equals(fieldName)){//工资
	                 		  person.setPaymentStr(fieldValue);
	                 	  }else if(("otherPayStr").equals(fieldName)){//其他待遇
	                 		  person.setOtherPayStr(fieldValue);
	                 	  }else if(("entryDateStr").equals(fieldName)){//入职时间
	                 		  person.setEntryDateStr(fieldValue);
	                 	  }else if(("dept").equals(fieldName)){//所属部门
	                 		  person.setDept(fieldValue);
	                 	  }else if(("ifJob").equals(fieldName)){//在岗情况
	                 		  person.setIfJob(fieldValue);
	                 	  }else if(("remark").equals(fieldName)){//备注
	                 		  person.setRemark(fieldValue);
	                 	  }
	                   }
	               }
	           }
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
			return person;
	    }
}
