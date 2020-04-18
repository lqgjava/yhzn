package com.yhzn.common.util;

import java.io.File;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.lang.reflect.Field;
 
import javax.servlet.http.HttpServletResponse;
 
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**   
* 
* @ClassName: ExportExcel.java
* @Description: excel导出功能
*
* @version: v1.0.0
* @author: mayn
* @date: 2019年4月2日 上午11:43:31 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年4月2日     mayn           v1.0.0               修改原因
*/
public class ExportExcel {
	/**
	 * @param fileName
	 *            EXCEL文件名称
	 * @param listTitle
	 *            EXCEL文件第一行列标题集合
	 * @param listContent
	 *            EXCEL文件正文数据集合
	 * @return
	 */
	public final static String exportExcel(String fileName, String[] Title,
			Object listContent, HttpServletResponse response,String title,Integer colWidth) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			// 定义输出流，以便打开保存对话框_______________________end
 
			/** **********创建工作簿************ */
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			/** **********创建工作表************ */
 
			WritableSheet sheet = workbook.createSheet("Sheet1", 0);
 
			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);
 
			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD);
 
			/** ************以下设置三种单元格样式，灵活备用************ */
			// 用于标题居中
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(false); // 文字是否换行
 
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
			/** ***************以下是EXCEL单元格宽度,省略********************* */
			/** ***************以下是EXCEL开头大标题，暂时省略********************* */
			 if(colWidth == null){
				 colWidth=21; 
			 }
			 sheet.mergeCells(0, 0, colWidth, 0);
			 sheet.addCell(new Label(0, 0, title, wcf_center));
			/** ***************以下是EXCEL第一行列标题********************* */
			for (int i = 0; i < Title.length; i++) {
				sheet.setColumnView(i, Title.length+6);//设置宽度
				sheet.addCell(new Label(i, 1, Title[i], wcf_center));
			}
			/** ***************以下是EXCEL正文数据********************* */
			Field[] fields = null;
			int i = 2;
			for (Object obj : (List)listContent) {
				fields = obj.getClass().getDeclaredFields();
				int j = 0;
				for (Field v : fields) {
					v.setAccessible(true);
					Object va = v.get(obj);
					if (va == null) {
						va = "";
					}
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if(va instanceof Timestamp||va instanceof Date){
						sheet.addCell(new Label(j, i, sf.format(va), wcf_center));
					}else{
						sheet.addCell(new Label(j, i, va.toString(), wcf_center));
						
					}
 
					j++;
 
				}
 
				i++;
 
			}
 
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
 
			workbook.write();
 
			/** *********关闭文件************* */
 
			workbook.close();
 
		} catch (Exception e) {
 
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
 
			System.out.println(result);
 
			e.printStackTrace();
 
		}
 
		return result;
 
	}
	

}
