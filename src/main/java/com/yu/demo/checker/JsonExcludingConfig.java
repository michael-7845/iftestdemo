package com.yu.demo.checker;

import java.util.HashMap;
import java.util.Map;

public class JsonExcludingConfig {
	private Map<String, String> excluding;

	public Map<String, String> getExcluding() {
		return excluding;
	}
	public void setExcluding(Map<String, String> excluding) {
		this.excluding = excluding;
	} 
//	= new HashMap<String, String>() {{
//		put("img", "img\\d+(.*)$"); //key:reg
//		put("rectImg", "img\\d+(.*)$");
//	}};

}
