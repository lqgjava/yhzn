package com.yhzn.common.util;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.*;
/**
 * Word转Pdf
 * @author zlm
 *
 */
public class Word2Pdf {
    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = Word2Pdf.class.getClassLoader().getResourceAsStream("license.xml"); //  wordlicense.xml应放在..\WebRoot\WEB-INF\classes路径下
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
   

    public static void word2pdf(InputStream inputStream, OutputStream outputStream) throws Exception{
        if (!getLicense()) {          // 验证License 若不验证则转化出的PDP文档会有水印产生
            return;
        }
        Document doc = new Document(inputStream);                    //Address是将要被转化的word文档
      //全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换  
        doc.save(outputStream, SaveFormat.PDF);
    }
}
