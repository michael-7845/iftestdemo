package com.yu.demo.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.yu.demo.common.dao.BaseDaoH4;
import com.yu.demo.db.TestCase;



@Repository
public class TestCaseDaoH4 extends BaseDaoH4<TestCase> implements TestCaseDao {
	@Override
	public List<TestCase> byTcName(Class<TestCase> entityClazz, String tcName) {
		String hql = String.format("from %s en where en.tcname = ?0", 
                entityClazz.getSimpleName().toLowerCase());
		return find(hql, tcName);
	}
	
	@Override
	public List<TestCase> byApi(Class<TestCase> entityClazz, String api) {
		String hql = String.format("from %s en where en.api = ?0", 
                entityClazz.getSimpleName().toLowerCase());
		return find(hql, api);
	}

	public static void demo() {
		ApplicationContext ctx =new ClassPathXmlApplicationContext("spring-config.xml");
		TestCaseDao dao = (TestCaseDao) ctx.getBean("testCaseDaoH4");
//		TestCaseDaoH4 dao = new TestCaseDaoH4();
		TestCase tc = new TestCase();
//		tc.setId(1);
		tc.setApi("/talent/find.html");
		tc.setTcname("findtalent");
		tc.setParams("a1,a2,a3");
		tc.setP0("123");
		tc.setP1("abc");
		tc.setP2("ABC");
		tc.setStatus(200);
		System.out.println(tc);
//		dao.save(tc);
	}

	public static void demo2() {
		ApplicationContext ctx =new ClassPathXmlApplicationContext("spring-config.xml");
		TestCaseDao dao = (TestCaseDao) ctx.getBean("testCaseDaoH4");
//		TestCaseDaoH4 daoH4 = (TestCaseDaoH4) dao;
		System.out.println(dao.findAll(TestCase.class));
	}
	
	public static void main(String[] args) {
		demo2();
	}

}
