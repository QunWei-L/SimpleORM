package com.sends.entity; 
/** 
* Introduction to Class:
* @version 创建时间：2015年12月9日
* @author qunwei.lin  
*/
public class Student {
	
	private int id;
	private String name;
	private String sex;
	
	
	/**
	 * 
	 * 
	 * @param id 学生唯一ID
	 * @param name 名字
	 * @param sex  性别
	 */
	
	public Student() {
		super();
	}

	public Student(String name, String sex) {
		super();
		this.name = name;
		this.sex = sex;
	}
	
	
	

	public Student(int id, String name, String sex) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex + "]";
	}
	
	
	
	

}
 