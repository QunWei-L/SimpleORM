package com.sends.dao;

import java.util.List;

/** 
* Introduction to Class:
* @version 创建时间：2015年12月9日
* @author qunwei.lin  
*/
public interface SendsDao<T>{
	
	public boolean add(T t);
	
	public boolean delete(Object id,Class<T> clazz);
	
	public boolean update(T t);
	
	public T get(Object id,Class<T> clazz);
	
	public List<T> getAll(Class<T> clazz);

}
 