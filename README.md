iftestdemo
====
* sub-project for the test pipeline demo project.
* demo for 3rd phase: if test

# 接口测试框架

本接口测试框架的技术栈为：java + maven + spring + testng

## 测试的主体逻辑

所有的接口测试逻辑被抽象为发起请求的抽样器(sampler)和对请求结果的检查器(checker).

```text
public interface IRunner {
	public ISampler getSampler();
	public List<ICheck> getChecklist();
}
```

在所有的执行器(Runner)的基类中，定义了对一个测试用例(TestCase)执行的抽象:
即执行sampler，将sampler的值赋值给IResult类型的结果，然后对结果执行check。
```text
public class BaseRunner extends AbstractTestNGSpringContextTests {
    ...
	protected void runByDefault(TestCase tc) {
		IResult result = sample(tc);
		check(tc, result);
	}
}
```

## 代码结构
```text
iftestdemo
├─
├─src
│  ├─main
│  │ ├─java.com.yu.demo
│  │ │  ├─ checker
│  │ │  ├─ common.dao
│  │ │  ├─ dao
│  │ │  ├─ db
│  │ │  ├─ runner
│  │ │  ├─ sampler
│  │ │  ├─ service
│  │ │  ├─ testng
│  │ │  └─ util
│  │ └─resources
│  │ │  ├─ env.properties
│  │ │  ├─ important.properties
│  │ │  ├─ spring-config.xml
│  │ │  ├─ spring-hibernate.xml
│  │ │  ├─ spring-testcase.xml
│  │ │  └─ testng.xml
│  └─test
│     ├─java.com.yu.demo
│     │  ├─ common.dao
│     │  ├─ db
│     │  ├─ service
│     │  └─ util
│     └─resources
│         └─ testng.xml
└─pom.xml
```

## 配置文件


## 使用说明