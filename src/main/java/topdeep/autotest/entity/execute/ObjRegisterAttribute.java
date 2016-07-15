/**
 * 
 */
package topdeep.autotest.entity.execute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ObjRegisterAttribute {
	/**
	 * BeanId
	 * 
	 * @return
	 */
	String BeanId();
}
