/**
 * 
 */
package topdeep.autotest.entity.constant;

/**
 * @author niexin
 *
 */
public class EnumType {

	public enum ObjType {
		UserCase("用例", "0"),
		UserCaseAction("用例动作", "1"),
		TestUserCaseExecute("用例测试执行器", "2"),
		TestCaseExecute("测试用例执行器", "3"),
		TestContext("测试环境", "4"),
		UserCaseExecute("用例执行器", "5"),
		UserCaseActionExecute("用例动作执行器", "6"),
		TestTaskExecute("测试任务执行器", "7"),
		TestContextExecute("测试环境执行器", "8");
		private String name;
		private String value;

		public String getName() {
			return this.name;
		}

		public String getValue() {
			return this.value;
		}

		private ObjType(String name, String value) {
			this.name = name;
			this.value = value;
		}

	}

	public enum TestContextParamKey {
		Browser("浏览器", "browser"),
		BrowserVersion("浏览器版本", "browserVer"),
		ServeUrl("测试服务器地址", "testServeUrl");
		private String name;
		private String value;

		public String getName() {
			return this.name;
		}

		public String getValue() {
			return this.value;
		}

		private TestContextParamKey(String name, String value) {
			this.name = name;
			this.value = value;
		}
	}

	public enum TestContextDataKey {
		Driver("驱动", "driver"),
		Alert("对话框", "alert");
		private String name;
		private String value;

		public String getName() {
			return this.name;
		}

		public String getValue() {
			return this.value;
		}

		private TestContextDataKey(String name, String value) {
			this.name = name;
			this.value = value;
		}
	}

	public enum ParamType {
		Integer("整型", "Integer"),
		Boolean("布尔型", "Boolean"),
		String("字符串", "String"),
		HtmlId("html标识", "HtmlId"),
		EleLocType("元素定位方式", "ElementLocateType"),
		BrowserType("浏览器类型", "BrowserType"),
		CheckType("文本检查类型", "CheckType");

		private String name;

		public String getName() {
			return this.name;
		}

		private String value;

		public String getValue() {
			return this.value;
		}

		private ParamType(String name, String value) {
			this.name = name;
			this.value = value;
		}
	}

	public enum TestState {
		Prepare("未开始", "0"),
		Testing("测试中", "1"),
		Finished("完成", "2");

		private String name;

		public String getName() {
			return this.name;
		}

		private String value;

		public String getValue() {
			return this.value;
		}

		private TestState(String name, String value) {
			this.name = name;
			this.value = value;
		}
	}

	public enum TestResult {
		NoTest("未开始", "0"),
		Success("成功", "1"),
		Fail("失败", "2"),
		PartialSuccess("部分成功", "3");

		private String name;

		public String getName() {
			return this.name;
		}

		private String value;

		public String getValue() {
			return this.value;
		}

		private TestResult(String name, String value) {
			this.name = name;
			this.value = value;
		}
	}

	public enum CheckType {
		Equale("完全相等", "0"),
		Like("包含", "1"),
		Regex("正则表达式", "2");

		private String name;

		public String getName() {
			return this.name;
		}

		private String value;

		public String getValue() {
			return this.value;
		}

		private CheckType(String name, String value) {
			this.name = name;
			this.value = value;
		}
	}
	
	public enum ElementLocateType{
		Id("id选择器","1"),
		CssSelector("css选择器","2"),
		ClassName("classname选择器","3");
		
		private String name;

		public String getName() {
			return this.name;
		}

		private String value;

		public String getValue() {
			return this.value;
		}

		private ElementLocateType(String name, String value) {
			this.name = name;
			this.value = value;
		}
	}
	
	public enum BrowserType{
		Firefox("火狐","1"),
		IE("IE","1"),
		Chrome("谷歌","1");
		
		private String name;

		public String getName() {
			return this.name;
		}

		private String value;

		public String getValue() {
			return this.value;
		}

		private BrowserType(String name, String value) {
			this.name = name;
			this.value = value;
		}
	}

}
