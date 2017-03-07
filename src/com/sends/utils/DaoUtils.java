package com.sends.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Introduction to Class:
 * 
 * @version 创建时间：2015年12月9日
 * @author qunwei.lin
 */
public final class DaoUtils {

	public static String url = "jdbc:mysql://10.3.37.55:3306/doubleEleven";
	public static String name = "root";
	public static String password = "sends123abc";

	public DaoUtils(String url, String name, String password) {
		this.url = url;
		this.name = name;
		this.password = password;
	}

	private static Connection conn;

	/**
	 * 
	 * 获取一个连接
	 * 
	 * @return sql.connection
	 */

	public static Connection getConnection() {

		if (conn == null) {

			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, name, password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return conn;

	}

	/**
	 * 
	 * close:Connection,PreparedStatement,ResultSet
	 * 
	 */

	public static void close(PreparedStatement ps, ResultSet rs) {
		if (conn != null) {

			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (ps != null) {

			try {
				ps.close();
				ps = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (rs != null) {

			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}