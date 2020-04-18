package com.yhzn.common.util.zncb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeSet;

import oracle.sql.CLOB;

public class operateDB {

	public static int flag=0;
	//获取数据
	public static ArrayList<ArrayList<String>> getRelationData(String s) throws Exception
    {
    	String sql="select t.entity1,t.entity2 from T_GXC_RELATION t where   del=0 ";
    			if(flag==1)
    			{
    		    	 sql="select t.entity1,t.entity2 from T_GXC_RELATION_dj  t where  ";

    			}
    	System.out.println(sql);
    	Connection conn = Connections.getConnection();
    	PreparedStatement pst = conn.prepareStatement(sql);// 准备执行语句
		 ResultSet bset = pst.executeQuery();
		 ArrayList<ArrayList<String>> res=new ArrayList<ArrayList<String>>();
		 while (bset.next()) {
			 ArrayList<String> tmp=new ArrayList<String>();
				//String id = bset.getString("id");
				String entity1 = bset.getString("entity1");	
				String entity2 = bset.getString("entity2");	
				String gxch = bset.getString("gxch");//增量用这个去取数据
				//tmp.add(id);
				tmp.add(entity1);
				tmp.add(entity2);
				tmp.add(gxch);
				res.add(tmp);
		 }
		 
		// pst1.close();
		// conn1.close();
		 bset.close();
		 pst.close();
		 conn.close();
	      return res;
    }
	public static String updateData(String entitys) throws Exception
    {
		//根据这一串id去找到实体表 如果有多个串号（不会出现） 
		//若果多个取第一个 全部更新为第一个
		//若全部为空  直接更新  第一次 肯定全部为空
		//如果 增量的时候 有可能全空  有可能部分空  把部分空的用原来的id替换  关系串 则 id1 和id2都 in 这一坨 则更新  规则一样 有则替换 无则新增
		//可以写成一个函数 去调用

       //由于数据库字符串4000的现在  现在将ids插入到临时表的clob字段 再去instr检索
		String res=null;
		String sql=null;
		if(entitys.length()>=4000)
		{
			String id=insertClob( entitys);
	    	sql="select UPDATEGXCHBYIDS('"+id+"') as  res  from dual";
	    	if(flag==1)
			{
	    		sql="select UPDATEGXCHBYIDS_DJ('"+id+"')  as res from dual";
	
			}
		}
		else
		{
			sql="select UPDATEGXCHBYIDS_XYSQ('"+entitys+"') as  res  from dual";
	    	if(flag==1)
			{
	    		sql="select UPDATEGXCHBYIDS_DJ_XYSQ('"+entitys+"')  as res from dual";
	
			}
		}
    	//System.out.println(sql);
    	Connection conn = Connections.getConnection();
    	
    	PreparedStatement pst = conn.prepareStatement(sql);// 准备执行语句
    	
		 ResultSet bset = pst.executeQuery();
		 //ArrayList<ArrayList<String>> res=new ArrayList<ArrayList<String>>();
		 while (bset.next()) {
			  res=bset.getString("res");
		 }
		 
		// pst1.close();
		// conn1.close();
		 bset.close();
		 pst.close();
		 conn.close();
		 //System.out.println(res);
	      return res;
    }
	
	public static String insertClob(String ENTITYIDS) throws Exception
    {


       //由于数据库字符串4000的现在  现在将ids插入到临时表的clob字段 再去instr检索
    	
    	String type="ql";
    	if(flag==1)
		{
    		type="dj";
		}
    	String id = type+System.currentTimeMillis();
    	String insert="insert into TMPIDSCLOB (ID, ENTITYIDS, TYPE, UPDATETIME)" +
    			"values ('"+id+"', empty_clob(), '"+type+"', sysdate)";
    	
    	String forupdate="select ENTITYIDS  from  TMPIDSCLOB  where id='"+id+"' for update";
    	
    	String update="update  TMPIDSCLOB set ENTITYIDS=?  where id='"+id+"'";
    	//System.out.println(ids);
    	Connection conn = Connections.getConnection();
    	conn.setAutoCommit(false);
    	Statement sta=conn.createStatement();
    	Statement st=conn.createStatement();
    	st.executeUpdate(insert);
    	conn.commit();
    	PreparedStatement pst = null;// 准备执行语句
		 ResultSet bset = sta.executeQuery(forupdate);
		 //ArrayList<ArrayList<String>> res=new ArrayList<ArrayList<String>>();
		 while (bset.next()) {
			 CLOB clob=(CLOB)bset.getClob("ENTITYIDS");
			// System.out.println(clob);
			 clob.putString(1, ENTITYIDS);
			 pst=conn.prepareStatement(update);
			 pst.setClob(1, clob);
			 pst.executeUpdate();
		 }
		 
		// pst1.close();
		// conn1.close();
		 conn.commit();
		 bset.close();
		 pst.close();
		 st.close();
		 sta.close();
		 conn.close();
	      return id;
    }
	
	
	//将数据入库
	public static String intoDataToDb(String w) throws Exception
    {
		
	    	
	    	
		String res=null;
		String	sql="select fn_cb_entity_all_run1('w')  from dual";
	
    	//System.out.println(sql);
    	Connection conn = Connections.getConnection();
    	
    	PreparedStatement pst = conn.prepareStatement(sql);// 准备执行语句
    	
		 ResultSet bset = pst.executeQuery();
		 //ArrayList<ArrayList<String>> res=new ArrayList<ArrayList<String>>();
		 while (bset.next()) {
			  res=bset.getString("res");
		 }
		 
		// pst1.close();
		// conn1.close();
		 bset.close();
		 pst.close();
		 conn.close();
		 //System.out.println(res);
	      return res;
    }
	
	
	
	//增量使用
	public static TreeSet<String> getEntityData(String s) throws Exception
    {
    	String sql=" select id from (select distinct ttg.entity1 as id  from t_gxc_relation ttg" +
    			" where ttg.gxch is null" +
    			" union" +
    			" select distinct ttg.entity2 as id" +
    			" from t_gxc_relation ttg where ttg.gxch is null)";
    			if(flag==1)
    			{
    				sql=" select id from (select distinct ttg.entity1 as id  from t_gxc_relation_dj ttg" +
    		    			" where ttg.gxch is null" +
    		    			" union" +
    		    			" select distinct ttg.entity2 as id" +
    		    			" from t_gxc_relation_dj ttg where ttg.gxch is null)";
    			}
    	System.out.println(sql);
    	Connection conn = Connections.getConnection();
    	
    	PreparedStatement pst = conn.prepareStatement(sql);// 准备执行语句
    	
		 ResultSet bset = pst.executeQuery();
		 TreeSet<String> res=new TreeSet<String>();
		 while (bset.next()) {
				String id = bset.getString("id");	
				res.add(id);
		 }
		 
		 bset.close();
		 pst.close();
		 conn.close();
	      return res;
    }
}
