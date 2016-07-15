package topdeep.autotest.util.Converter;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.convert.converter.Converter;

import common.util.StringUtils;


/**
 * @author niexin
 *
 */
public class StringToDateConverter implements Converter<String, Date> {

	private static Log logger = LogFactory.getLog(StringToDateConverter.class);
	
	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	public Date convert(String source) {
		if(StringUtils.isNullOrEmpty(source)){
			return null;
		}
		source = source.trim();
		if(StringUtils.isNullOrEmpty(source)){
			return null;
		}
		source = source.replace("-", "").replace(":", "").replace(" ", "");
		try {
			if(source.length() == 8){
				return StringUtils.stringToDate(source, "yyyyMMdd");	
			}else if(source.length() == 14){
				return StringUtils.stringToDate(source, "yyyyMMddHHmmss");
			}else{
				logger.warn("StringToDateConverter error: "+source);
				return null;
			}
			
		} catch (ParseException e) {
			logger.debug("StringToDateConverter error! "+e.getMessage(), e);
		}
		return null;
	}

}
