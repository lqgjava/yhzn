package com.yhzn.tools;

import java.io.File;
import java.io.FileInputStream;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class propertiesToEasyui {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		readExcleFile();
	}
	public  static void readExcleFile() throws Exception{


            File f=new File("C:\\Users\\Administrator\\Desktop\\dnawt.xlsx");
	        FileInputStream in = new FileInputStream(f); //文件流  
           String myf="{field:'tmpfield',title:'tmptitle',align:'center',width:150 },";

	        //同时支持Excel 2003、2007  
	      //  FileInputStream is = new FileInputStream(excelFile); //文件流  
	        Workbook workbook = WorkbookFactory.create(in); //这种方式 Excel 2003/2007/2010 都是可以处理的  
	        int sheetCount = workbook.getNumberOfSheets();  //Sheet的数量  
	      
	        //遍历每个Sheet  
	        for (int s = 0; s < sheetCount; s++)
	        {
	        	
              
	            Sheet sheet = workbook.getSheetAt(s);  
	            int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数  
	            //遍历每一行  
	            for (int r = 1; r < rowCount; r++) 
	            {  
	                Row row = sheet.getRow(r); 
	                if(row==null){continue;}
	              
	                int cellCount = row.getPhysicalNumberOfCells(); //获取总列数  
	                //遍历每一列  
	                String tmpf=myf;
	                for (int c = 0; c < cellCount; c++) 
	                {  
	                    Cell cell = row.getCell(c);  
	                    String cellValue = null; 
	                    if(cell!=null)
	                    {
		                    int cellType = cell.getCellType();  
		                    
		                    switch(cellType) {  
		                        case Cell.CELL_TYPE_STRING: //文本  
		                            cellValue = cell.getStringCellValue();  
		                            break;  
		                      
		                        case Cell.CELL_TYPE_BOOLEAN: //布尔型  
		                            cellValue = String.valueOf(cell.getBooleanCellValue());  
		                            break;  
		                        case Cell.CELL_TYPE_BLANK: //空白  
		                            cellValue = cell.getStringCellValue();  
		                            break;  
		                        case Cell.CELL_TYPE_ERROR: //错误  
		                            cellValue = "错误";  
		                            break;  
		                        case Cell.CELL_TYPE_FORMULA: //公式  
		                            cellValue = "错误";  
		                            break;  
		                        default:  
		                            cellValue = "错误";  
		                      }  
	                    }
	                    
	                    if(c==0)
	                    {
	                    	tmpf=tmpf.replaceAll("tmpfield", cellValue.trim());
	                    }
	                    if(c==1)
	                    {
	                    	tmpf=tmpf.replace("tmptitle", cellValue.trim());
	                    	if(cellValue.contains("时间")||cellValue.contains("日期"))
	                    	{
	                    		tmpf=tmpf.replace("},", ",formatter:formatDatebox },");
	                    	}
	                    }
	                    
	                   // System.out.print(cellValue + "\t");  
	                   
	                }  
	                System.out.println(tmpf);  
	            
	            }  
	        }		
	
}

}
