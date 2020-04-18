package com.yhzn.common.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.yhzn.model.common.Attachment;

/**
 * 
 * @version 创建时间：2010-6-8 下午02:58:29
 * @Description
 */

public class FileUpload {
 public static Attachment upload(MultipartFile file,String uploadDir, String type) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy\\MM\\dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
		//原文件名
		String oldname = file.getOriginalFilename();
		//获取文件的后缀
		String filetype=oldname.substring(oldname.lastIndexOf(".")+1,oldname.length());
		//新文件名
		String newName=UUID.randomUUID().toString().replaceAll("-", "")+"."+filetype;
		//文件上传
		FileUtils.writeByteArrayToFile(new File(uploadDir + type +"\\" + sdf.format(new Date()) +"\\"+newName ),
				file.getBytes());
		Attachment att = new Attachment();
		//文件类型（mm模板）
		att.setContentType(type);
		//原文件名
		att.setFileName(file.getOriginalFilename());
		//文件路径
		att.setFilePath( type +"\\" + sdf.format(new Date()) +"\\"+newName);
		//新文件名
		att.setNewName(newName);
		//文件后缀
		att.setSuffix(filetype);
		//文件大小
		att.setFileSize(file.getSize());
		//增加时间
		att.setUploadDate(sdf1.format(new Date()));
		return att;

		
	}

}
