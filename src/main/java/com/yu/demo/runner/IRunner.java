package com.yu.demo.runner;

import java.util.List;

import com.yu.demo.checker.ICheck;
import com.yu.demo.sampler.ISampler;

public interface IRunner {
	public ISampler getSampler();
	public List<ICheck> getChecklist();
}
