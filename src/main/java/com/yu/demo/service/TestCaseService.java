package com.yu.demo.service;

import java.util.List;

import com.yu.demo.db.TestCase;

public interface TestCaseService {

//    String getTestName();

    List<TestCase> all();

    List<TestCase> byTcName(String tcname);

    List<TestCase> byApi(String api);
}
