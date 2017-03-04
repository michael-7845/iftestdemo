package com.yu.demo.sampler;

import com.yu.demo.db.TestCase;
import com.yu.demo.util.IResult;

public interface ISampler {
	public IResult request(TestCase tc); 
}
