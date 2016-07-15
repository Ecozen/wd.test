/**
 * 
 */
package topdeep.autotest.biz.executer.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import topdeep.autotest.entity.constant.EnumType.ObjType;
import topdeep.autotest.entity.constant.EnumType.ParamType;
import topdeep.autotest.entity.constant.EnumType.TestContextParamKey;
import topdeep.autotest.entity.db.AtObjParams;
import topdeep.autotest.entity.execute.ObjRegisterAttribute;
import topdeep.autotest.entity.execute.TestContextExecute;

/**
 * @author niexin
 *
 */
@Service("SBmZnuRRveN0QqZNFbGpA..q")
@ObjRegisterAttribute(BeanId = "SBmZnuRRveN0QqZNFbGpA..q")
public class DefaultTestContextExecute extends BaseExecute implements TestContextExecute {

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getId()
	 */
	public String getId() {
		return "RT3OHGARS2RUmmcx-6oWg..V";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getName()
	 */
	public String getName() {
		return "测试环境对象";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getObjType()
	 */
	public String getObjType() {
		return ObjType.TestContextExecute.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.biz.executer.impl.BaseExecute#getParamList()
	 */
	@Override
	public List<AtObjParams> getInitParamList() {
		List<AtObjParams> paramList = super.getInitParamList();
		paramList.add(createNewParam(getId(), ObjType.TestContextExecute.getValue(), getId(), TestContextParamKey.Browser.getValue(), "目标浏览器", "指定使用的目标浏览器",
				ParamType.BrowserType.getValue(), "", "firefox", null, "0", "1", 1));
		paramList.add(createNewParam(getId(), ObjType.TestContextExecute.getValue(), getId(), TestContextParamKey.BrowserVersion.getValue(), "目标浏览器版本",
				"指定使用的目标浏览器版本", ParamType.String.getValue(), "", null, null, "0", "1", 2));
		paramList.add(createNewParam(getId(), ObjType.TestContextExecute.getValue(), getId(), TestContextParamKey.ServeUrl.getValue(), "Selenium2服务器地址",
				"指定使用Selenium2服务器", ParamType.String.getValue(), "", "http://localhost:4444/wd/hub", null, "1", "1", 3));
		return paramList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getBeanVersion()
	 */
	public int getBeanVersion() {
		return 4;
	}

}
