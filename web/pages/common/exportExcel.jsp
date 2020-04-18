<%@ page language="java" import="java.io.*" pageEncoding="UTF-8"%>
<%
	String filename = (String)request.getSession().getAttribute("filename");
	
	out.clear();
	File file = new File(request.getRealPath("/download/"+filename));
	InputStream inStream=new FileInputStream(file);
	ServletOutputStream  outStream=response.getOutputStream();
      response.reset(); 
      response.setIntHeader("Content-Length",(int)file.length());
		response.setHeader("Content-Transfer-Encoding","8bit");
		response.setHeader("Content-Disposition","attachment; filename=" + file.getName() + "");
		response.setContentType("application/msword; name="+file.getName());
      byte[] b = new byte[10240];
      int len;
      while((len=inStream.read(b)) >0)
        outStream.write(b,0,len);  
      inStream.close();
      outStream.close();
      if(file.exists()){
			file.delete();
		}	
%>