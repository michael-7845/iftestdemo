package com.yu.demo.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "testcase")
public class TestCase {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
	
    @Column(name = "tc_name")
    private String tcname;
    
    @Column(name = "api")
    private String api;
    
    @Column(name = "params")
    private String params;
    
    @Column(name = "param0")
    private String p0;
    @Column(name = "param1")
    private String p1;
    @Column(name = "param2")
    private String p2;
    @Column(name = "param3")
    private String p3;
    @Column(name = "param4")
    private String p4;
    @Column(name = "param5")
    private String p5;
    @Column(name = "param6")
    private String p6;
    @Column(name = "param7")
    private String p7;
    @Column(name = "param8")
    private String p8;
    @Column(name = "param9")
    private String p9;
    
    @Column(name = "status")
    private int status;

    // getter and setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTcname() {
		return tcname;
	}

	public void setTcname(String tcname) {
		this.tcname = tcname;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getP0() {
		return p0;
	}

	public void setP0(String p0) {
		this.p0 = p0;
	}

	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public String getP3() {
		return p3;
	}

	public void setP3(String p3) {
		this.p3 = p3;
	}

	public String getP4() {
		return p4;
	}

	public void setP4(String p4) {
		this.p4 = p4;
	}

	public String getP5() {
		return p5;
	}

	public void setP5(String p5) {
		this.p5 = p5;
	}

	public String getP6() {
		return p6;
	}

	public void setP6(String p6) {
		this.p6 = p6;
	}

	public String getP7() {
		return p7;
	}

	public void setP7(String p7) {
		this.p7 = p7;
	}

	public String getP8() {
		return p8;
	}

	public void setP8(String p8) {
		this.p8 = p8;
	}

	public String getP9() {
		return p9;
	}

	public void setP9(String p9) {
		this.p9 = p9;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	// toString
	@Override
	public String toString() {
		return "TestCase [id=" + id + ", tcname=" + tcname + ", api=" + api + ", params=" + params + ", p0=" + p0
				+ ", p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + ", p4=" + p4 + ", p5=" + p5 + ", p6=" + p6 + ", p7=" + p7
				+ ", p8=" + p8 + ", p9=" + p9 + ", status=" + status + "]";
	} 
	
	// getParamList
	public Map<String, Object> getParamMap() {
		List<Object> values = new ArrayList<Object>();
		Collections.addAll(values, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
		List<String> keys = new ArrayList<String>();
		if(this.params != null) {
			keys = Arrays.asList(this.params.split(","));
		}
		Map<String,Object> result = new HashMap<String,Object>();
		for(int i=0; i<keys.size(); i++) {
			result.put(keys.get(i), values.get(i));
		}
		return result;
	}
}
