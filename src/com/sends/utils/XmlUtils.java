package com.sends.utils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Introduction to Class:
 * 
 * @version 创建时间：2015年12月11日
 * @author qunwei.lin
 */
public class XmlUtils {

	public static void loadXml() {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document xml = db.parse("src/SendsDao.cfg.xml");

			NodeList list = xml.getFirstChild().getChildNodes();

			/**
			 * 
			 * 获取指定标签 (version=2.0) 第二次更新优化
			 * 
			 */

			DaoUtils.url = xml.getElementsByTagName("url").item(0).getTextContent();

			DaoUtils.name = xml.getElementsByTagName("username").item(0).getTextContent();

			DaoUtils.password = xml.getElementsByTagName("password").item(0).getTextContent();

			// 方法结束,以下为注释

			/**
			 * 
			 * 遍历法(version=1.0)//第一次写的时候
			 * 
			 */
			/*
			 * for (int i = 0; i < list.getLength(); i++) { org.w3c.dom.Node
			 * node = list.item(i);
			 * 
			 * if (node.getNodeType() == Node.ELEMENT_NODE) {
			 * 
			 * if (node.getNodeName().equals("url")) {
			 * 
			 * DaoUtils.url = node.getTextContent();
			 * 
			 * } else if (node.getNodeName().equals("username")) {
			 * 
			 * DaoUtils.name = node.getTextContent();
			 * 
			 * } else if (node.getNodeName().equals("password")) {
			 * 
			 * DaoUtils.password = node.getTextContent(); }
			 * 
			 * }
			 * 
			 * }
			 */

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}