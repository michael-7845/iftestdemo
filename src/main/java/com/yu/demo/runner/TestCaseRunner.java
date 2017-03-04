package com.yu.demo.runner;

import java.util.List;
import com.yu.demo.checker.ICheck;
import com.yu.demo.sampler.ISampler;

import static org.junit.Assert.*;

public class TestCaseRunner implements IRunner {
	private ISampler sampler;
	
	public ISampler getSampler() {
		return sampler;
	}
	public void setSampler(ISampler sample) {
		this.sampler = sample;
	}
	private List<ICheck> checklist;

	public List<ICheck> getChecklist() {
		return checklist;
	}
	public void setChecklist(List<ICheck> checklist) {
		this.checklist = checklist;
	}
}
