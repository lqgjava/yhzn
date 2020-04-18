package com.yhzn.service.impl.person;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.dao.common.CommonPhotoDao;
import com.yhzn.dao.person.PersonDao;
import com.yhzn.model.common.CommonPhoto;
import com.yhzn.model.person.Person;
import com.yhzn.model.security.User;
import com.yhzn.service.person.PersonService;

/**
 * 人员管理实现类
 * @author liany
 *
 */
@Service
public class PersonServiceImpl implements PersonService {

	
	//人员管理dao
	@Resource
	private PersonDao personDao;
	
	//通用图片dao
	@Resource
	private CommonPhotoDao commonPhotoDao;

	/**
	 * 查询人员信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	@Override
	public List<Person> queryPersonList(PageBounds bounds,Map<String, Object> parameter) {
		return personDao.queryPersonList(bounds, parameter);
	}
	
	/**
	 * 新增人员信息
	 * @param person
	 * @param user
	 */
	public void insertPersonInfo(Person person, User user) {
		MultipartFile uploadFile = (MultipartFile) person.getImage();
	   person.setId(UUID.randomUUID().toString().replaceAll("-", ""));//id
	   person.setCreateName(user.getTrueName());//创建人姓名
	   person.setPhotoId(UUID.randomUUID().toString().replaceAll("-", ""));//照片id
	   //待遇
	   if(StringUtils.isNotBlank(person.getPaymentStr())){
		   person.setPayment(Integer.parseInt(person.getPaymentStr()));
	   }
	   //其他待遇
	   if(StringUtils.isNotBlank(person.getOtherPayStr())){
		   person.setOtherPay(Integer.parseInt(person.getOtherPayStr()));
	   }
	   //图片路径不为空则插入图片
	   if(uploadFile.getSize()>0){
		   insertCommonPhotoInfo(person,user,uploadFile);
	   }
	   personDao.insertPersonInfo(person);
		
	}

	/**
	 * 删除人员信息
	 * @param id
	 */
	@Override
	public void deletePersonById(String id) {
		personDao.deletePersonById(id);
	}

	/**
	 * 修改人员信息
	 * @param person
	 * @param user
	 */
	public void editPersonInfo(Person person, User user) {
		MultipartFile uploadFile = (MultipartFile) person.getImage();
		
		person.setModifyName(user.getTrueName());//修改人姓名
		//待遇
	    if(StringUtils.isNotBlank(person.getPaymentStr())){
		   person.setPayment(Integer.parseInt(person.getPaymentStr()));
	    }
	    //其他待遇
	    if(StringUtils.isNotBlank(person.getOtherPayStr())){
		   person.setOtherPay(Integer.parseInt(person.getOtherPayStr()));
	    }
		//判断头像是否更换
		if(uploadFile.getSize()>0){
			commonPhotoDao.deleteCommonPhotoInfo(person.getPhotoId());//删除原来的照片
			person.setPhotoId(UUID.randomUUID().toString().replaceAll("-", ""));//照片id
			insertCommonPhotoInfo(person,user,uploadFile);//新增照片
		}
		personDao.editPersonInfo(person);
	}
	
	/**
	 * 新增人员照片
	 * @param person
	 * @param user
	 * @throws IOException 
	 */
	public void  insertCommonPhotoInfo(Person person, User user,MultipartFile uploadFile) {
		InputStream in = null;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
		CommonPhoto commonPhoto = new CommonPhoto();
		 //获得图片文件的byte数组  
      /*  byte[] buffer = null;  
        String imgPath=person.getFilePath();
        try {  
            File file = new File(imgPath);  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);  
            byte[] b = new byte[1024];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  */
			in=uploadFile.getInputStream();
	        byte[] buffer = new byte[4096];
	        int n = 0;
	        while (-1 != (n = in.read(buffer))) {
	            output.write(buffer, 0, n);
	        }
			commonPhoto.setImage(output.toByteArray());
	        commonPhoto.setId(person.getPhotoId());//id
	        commonPhoto.setParentId(person.getId());//关联id
	        commonPhoto.setCreateName(user.getTrueName());//创建人姓名
	        commonPhoto.setPhotoName(uploadFile.getOriginalFilename());//图片名称
	        commonPhotoDao.insertCommonPhotoInfo(commonPhoto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				output.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
    /**  
     * 对象转数组  
     * @param obj  
     * @return  
     */  
    public byte[] toByteArray (Object obj) {      
        byte[] bytes = null;      
        ByteArrayOutputStream bos = new ByteArrayOutputStream();      
        try {        
            ObjectOutputStream oos = new ObjectOutputStream(bos);         
            oos.writeObject(obj);        
            oos.flush();         
            bytes = bos.toByteArray ();      
            oos.close();         
            bos.close();        
        } catch (IOException ex) {        
            ex.printStackTrace();   
        }      
        return bytes;    
    }   
	
	/**
	 * map查询用户信息列表
	 * @param map
	 * @return
	 */
	public List<Person> queryPersonListByMap( Map<String, Object> map){
		return personDao.queryPersonListByMap(map);
	}
}
