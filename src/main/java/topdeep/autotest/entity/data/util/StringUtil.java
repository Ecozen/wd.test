package topdeep.autotest.entity.data.util;

public class StringUtil {
    public static String removeSpaces(String str){
    	return str.replaceAll("[\\s]+", "");
    }
}
