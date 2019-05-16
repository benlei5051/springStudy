package org.andy.hibernateValidator.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * <p>Description: </p>
 * @author <a href="mailto:gaolu@pateo.com.cn"> gaolu </a>
 * @version $Revision 1.1 $ 2018年10月20日 上午10:32:22
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface SignatureUnion {

	/**
	 * the value may indicate the index of signature
	 * @return String index of signature
	 */
	String value();

}
