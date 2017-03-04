package com.yu.demo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.yu.demo.db.TestCase;

public class TestCaseServiceImplTester {
	private TestCaseService serv;
	
	@BeforeClass
	public void init() {
		ApplicationContext ctx =new ClassPathXmlApplicationContext("spring-config.xml");
		this.serv = (TestCaseService) ctx.getBean("tcService");
	}
	
	@Test
	public void testGetAllTestData() {
		List<TestCase> result = serv.all();
		assertEquals("findclass", result.get(0).getTcname());
		assertEquals("/recipe/find.html", result.get(1).getApi());
		assertEquals("a1,a2,a3", result.get(2).getParams());
		assertEquals(3, result.size());
	}
	
	@Test
	public void testByTcName() {
		List<TestCase> result = serv.byTcName("findrecipe");
		assertEquals(1, result.size());
		assertEquals(2, result.get(0).getId());
	}
	
	@Test
	public void testByApi() {
		List<TestCase> result = serv.byApi("/talent/find.html");
		assertEquals(1, result.size());
		assertEquals(3, result.get(0).getId());
	}
}
