package com.yu.demo.util;

public interface IResult {
	public int getStatusCode();
	public long getEntityLength();
	public String getEntity();
}
