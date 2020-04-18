package com.yhzn.common.util.zncb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;

	public static Connection getConnection() {
		url="jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.154.132.75)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.154.132.76)(PORT = 1521))(LOAD_BALANCE = yes)(CONNECT_DATA =(SERVICE_NAME = xcky)))";

		driver = "oracle.jdbc.driver.OracleDriver";
		//url="jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.154.17.207)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.154.17.206)(PORT = 1521))(LOAD_BALANCE = yes)(CONNECT_DATA =(SERVICE_NAME = xzdb)))";
		username = "xjpt_zjk";
		password = "xjpt_zjk";
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			// TODO: handle exception
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, username, password);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
			//	e1.printStackTrace();
			//	conn=getConnection1();
			}

		}
		return conn;
	}

	public static Connection getConnection1() {

		driver = "oracle.jdbc.driver.OracleDriver";
		url="jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.154.132.75)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.154.132.76)(PORT = 1521))(LOAD_BALANCE = yes)(CONNECT_DATA =(SERVICE_NAME = xcky)))";
		//url="jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.154.17.207)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.154.17.206)(PORT = 1521))(LOAD_BALANCE = yes)(CONNECT_DATA =(SERVICE_NAME = xzdb)))";
		username = "xjpt_zjk";
		password = "xjpt_zjk";
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			// TODO: handle exception
			try {
				url="jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.154.132.75)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.154.132.76)(PORT = 1521))(LOAD_BALANCE = yes)(CONNECT_DATA =(SERVICE_NAME = xcky)))";
				Class.forName(driver);
				conn = DriverManager.getConnection(url, username, password);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				//conn=getConnection();
			}

		}
		return conn;
	}
	
	public static void closeConnection(Connection conn) {

		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
