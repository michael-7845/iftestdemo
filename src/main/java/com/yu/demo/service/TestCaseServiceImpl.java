package com.yu.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yu.demo.dao.TestCaseDao;
import com.yu.demo.db.TestCase;

@Service("tcService")
@Transactional
public class TestCaseServiceImpl implements TestCaseService {

    @Resource
    private TestCaseDao dao;

    //kemin: for what?
//    public String getTestName() {
//        return this.getClass().getName();
//    }
    
    public List<TestCase> all() {
        return dao.findAll(TestCase.class);
    }

    public List<TestCase> byTcName(String tcname) {
    	return dao.byTcName(TestCase.class, tcname);
    }

    public List<TestCase> byApi(String className) {
    	return dao.byApi(TestCase.class, className);
    }

}
