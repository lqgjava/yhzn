package com.yhzn.common.util;

import com.aspose.cells.License;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * ExcelPdf
 * @author zlm
 *
 */
public class Excel2Pdf {
    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = Excel2Pdf.class.getClassLoader().getResourceAsStream("license.xml"); 
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


   
    public static void excel2pdf(InputStream inputStream, OutputStream outputStream) throws Exception{
        if (!getLicense()) {          // 验证License 若不验证则转化出的pdf文档会有水印产生
            return;
        }
        Workbook wb = new Workbook(inputStream);
        wb.save(outputStream, SaveFormat.PDF);
    }
}
