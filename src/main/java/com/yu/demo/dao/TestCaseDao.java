package com.yu.demo.dao;

import java.util.List;

import com.yu.demo.common.dao.BaseDao;
import com.yu.demo.db.TestCase;

public interface TestCaseDao extends BaseDao<TestCase> {
	//Get by tc_name
	List<TestCase> byTcName(Class<TestCase> entityClazz, String tcName);
	//Get by api
	List<TestCase> byApi(Class<TestCase> entityClazz, String api);
}
