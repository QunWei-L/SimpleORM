package com.sends.daoimpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.mysql.jdbc.util.PropertiesDocGenerator;
import com.sends.dao.SendsDao;
import com.sends.utils.DaoUtils;
import com.sends.utils.XmlUtils;

/**
 * Introduction to Class:
 * 
 * @version 创建时间：2015年12月9日
 * @author qunwei.lin
 */
public class SendsDaoImpl<T> implements SendsDao<T> {

	/**
	 * 
	 * 自动加载配置文件SendsDao.cfg.xml
	 * 
	 */

	public SendsDaoImpl() {

		// 读取XML配置文件
		XmlUtils.loadXml();

	}

	/**
	 * 
	 * 往数据表中插入一条数据
	 * 
	 * @param 一个与数据表相对应的对象
	 * @return成功:true 失败:false
	 */

	@Override
	public boolean add(T t) {
		// TODO Auto-generated method stub
		Class<?> clazz = t.getClass();

		// 获取表名
		String tableName = clazz.getSimpleName();

		// 获取字段名

		StringBuilder sql_1 = new StringBuilder();
		StringBuilder sql_2 = new StringBuilder();

		sql_1.append("" + "insert into " + tableName + "(");
		sql_2.append("values(");

		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {

			field.setAccessible(true);

			sql_1.append(field.getName()).append(",");
			try {
				String field_type = field.getGenericType().toString();

				if ("int".equals(field_type) || "long".equals(field_type)) {

					sql_2.append(field.get(t).toString()).append(",");

				} else {

					sql_2.append("'").append(field.get(t).toString()).append("'").append(",");

				}

			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		// 构建sql

		sql_1.deleteCharAt(sql_1.length() - 1);
		sql_2.deleteCharAt(sql_2.length() - 1);

		sql_1.append(")");
		sql_2.append(")");

		sql_1.append(sql_2.toString());

		String sql = sql_1.toString();

		System.out.println("SQL:" + sql);

		// 插入

		Connection conn = DaoUtils.getConnection();

		try {
			PreparedStatement ppst = conn.prepareStatement(sql);
			boolean flag = ppst.execute();

			// 释放链接

			DaoUtils.close(ppst, null);

			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DaoUtils.close(null, null);

		return false;
	}

	/**
	 * 
	 * 删除数据表中的一条数据
	 * 
	 * @param id
	 *            数据的唯一id
	 * @param clazz
	 *            与数据表相对应的对象类型
	 * @return 成功:true 失败:false
	 */

	@Override
	public boolean delete(Object id, Class<T> clazz) {
		// TODO Auto-generated method stub

		// 获取表名
		String tableName = clazz.getSimpleName();

		// 获取字段名

		StringBuilder sql_1 = new StringBuilder();
		StringBuilder sql_2 = new StringBuilder();

		sql_1.append("" + "delete from " + tableName + " where ");
		sql_2.append(" like ");

		Field[] fields = clazz.getDeclaredFields();

		boolean id_find = false;

		for (Field field : fields) {

			field.setAccessible(true);

			try {

				String field_type = field.getGenericType().toString();

				if ("int".equals(field_type) || "long".equals(field_type)) {

					sql_1.append(field.getName());

					id_find = true;

					break;
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		if (!id_find) {

			sql_1.append("id");

		}

		sql_2.append(id.toString());

		sql_1.append(sql_2.toString());

		String sql = sql_1.toString();

		System.out.println("SQL:" + sql);

		// 插入

		Connection conn = DaoUtils.getConnection();

		try {
			PreparedStatement ppst = conn.prepareStatement(sql);
			boolean flag = ppst.execute();

			// 释放链接

			DaoUtils.close(ppst, null);

			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DaoUtils.close(null, null);

		return false;

	}

	/**
	 * 
	 * 更新数据表中的一条数据
	 * 
	 * @param t
	 *            更新的对象
	 * @return 成功:true 失败:False
	 */

	@Override
	public boolean update(T t) {
		// TODO Auto-generated method stub

		Class<?> clazz = t.getClass();

		// 获取表名
		String tableName = clazz.getSimpleName();

		// 获取字段名

		StringBuilder sql_1 = new StringBuilder();
		StringBuilder sql_2 = new StringBuilder();

		sql_1.append("" + "update " + tableName + " set ");
		sql_2.append(" where (id=");

		Field[] fields = clazz.getDeclaredFields();

		String id = "0";

		for (Field field : fields) {

			field.setAccessible(true);

			if (field.getName().equals("id")) {

				try {
					id = field.get(t).toString();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {

				try {
					sql_1.append("").append(field.getName()).append("='").append(field.get(t) + "',");
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		// 构建sql

		sql_1.deleteCharAt(sql_1.length() - 1);
		sql_2.append("'" + id + "')");

		sql_1.append(sql_2.toString());

		String sql = sql_1.toString();

		System.out.println("SQL:" + sql);

		// 插入

		Connection conn = DaoUtils.getConnection();

		try {
			PreparedStatement ppst = conn.prepareStatement(sql);
			boolean flag = ppst.execute();

			// 释放链接

			DaoUtils.close(ppst, null);

			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DaoUtils.close(null, null);

		return false;
	}

	/**
	 * 
	 * 按id查询一条记录
	 * 
	 * @param id
	 *            查询的id
	 * @param clazz
	 *            数据的对象类型
	 * @return 返回查询到的数据对象
	 */

	@Override
	public T get(Object id, Class<T> clazz) {
		// TODO Auto-generated method stub

		// 获取表名
		String tableName = clazz.getSimpleName();

		// 获取字段名

		StringBuilder sql_1 = new StringBuilder();
		StringBuilder sql_2 = new StringBuilder();

		sql_1.append("" + "select * from " + tableName + " where ");
		sql_2.append(" like ");

		Field[] fields = clazz.getDeclaredFields();

		boolean id_find = false;

		for (Field field : fields) {

			field.setAccessible(true);

			try {

				String field_type = field.getGenericType().toString();

				if ("int".equals(field_type) || "long".equals(field_type)) {

					sql_1.append(field.getName());

					id_find = true;

					break;
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		if (!id_find) {

			sql_1.append("id");

		}

		sql_2.append(id.toString());

		sql_1.append(sql_2.toString());

		String sql = sql_1.toString();

		System.out.println("SQL:" + sql);

		// 插入

		Connection conn = DaoUtils.getConnection();

		try {
			PreparedStatement ppst = conn.prepareStatement(sql);
			ResultSet rs = ppst.executeQuery();

			T t = clazz.newInstance();

			Field[] fields2 = clazz.getDeclaredFields();

			while (rs.next()) {

				insertT(t, fields2, rs);

			}

			// 释放链接

			DaoUtils.close(ppst, rs);

			return t;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DaoUtils.close(null, null);

		return null;
	}

	/**
	 * 
	 * 获取所有数据
	 * 
	 * @param 该数据表的对象类型
	 * @return 数据集合
	 */

	@Override
	public List<T> getAll(Class<T> clazz) {
		// TODO Auto-generated method stub

		// 获取表名
		String tableName = clazz.getSimpleName();

		// 获取字段名

		StringBuilder sql_1 = new StringBuilder();

		sql_1.append("" + "select * from " + tableName);

		String sql = sql_1.toString();

		System.out.println("SQL:" + sql);

		// 插入

		Connection conn = DaoUtils.getConnection();

		try {
			PreparedStatement ppst = conn.prepareStatement(sql);

			ResultSet rs = ppst.executeQuery(sql);

			List<T> entitys = new ArrayList<T>();

			while (rs.next()) {

				// 获取字段值,插入对象t

				T t;
				try {
					t = clazz.newInstance();
					Field[] fields = clazz.getDeclaredFields();

					insertT(t, fields, rs);

					entitys.add(t);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			// 释放链接

			DaoUtils.close(ppst, rs);

			return entitys;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DaoUtils.close(null, null);

		return null;
	}

	/**
	 * 
	 * 往泛型对象中,反向插入值
	 * 
	 * @param t
	 *            泛型对象
	 * @param fields
	 *            泛型对象的属性集合
	 * @param rs
	 *            数据集合
	 * @throws Exception
	 *             插入出错
	 */

	private void insertT(T t, Field[] fields, ResultSet rs) throws Exception {

		for (Field field : fields) {
			String propertyName = field.getName();
			Object paramVal = null;
			Class<?> clazzField = field.getType();
			if (clazzField == String.class) {
				paramVal = rs.getString(propertyName);
			} else if (clazzField == short.class || clazzField == Short.class) {
				paramVal = rs.getShort(propertyName);
			} else if (clazzField == int.class || clazzField == Integer.class) {
				paramVal = rs.getInt(propertyName);
			} else if (clazzField == long.class || clazzField == Long.class) {
				paramVal = rs.getLong(propertyName);
			} else if (clazzField == float.class || clazzField == Float.class) {
				paramVal = rs.getFloat(propertyName);
			} else if (clazzField == double.class || clazzField == Double.class) {
				paramVal = rs.getDouble(propertyName);
			} else if (clazzField == boolean.class || clazzField == Boolean.class) {
				paramVal = rs.getBoolean(propertyName);
			} else if (clazzField == byte.class || clazzField == Byte.class) {
				paramVal = rs.getByte(propertyName);
			} else if (clazzField == char.class || clazzField == Character.class) {
				paramVal = rs.getCharacterStream(propertyName);
			} else if (clazzField == Date.class) {
				paramVal = rs.getTimestamp(propertyName);
			} else if (clazzField.isArray()) {
				paramVal = rs.getString(propertyName).split(","); // 以逗号分隔的字符串
			}

			PropertyDescriptor pptdes = new PropertyDescriptor(propertyName, t.getClass());

			pptdes.getWriteMethod().invoke(t, paramVal);

		}

	}

}