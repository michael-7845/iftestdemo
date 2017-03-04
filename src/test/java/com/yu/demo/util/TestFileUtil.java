package com.yu.demo.util;

import java.io.File;

import org.hamcrest.Matchers;
import static org.testng.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.testng.annotations.*;

public class TestFileUtil { 
	@Test
	public void testExpectFile() {
		String api = "/prices/get", casename = "casename";
		String s1 = "{'avBar':[{'barAge':-1442278496,'barDate':'2016-12-22 14:12:11','barName':'sss_0.7553178'},{'barAge':-46028450,'barDate':'2016-12-22 14:12:11','barName':'sss_0.9416827'}],'avString':['aaa','bbb','ccc'],'avboolean':[true,false,true,true],'avint':[1,2,3,4],'bar':{'barAge':2051276180,'barDate':'2016-12-22 14:12:11','barName':'sss_0.8763819'},'dddd':'2016-12-22 14:12:11','listBar':[{'barAge':385060738,'barDate':'2016-12-22 14:12:11','barName':'sss_0.14227659'},{'barAge':698120777,'barDate':'2016-12-22 14:12:11','barName':'sss_0.8042798'},{'barAge':171325217,'barDate':'2016-12-22 14:12:11','barName':'sss_0.63253206'}],'listString':['listString1','listString2','listString3'],'map':{'x':'s11111x','y':'s22222y','z':'s33333z'},'vDate':'2016-12-22 14:12:11','vString':'vStringhehhehe','v_Date':'2016-12-22 14:12:11','vboolean':false,'vbyte':64,'vchar':'x','vdouble':22.203,'vfloat':12.1,'vint':65535,'vlong':9999999,'vshort':128}";
		FileUtil.writeExpect(api, casename, s1);
		
		File f = FileUtil.expectFile(api, casename);
		assertThat(f.exists(), Matchers.equalTo(true));
		assertThat(FileUtil.read(f), Matchers.equalTo(s1));
		f.delete();
	}
	
	@Test
	public void testRealFile() {
		String api = "/prices/get", casename = "casename";
		String s1 = "{'avBar':[{'barAge':-1442278496,'barDate':'2016-12-22 14:12:11','barName':'sss_0.7553178'},{'barAge':-46028450,'barDate':'2016-12-22 14:12:11','barName':'sss_0.9416827'}],'avString':['aaa','bbb','ccc'],'avboolean':[true,false,true,true],'avint':[1,2,3,4],'bar':{'barAge':2051276180,'barDate':'2016-12-22 14:12:11','barName':'sss_0.8763819'},'dddd':'2016-12-22 14:12:11','listBar':[{'barAge':385060738,'barDate':'2016-12-22 14:12:11','barName':'sss_0.14227659'},{'barAge':698120777,'barDate':'2016-12-22 14:12:11','barName':'sss_0.8042798'},{'barAge':171325217,'barDate':'2016-12-22 14:12:11','barName':'sss_0.63253206'}],'listString':['listString1','listString2','listString3'],'map':{'x':'s11111x','y':'s22222y','z':'s33333z'},'vDate':'2016-12-22 14:12:11','vString':'vStringhehhehe','v_Date':'2016-12-22 14:12:11','vboolean':false,'vbyte':64,'vchar':'x','vdouble':22.203,'vfloat':12.1,'vint':65535,'vlong':9999999,'vshort':128}";
		FileUtil.writeReal(api, casename, s1);
		
		File f = FileUtil.realFile(api, casename);
		assertThat(f.exists(), Matchers.equalTo(true));
		assertThat(FileUtil.read(f), Matchers.equalTo(s1));
		f.delete();
	}
	
	@Test
	public void testRecordFile() {
		String api = "/prices/get", casename = "casename";
		String s1 = "{'avBar':[{'barAge':-1442278496,'barDate':'2016-12-22 14:12:11','barName':'sss_0.7553178'},{'barAge':-46028450,'barDate':'2016-12-22 14:12:11','barName':'sss_0.9416827'}],'avString':['aaa','bbb','ccc'],'avboolean':[true,false,true,true],'avint':[1,2,3,4],'bar':{'barAge':2051276180,'barDate':'2016-12-22 14:12:11','barName':'sss_0.8763819'},'dddd':'2016-12-22 14:12:11','listBar':[{'barAge':385060738,'barDate':'2016-12-22 14:12:11','barName':'sss_0.14227659'},{'barAge':698120777,'barDate':'2016-12-22 14:12:11','barName':'sss_0.8042798'},{'barAge':171325217,'barDate':'2016-12-22 14:12:11','barName':'sss_0.63253206'}],'listString':['listString1','listString2','listString3'],'map':{'x':'s11111x','y':'s22222y','z':'s33333z'},'vDate':'2016-12-22 14:12:11','vString':'vStringhehhehe','v_Date':'2016-12-22 14:12:11','vboolean':false,'vbyte':64,'vchar':'x','vdouble':22.203,'vfloat':12.1,'vint':65535,'vlong':9999999,'vshort':128}";
		FileUtil.writeRecord(api, casename, s1);
		
		File f = FileUtil.recordFile(api, casename);
		assertThat(f.exists(), Matchers.equalTo(true));
		assertThat(FileUtil.read(f), Matchers.equalTo(s1));
		f.delete();
	}

}
