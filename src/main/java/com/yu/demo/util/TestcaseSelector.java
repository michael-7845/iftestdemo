package com.yu.demo.util;

import java.util.List;

public class TestcaseSelector {
	
	private List<String> apiList;

	public List<String> getApiList() {
		return apiList;
	}
	public void setApiList(List<String> config) {
		this.apiList = config;
	}

	private List<String> apiListSkipped;

	public List<String> getApiListSkipped() {
		return apiListSkipped;
	}
	public void setApiListSkipped(List<String> config) {
		this.apiListSkipped = config;
	}
	
	private List<String> nameList;

	public List<String> getNameList() {
		return nameList;
	}
	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}

	private List<String> nameListSkipped;

	public List<String> getNameListSkipped() {
		return nameListSkipped;
	}
	public void setNameListSkipped(List<String> nameList) {
		this.nameListSkipped = nameList;
	}
	
}
