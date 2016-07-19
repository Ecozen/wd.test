/**
 * 
 */
package topdeep.autotest.biz.executer.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import topdeep.autotest.biz.common.UserCaseManageBiz;
import topdeep.autotest.entity.constant.EnumType.CheckType;
import topdeep.autotest.entity.data.AtObjParams;
import topdeep.autotest.entity.params.ApplicationParam;


/**
 * @author niexin
 *
 */
public abstract class BaseExecute implements topdeep.autotest.entity.execute.BaseExecute {

	protected UserCaseManageBiz userCaseManageBiz;

	protected ApplicationParam applicationParam;

	public List<AtObjParams> getInitParamList() {
		return new ArrayList<AtObjParams>();
	}

	public int getBeanVersion() {
		return 1;
	}

	public Map<String, AtObjParams> convertParamListToMap(List<AtObjParams> paramList) {
		Map<String, AtObjParams> map = new HashMap<String, AtObjParams>();
		for (AtObjParams item : paramList) {
			if (!map.containsKey(item.getParamCode())) {
				map.put(item.getParamCode(), item);
			}
		}
		return map;
	}

	public List<AtObjParams> queryParamList(String objId, String objType) throws Exception {
		List<AtObjParams> paramList = null ;
		return paramList;
	}

	public Map<String, AtObjParams> queryParamMap(String objId, String objType) throws Exception {
		List<AtObjParams> paramList = queryParamList(objId, objType);
		Map<String, AtObjParams> paramMap = convertParamListToMap(paramList);
		return paramMap;
	}

	public boolean existsKeyValud(Map<String, AtObjParams> paramMap, String key, String value) {
		if (!paramMap.containsKey(key)) {
			return false;
		}
		AtObjParams objParam = paramMap.get(key);
		if (value == null) {
			return true;
		}
		return value.equals(objParam.getParamValue());
	}

	public String getStringValueFromParamMap(Map<String, AtObjParams> paramMap, String key) {
		if (!paramMap.containsKey(key)) {
			return null;
		}
		AtObjParams objParam = paramMap.get(key);
		return objParam.getParamValue();
	}

	public AtObjParams createNewParam(String objId, String objType, String ownerId, String paramCode, String paramName, String paramDesc, String paramType,
			String paramTypeData, String paramValue, String paramValueDesc, String paramRequired, String userModify, int paramOrder) {
		AtObjParams obj = new AtObjParams();
		obj.setCreateTime(new Date());
		obj.setModifyTime(new Date());
		obj.setId(objId);
		obj.setObjType(objType);
		obj.setOwnerId(ownerId);
		obj.setParamCode(paramCode);
		obj.setParamDesc(paramDesc);
		obj.setParamName(paramName);
		obj.setParamRequired(paramRequired);
		obj.setParamType(paramType);
		obj.setParamTypeData(paramTypeData);
		obj.setParamValue(paramValue);
		obj.setParamValueDesc(paramValueDesc);
		obj.setUserModify(userModify);
		obj.setRowVersion(0);
		obj.setCreateTime(new Date());
		obj.setModifyTime(new Date());
		obj.setParamOrder(paramOrder);
		return obj;
	}

	public int getIntValue(String value) {
		if (StringUtils.isNullOrEmpty(value)) {
			return 0;
		}
		for (Character ch : value.toCharArray()) {
			if (!Character.isDigit(ch)) {
				return 0;
			}
		}
		return Integer.parseInt(value);
	}

	/**
	 * 根据检查类型和表达式检查源值，看是否符合
	 * 
	 * @param srcValue
	 * @param checkType
	 * @param checkValue
	 * @return
	 */
	public boolean checkStringValue(String srcValue, String checkType, String checkValue) {
		if (srcValue == null || checkValue == null || checkType == null) {
			return false;
		}
		CheckType ct = EnumTypeUtil.valueOf(checkType, CheckType.class);
		if (ct == null) {
			return false;
		}
		if (ct == CheckType.Equale) {
			return srcValue.equals(checkValue);
		} else if (ct == CheckType.Like) {
			return srcValue.indexOf(checkValue) >= 0;
		} else if (ct == CheckType.Regex) {
			return Pattern.matches(checkValue, srcValue);
		}
		return false;
	}

	private Pattern paramPattern = Pattern.compile("\\$\\{(.*?)\\}");

	public void resolveParamPattern(String paramValue, Set<String> resultSet) {
		// String regex = "\\$\\{(.*)?\\}";
		// Pattern p = Pattern.compile(regex);
		if (!StringUtils.isNullOrEmpty(paramValue)) {
			Matcher matcher = paramPattern.matcher(paramValue);
			while (matcher.find()) {
				String value = matcher.group(1);
				if (!resultSet.contains(value)) {
					resultSet.add(value);
				}
			}
		}
	}

	public String getParamValue(String orgValue, Map<String, String> paramMap) {
		if (paramMap == null) {
			return orgValue;
		}
		String ret = orgValue;
		Set<String> matchSet = new HashSet<String>();
		resolveParamPattern(orgValue, matchSet);
		for (String item : matchSet) {
			String value = "";
			if (paramMap.containsKey(item)) {
				value = paramMap.get(item);
			}
			try {
				ret = ret.replaceAll("\\$\\{" + item + "\\}", value);
			} catch (RuntimeException ex) {
				throw ex;
			}
		}
		return ret;
	}

}
