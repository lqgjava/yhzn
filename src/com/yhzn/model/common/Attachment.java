package com.yhzn.model.common;


/**   
* 
* @ClassName: Attachment.java
* @Description:附件信息
*
* @version: v1.0.0
* @author: mayn
* @date: 2019年3月26日 下午12:59:30 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年3月26日     mayn           v1.0.0               修改原因
*/
public class Attachment {

	private String id;

	private String type;

	private String fileName;

	private String filePath;

	private Long fileSize;

	private String suffix;

	private String contentType;

	private String uploadDate;
	private String newName;
	private String parentId;

	public Attachment() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}



	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Attachment(String id, String type, String fileName, String filePath, Long fileSize, String suffix,
			String contentType, String uploadDate, String newName,String parentId) {
		super();
		this.id = id;
		this.type = type;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.suffix = suffix;
		this.contentType = contentType;
		this.uploadDate = uploadDate;
		this.newName = newName;
		this.parentId=parentId;
	}

	
}
