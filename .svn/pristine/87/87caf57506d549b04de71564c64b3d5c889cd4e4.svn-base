package com.yhzn.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.yhzn.common.page.PageUtil;
import com.yhzn.common.util.ConfigUtil;
import com.yhzn.common.util.Excel2Pdf;
import com.yhzn.common.util.Word2Pdf;
import com.yhzn.model.common.Attachment;
import com.yhzn.service.common.AttachmentService;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController {

	@Autowired
	private AttachmentService attachmentService;
	String uploadDir = ConfigUtil.getConfig("upload.dir");

	/**
	 * 文件浏览页
	 * 
	 * @param parentId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/fileList", method = RequestMethod.GET)
	public String filePage(String parentId, Model model) {
		model.addAttribute("parentId", parentId);
		return "/common/fileList";

	}

	/**
	 * 文件数量
	 * 
	 * @param parentId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/QueryFileByParentId")
	@ResponseBody
	public PageUtil QueryFileByParentId(String parentId, @RequestParam(name = "page", defaultValue = "1") int pageNum,
			@RequestParam(name = "rows", defaultValue = "10") int pageSize) {
		PageBounds bounds = new PageBounds(pageNum, pageSize);

		List<Attachment> list = attachmentService.QueryFileByParentId(parentId, bounds);
		int total = ((PageList<Attachment>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);

		return result;

	}

	/**
	 * 下载
	 * 
	 * @param request
	 * @param response
	 * @param attId
	 * @throws IOException
	 */
	@GetMapping("/download/{id}")
	public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id)
			throws IOException {
		// 根据id获取附件信息
		Attachment att = attachmentService.get(id);

		if (att == null) {
			// 附件不存在的时候，直接返回404
			response.sendError(404);
			return;
		}

		// 设置下载响应头信息
		// response.setContentLengthLong(att.getFileSize());
		response.setContentType(att.getContentType());
		// 设置附件存储的默认文件名
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(att.getFileName().getBytes("UTF-8"), "ISO8859-1"));

		String path = uploadDir + att.getFilePath();
		ServletOutputStream os = response.getOutputStream();

		// 创建一个输入流
		FileInputStream fis = new FileInputStream(path);
		// 每次读取的字节数
		byte[] b = new byte[2048];
		// 每次实际读取到的字节长度
		int len = 0;
		while ((len = fis.read(b)) > 0) {
			// 进入循环，表示读取到了字节
			// 读到多少，就写出多少
			os.write(b, 0, len);
		}
		os.close();
		fis.close();
	}

	/**
	 * 删除文件
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteFile/{id}")
	@ResponseBody
	public JSONObject deleteFile(@PathVariable("id") String id) {
		JSONObject json = new JSONObject();
		try {
			attachmentService.deleteFileById(id);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			json.put("success", false);
			return json;
		}
		json.put("success", true);

		return json;
	}
	/**
	 * 图片预览界面
	 */
	@GetMapping("/img")
	public String img(String id, Model model) {
		model.addAttribute("id", id);
		return "/common/img2";
	}
	/**
	 * 预览图片
	 */
	@GetMapping("previewImg")
	public void previewImg(HttpServletRequest request, HttpServletResponse response,String id) throws IOException {
		
		// 根据id获取附件信息
		Attachment att = attachmentService.get(id);
		if (att == null) {
			// 附件不存在的时候，直接返回404
			response.sendError(404);
			return;
		}
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(att.getFileName(), "UTF-8"));
		String path = uploadDir + att.getFilePath();
		ServletOutputStream os = response.getOutputStream();
		File f = new File(path);
		if (!f.exists()) {
			// 附件不存在的时候，直接返回404
			response.sendError(404);
			return;
		}

		// 创建一个输入流
		FileInputStream fis = new FileInputStream(f);
		// 每次读取的字节数
		byte[] b = new byte[2048];
		// 每次实际读取到的字节长度
		int len = 0;
		while ((len = fis.read(b)) > 0) {
			// 进入循环，表示读取到了字节
			// 读到多少，就写出多少
			os.write(b, 0, len);
		}

		fis.close();
	}
	/**
	 * word和xlsx预览
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@GetMapping("/preview/{id}")
	public void preview(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id)throws Exception {
		// 根据id获取附件信息
		Attachment att = attachmentService.get(id);
		if (att == null) {
			// 附件不存在的时候，直接返回404
			response.sendError(404);
			return;
		}
		if ("docx".equals(att.getSuffix()) || "doc".equals(att.getSuffix())) {
			previewWord(request, response, att);
		} else if ("xlsx".equals(att.getSuffix()) || "xls".equals(att.getSuffix())) {
			previewExcel(request, response, att);
		} else if("pdf".equals(att.getSuffix())){
			previewPdf(request, response, att);
		}
	}

	/**
	 * word预览 DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void previewWord(HttpServletRequest request, HttpServletResponse response, Attachment att) throws Exception {
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("word", "UTF-8"));
		String path = uploadDir + att.getFilePath();

		FileInputStream fis = new FileInputStream(path);
		OutputStream out = response.getOutputStream();
		Word2Pdf.word2pdf(fis, out);

		out.close();
	}

	/**
	 * excel预览
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void previewExcel(HttpServletRequest request, HttpServletResponse response, Attachment att)
			throws Exception {
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("excel", "UTF-8"));
		String path = uploadDir + att.getFilePath();
		FileInputStream fis = new FileInputStream(path);
		OutputStream out = response.getOutputStream();
		Excel2Pdf.excel2pdf(fis, out);

		fis.close();
		out.close();
	}

	/**
	 * pdf预览
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void previewPdf(HttpServletRequest request, HttpServletResponse response,Attachment att) throws IOException {
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("pdf", "UTF-8"));
		String path = uploadDir + att.getFilePath();
		FileInputStream fis = new FileInputStream(path);
		OutputStream out = response.getOutputStream();
		byte[] bs = new byte[1024];
		int len = 0;
		while ((len = fis.read(bs)) != -1) {
			out.write(bs, 0, len);
		}
		fis.close();
		out.close();
	}
}
