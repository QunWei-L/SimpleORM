package com.sends.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sends.daoimpl.SendsDaoImpl;
import com.sends.entity.Student;

/**
 * Introduction to Class:
 * 
 * @version 创建时间：2015年12月9日
 * @author qunwei.lin
 */
public class DaoTest {

	@Test
	public void testAdd() {

		Student s1 = new Student("Jackey", "male");

		SendsDaoImpl<Student> sDaoImpl = new SendsDaoImpl<Student>();

		sDaoImpl.add(s1);

	}

	@Test
	public void testDelete() {

		SendsDaoImpl<Student> sDaoImpl = new SendsDaoImpl<Student>();

		sDaoImpl.delete(1, Student.class);

	}

	@Test
	public void testUpdate() {

		Student s1 = new Student(2, "Mark", "dd");

		SendsDaoImpl<Student> sDaoImpl = new SendsDaoImpl<Student>();

		sDaoImpl.update(s1);

	}

	@Test
	public void testGetAll() {

		SendsDaoImpl<Student> sDaoImpl = new SendsDaoImpl<Student>();

		List<Student> list = sDaoImpl.getAll(Student.class);

		for (Student student : list) {

			System.out.println(student.toString());

		}

	}

	@Test
	public void testGet() {

		SendsDaoImpl<Student> sDaoImpl = new SendsDaoImpl<Student>();

		Student student = sDaoImpl.get(3, Student.class);

		System.out.println(student.toString());

	}

}