package com.yhzn.common.util.zncb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class zncbString {

	/**
	 * @param args
	 */
	private static TreeSet<String> allEntitySet =new TreeSet<String>();
	private static TreeSet<String> resSet =new TreeSet<String>();
	private static TreeSet<String> tmpDidEntitySet =new TreeSet<String>();
	private static String tmpEntitysStr = null;
    private  static String allrunE="";
	private static ArrayList<ArrayList<String>> allRelationsList=null;
	private static int n=0;
	private static int m=0;
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		//public static String cbrk(String ip) throws Exception {
		//业务逻辑  第一次抽
		/*
		 * 1.所有数据拿过来gxch是空的数据
		 * 2、把所有的号的放到set里 去循环找成串的数据 每次都是跑完了的  想办法把跑了的删除掉
		 *   如果第一次的时候中途断了 因为每次都是所有的串所以可以只去找到未成串数据 继续跑 可以多个线程跑 因为跑过的肯定有号了
		 *   则不做更新 每一个串都是完整的
		 *   
		 *   另外用一个字符串 存所有的跑过的结果  如果每次循环的时候去判断  如果不存在 则跑 存在则不跑 可以节省时间
		 *   有可能字符串长度限制 可以试试用set  40w个
		 *   
		 *   
		 *   z增量的时候是  拿到所有的关系实体  但是再去拿一次新增的空的实体  
		 *   dna和指纹脏数据较多 导致跑不动
		 *   
		 */
       //获取数据 list上万个list id  entity1  entity2
		  //operateDB.updateData("5BB9D9615294606AE0530A9A844A606A ");
		Long be = System.currentTimeMillis();
		try {
			allRelationsList=operateDB.getRelationData("");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Long be1 = System.currentTimeMillis();
		System.out.println("获取所有关系数据：" + (be1-be)/1000 + "秒钟,关系数据量:"+allRelationsList.size());

      //  String[] testid={"5BB9D97BAB32606AE0530A9A844A606A","5BB9D97C4153606AE0530A9A844A606A"};
            //首次是这样 增量则要去获取串号为空的实体
    		for(int i=0;i<allRelationsList.size();i++)
    		{
    			ArrayList<String> tmp=allRelationsList.get(i);
    			if(tmp.get(2)==null||tmp.get(2).length()==0)
    			{
	    			if(tmp.get(0)!=null)
	    			{
	    			  allEntitySet.add(tmp.get(0));
	    			}
	    			if(tmp.get(1)!=null)
	    			{
	    			  allEntitySet.add(tmp.get(1));
	    			}
    			}
    		}
        
    		Long be2 = System.currentTimeMillis();
			System.out.println("通过关系整理出所有实体耗时：" + (be2-be1) + "毫秒秒钟,待跑数量:"+allEntitySet.size());
        
			//为了首次提速  
		   // Object[]  tmpStr= allEntitySet.toArray();
			//for(int i=(tmpStr.length-1)/2;i<tmpStr.length-1;i++)
			//for(int i=(tmpStr.length-1)/2;i>0;i--)
			//for(int i=tmpStr.length-1;i>0;i--)
		    for(String entity:allEntitySet)
           {
		    	//String entity="5E7C31832EEC60FEE0530A9A844A60FE";
			//	String entity=(String) tmpStr[i];
        	if(!tmpDidEntitySet.contains(entity))
        	{
        		 n++;
        		 m=0;
        		Long be3 = System.currentTimeMillis();
        		tmpEntitysStr=entity;
        		//System.out.println(entity);
        		resSet.add(entity);
    		      run(entity);
        		
        			
           		 //根据实体id 去操作更新数据库
        			 String inDbstr=tmpEntitysStr.replaceAll("@", "").trim();
            		 String dbres = null;
					try {
						dbres = operateDB.updateData(inDbstr);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		 //allrunE+=tmpEntitysStr;
            			
            			 String[]  tmpSt=tmpEntitysStr.replaceAll("@", "").trim().split(" ");
            			 tmpDidEntitySet.addAll(Arrays.asList(tmpSt));
            			 Long be4 = System.currentTimeMillis();
                		System.out.println(n+","+"跑出实体数量:"+tmpSt.length+"----->已处理实体数量:"+tmpDidEntitySet.size()
                				+",数据库结果:"+dbres+"耗时间:"+(be4-be3)/1000 + "秒钟");
        		}
    		
             
        }
       
			Long be5 = System.currentTimeMillis();
			System.out.println("跑完所有串耗时：" + (be5-be2)/1000 + "秒钟");
 
	}
	public  static void run(String entity)
	{
		//根据entity去找到所有的String 
		 //如果找到的在tmp里全部存在 则停止 否则继续
		m++;
		//System.out.println(m+"当前:"+entity+"-->"+m);
		//System.out.println( m+"当前:"+entity+"-实体个数->"+tmpEntitysStr);
        if(tmpEntitysStr.contains("@@"))
        {  //System.out.println("----"+entity);
        	return;
        }
       // if(m>200)

       /* if(tmpEntitysStr.length()/32>300)
		{
			//System.out.println( "当前:"+entity+"-实体个数->"+tmpEntitysStr.length()/32);
			System.out.println( "当前:"+entity+"-实体个数->"+tmpEntitysStr);
			try {
				operateDB.insertClob(tmpEntitysStr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 System.exit(0);
		}*/
        tmpEntitysStr= tmpEntitysStr.replace(entity, entity+"@");
		 if(getAllbyOne(entity)==false)
		  {
			  return ;
		  }
		  else
		  {
			  String[]  tmpSt=tmpEntitysStr.trim().split(" ");
			  for(String str:tmpSt)
			  {
				  if(!str.contains("@"))
				   {
					  run(str);
					 
					}
			  }
		  }
		//return entity;		 		
	}


	public static boolean getAllbyOne(String one)
	{
		boolean  isallcontain=false;
		int co=0;
		for(int i=0;i<allRelationsList.size();i++)
		{
			ArrayList<String> tmp=allRelationsList.get(i);
			if(one.equals(tmp.get(0))||one.equals(tmp.get(1)))
			{
				if(tmp.get(0)!=null&&!tmpEntitysStr.contains(tmp.get(0)))
				{
					tmpEntitysStr+=" "+tmp.get(0);
					isallcontain=true;
				}
				if(tmp.get(1)!=null&&!tmpEntitysStr.contains(tmp.get(1)))
				{
					tmpEntitysStr+=" "+tmp.get(1);
					isallcontain=true;
				}
				
				//增量改进 找到有串号的立即停止
				if(tmp.get(2)!=null&&tmp.get(2).length()>0)
				{
					isallcontain=false;
					//System.out.println(tmp.get(2));
				}
				//allRelationsList.remove(i);// 加了数据量不一致
				co++;
				
			}
		}
		if(co>50)
		{
			System.out.println(one+"--->"+co+"--"+tmpEntitysStr.length()/32);
		}
		//如果返回false 表示全都加过了
	//	System.out.println("成串实体数:"+tmpEntitysStr.trim().split(" ").length);
		return isallcontain;
	}
	
}
