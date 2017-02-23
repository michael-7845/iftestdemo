package com.yu.demo.common.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;
import com.yu.demo.dao.TestCaseDao;
import com.yu.demo.db.TestCase;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.Serializable;
import java.util.List;

@ContextConfiguration(locations = { "classpath*:/spring-config.xml" })  
public class TestCaseDaoH4Tester extends AbstractTestNGSpringContextTests {
	@Autowired
	private TestCaseDao dao;
	
	@Test(groups={"unchanged"})
	public void testGet() {
		TestCase entity = dao.get(TestCase.class, 1);
		assertEquals("cook002", entity.getTcname());
		assertEquals("cookChefShowTabInit", entity.getApi());
		assertEquals("pageSize", entity.getParams());
		assertEquals(200, entity.getStatus());
	}
	
	@Test(groups={"unchanged"})
	public void testByTcName() {
		List<TestCase> result = dao.byTcName(TestCase.class, "cook002");
		assertEquals(1, result.size());
		assertEquals(1, result.get(0).getId());
	}
	
	@Test(groups={"unchanged"})
	public void testFindCount() {
		long count = dao.findCount(TestCase.class);
		assertEquals(8, count);
	}
	
	@Test(groups={"unchanged"})
	public void testFindAll() {
		List<TestCase> result = dao.findAll(TestCase.class);
		assertEquals("cook002", result.get(0).getTcname());
		assertEquals("pageNum,pageSize,sortType,asc,keyword", result.get(1).getParams());
		assertEquals(8, result.size());
	}
	
	@Test(groups={"changed"}, dependsOnGroups = {"unchanged.*"})
	public void testUpdate() {
		TestCase entity = dao.get(TestCase.class, 1);
		int oldValue = entity.getStatus();
		
		entity.setStatus(201);
		dao.update(entity); // do update()
		entity = dao.get(TestCase.class, 1);
		assertEquals(201, entity.getStatus());
		
		//resume
		entity.setStatus(oldValue);
		dao.update(entity);
	}
	
	@Test(groups = {"added"}, dependsOnGroups = {"unchanged.*", "changed.*"})
	public void testSaveAndDelete() {
		TestCase entity = dao.get(TestCase.class, 1);
		long count = dao.findCount(TestCase.class);
		
		entity.setTcname("tc4added");
		Serializable id = dao.save(entity);
		assertEquals(count+1, dao.findCount(TestCase.class));
		
		dao.delete(TestCase.class, id);
		assertEquals(count, dao.findCount(TestCase.class));
	}
	
	@Test(groups = {"added"}, dependsOnGroups = {"unchanged.*", "changed.*"})
	public void testDeleteEntity() {
		String newTname = "tc4added";
		TestCase entity = dao.get(TestCase.class, 1);
		long count = dao.findCount(TestCase.class);
		
		entity.setTcname(newTname);
		Serializable id = dao.save(entity);
		assertEquals(count+1, dao.findCount(TestCase.class));
		
		entity = dao.get(TestCase.class, id);
		assertEquals(newTname, entity.getTcname());
		
		dao.delete(entity);
		assertEquals(count, dao.findCount(TestCase.class));
	}
}
