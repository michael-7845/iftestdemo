iftestdemo
====
* sub-project for the test pipeline demo project.
* demo for 3rd phase: if test

# 接口测试框架

本接口测试框架的技术栈为：java + maven + spring + testng + hibernation

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

### 关键代码说明

#### Checker

#### java.com.yu.demo.checker

ICheck定义了checker的通用接口，所有checker实现应该实现此接口。
语义为：针对测试用例TestCase tc，比对IRsult result进行检查。

```text
public interface ICheck {
	public ICheckResult check(TestCase tc, IResult result);
}
```

针对测试中常见的结果检查，提供了一些基础的checker实现。
```text
类层次
ICheck
├─BasicChecker
└─EntityChecker
    ├─ ArrayEntityChecker
    ├─ ObjectEntityChecker
    ├─ ContainEntityChecker
    └─ RegexEntityChecker
```

其中，
* BasicChecker - 检查 实际返回的Status vs. 用例中预期的Status
* EntityChecker - 关注请求返回的Entity
  如果${chk.mode}为ecorder时， Entity内容会被保存在 ${chk.result.folder} 下的 ${chk.record} 目录。
  如果${chk.mode}为checker时， Entity内容会被保存在 ${chk.result.folder} 下的 ${chk.real} 目录下，根据TestCase的中api信息映射的目录，
  并且 vs. ${chk.result.folder} 下的 ${chk.expect} 目录下， api信息映射的目录内容 。
* ArrayEntityChecker - EntityChecker的子类，用于比较entity为json array数据的结果
* ObjectEntityChecker - EntityChecker的子类，用于比较entity为json object数据的结果
* ContainEntityChecker - EntityChecker的子类，用于比较实际结果是否包含预期结果
* RegexEntityChecker - EntityChecker的子类，用于判断实际结果是否匹配包含预期的正则模式

其中，
对于json类型数据，提供了JSONExcludingConfig配置。
可以针对某些json key，根据正则表达式提取出其值中关注的匹配组后，进行比较。
具体配置请参考配置文件章节。

#### TestCase
TestCase代表的具体的测试用例，指定了希望访问的api，约定的预期的结果，直接的如表中的预期返回，间接如通过api映射的文件目录。

##### com.yu.demo.common.dao
hibernate的Dao泛型基类。

##### com.yu.demo.dao
TestCaseDao 和 TestCaseDaoH4, 根据测试用例名称 和 测试用例的API获取测试用例数据的Dao类。

##### com.yu.demo.db
TestCase 数据库testcae表

##### com.yu.demo.service
testcase的service层实现。

#### Runner

接口IRunner抽象出Runner中最重要的两个概念ISampler和IChecker。
在配件文件中可以针对自己意图，灵活配置上自己的sampler和checker。

```text
public interface IRunner {
	public ISampler getSampler();
	public List<ICheck> getChecklist();
}
```

##### com.yu.demo.runner

针对测试中常见的执行，提供了一些范例性质的runner实现。
建议如要创建自己的runner前，阅读这些范例实现。
```text
类层次
BasicRunner
├─AllRunner
├─ByApiRunner
└─ByNameRunner
```

为了创建自己的Runner，请继承BaseRunner。
在框架中提供的范例性质的Runner实现，都是基于Runner实现的。包括执行全部用例，根据配置的api， 根据配置的测试用例名称进行执行的Runner。
重点对BaseRunner代码进行简单解释。

```text
public class BaseRunner extends AbstractTestNGSpringContextTests {
	protected IRunner runner; => 基类中为提供实现的IRunner runner，只提供了runByDefault()作为以runner进行执行的框架性代码。

	@Resource(name="tcService")
	protected TestCaseService serv;

	// Copy them to the sub-class so that it can be configurable in the sub-class
	@Resource(name="testcaseSelector")
	public TestcaseSelector selector; => 测试用例选择器，如果不在子类中覆盖，那么将使用缺省的testcaseSelector bean作为选择定义。

	protected IResult sample(TestCase tc) { => sample()调用ISampler的request()方法，发起请求，返回IResult结果供checker检查。
		return runner.getSampler().request(tc);
	}

	protected void check(TestCase tc, IResult result) { => check()以此调用checkList中的checker对IResult结果进行检查。
		int checkStep = 0;
		for(ICheck c: runner.getChecklist()) {
			ICheckResult r = c.check(tc, result);
			System.out.println(String.format("tcname: %10s - checker[%2d] | pass: %s, info: %s",
					tc.getTcname(), ++checkStep, r.getStatus(), r.getMessage()));
			assertTrue(r.getStatus());
		}
	}

	protected void runByDefault(TestCase tc) {
		IResult result = sample(tc);
		check(tc, result);
	}
}
```

#### Sampler

接口ISampler抽象出Sampler的request()方法， 根据TestCase tc创建自己的请求。

##### com.yu.demo.sampler

针对测试中常见的请求，提供了一些范例性质的sampler实现。
建议如要创建自己的sampler前，阅读这些范例实现。
```text
类层次
ISampler
├─HttpSampler
└─AppGwSampler
```

## 配置文件


## 使用说明