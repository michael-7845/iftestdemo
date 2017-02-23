package com.yu.demo.util;

import org.springframework.stereotype.Service;

public class HttpResult implements IResult {
	public HttpResult(int statusCode, long entityLength, String entity) {
		super();
		this.statusCode = statusCode;
		this.entityLength = entityLength;
		this.entity = entity;
	}

	private int statusCode;
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	@Override
	public int getStatusCode() {
		return this.statusCode;
	}
	
	private long entityLength;
	@Override
	public long getEntityLength() {
		return entityLength;
	}
	public void setEntityLength(long entityLength) {
		this.entityLength = entityLength;
	}

	private String entity;
	public void setEntity(String entity) {
		this.entity = entity;
	}
	@Override
	public String getEntity() {
		return this.entity;
	}

	@Override
	public String toString() {
		return "HttpResult [statusCode=" + statusCode + ", entityLength=" + entityLength + ", entity=" + entity + "]";
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
