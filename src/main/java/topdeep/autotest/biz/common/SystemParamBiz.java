package topdeep.autotest.biz.common;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import topdeep.autotest.entity.params.ApplicationParam;
import topdeep.autotest.entity.params.BaseSystemParam;
import topdeep.autotest.entity.params.DbParam;

/**
 * 
 * @author shidewen
 * 
 */
@Service("systemParamBiz")
public class SystemParamBiz extends BaseBiz implements InitializingBean {

	@Autowired
	private ApplicationParam ApplicationParam;

	@Autowired
	private DbParam dbParam;

	private void initParamMap(Class<?> classType, Map<String, BaseSystemParam> paramMap) throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = classType.getDeclaredFields();
		for (Field item : fields) {
			Class<?> type = item.getType();
			if (!BaseSystemParam.class.isAssignableFrom(type)) {
				continue;
			}
			String typeName = type.getName();
			if (!paramMap.containsKey(typeName)) {
				item.setAccessible(true);
				paramMap.put(typeName, (BaseSystemParam) item.get(this));
				item.setAccessible(false);
			}
		}

	}

	private Map<String, BaseSystemParam> getParamMap() throws IllegalArgumentException, IllegalAccessException {
		Map<String, BaseSystemParam> paramMap = new TreeMap<String, BaseSystemParam>();
		Class<?> currentClass = getClass();
		while (SystemParamBiz.class.isAssignableFrom(currentClass)) {
			initParamMap(currentClass, paramMap);
			currentClass = currentClass.getSuperclass();
		}
		return paramMap;
	}

	private Field getDeclaredField(Class<?> type, String fieldName) throws SecurityException, NoSuchFieldException {
		if (!BaseSystemParam.class.isAssignableFrom(type)) {
			return null;
		}
		Field[] fields = type.getDeclaredFields();
		Field field = null;
		for (Field item : fields) {
			if (item.getName().equals(fieldName)) {
				field = item;
				break;
			}
		}

		if (field == null) {
			field = getDeclaredField(type.getSuperclass(), fieldName);
		}
		return field;
	}

	private void setFieldValue(Object obj, String fieldName, String fieldValue) throws SecurityException, NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		Class<?> type = obj.getClass();
		Field field = getDeclaredField(type, fieldName);
		if (field != null) {
			logger.debug("set param: " + type.getName() + "." + fieldName + "=" + fieldValue);
			String fieldTypeName = field.getType().getName();
			field.setAccessible(true);
			if ("int".equals(fieldTypeName)) {
				field.setInt(obj, Integer.parseInt(fieldValue));
			} else if ("java.lang.String".equals(fieldTypeName)) {
				field.set(obj, fieldValue);
			} else {
				logger.debug("field type = " + field.getType().getName());
			}
			field.setAccessible(false);
		}
	}

	private void printFieldValue(Object obj) throws IllegalArgumentException, IllegalAccessException {
		Class<?> type = obj.getClass();
		Field[] fields = type.getDeclaredFields();
		for (Field item : fields) {
			item.setAccessible(true);
			logger.debug("\t" + item.getName() + "=[" + item.get(obj) + "]");
			item.setAccessible(false);
		}
	}

	public void init() {
		// logger.info("load param from db");
		// try {
		// Map<String, BaseSystemParam> paramMap = getParamMap();
		// List<AtObjParams> paramList = atObjParamsSelectObjects(new AtObjParamsExample());
		// for (AtObjParams item : paramList) {
		// BaseSystemParam param = paramMap.get(item.getParamType());
		// if (param != null) {
		// setFieldValue(param, item.getParamCode(), item.getParamValue());
		// }
		// }
		// } catch (Exception e) {
		// logger.error("初始化系统参数错误!", e);
		// }
		// debugParams();
	}

	private void debugParams() {
		logger.info("current bankforward param list start:");
		try {
			Map<String, BaseSystemParam> paramMap = getParamMap();
			for (String item : paramMap.keySet()) {
				logger.debug(item);
				printFieldValue(paramMap.get(item));
			}
		} catch (Exception e) {
			logger.error("打印系统参数错误!", e);
		}
		logger.info("current bankforward param list end!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		init();
	}

	public ApplicationParam getApplicationParam() {
		return ApplicationParam;
	}

	public DbParam getDbParam() {
		return dbParam;
	}


}
