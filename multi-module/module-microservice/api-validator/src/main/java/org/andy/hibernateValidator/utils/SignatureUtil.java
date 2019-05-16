package org.andy.hibernateValidator.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * <p>Title: SignatureUtil.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 All rights reserved.</p>
 * <p>Company: PATEO Corporation, China, Ltd.</p>
 * <p>Date: Jul 12, 2018
 * @author junhao.zhan
 *
 */
public class SignatureUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(SignatureUtil.class);

	/**
	 * @param t
	 * @param sign
	 * @return
	 */
	public static <T> boolean valid(T t, String sign) {
		String _sign = getSign(t);
		return _sign.equals(sign);
	}

	/**
	 * @param t
	 * @return
	 */
	public static <T> String getSign(T t) {
		Set<Field> fields = ReflectionUtil.getAnnotatedFields(t.getClass(), SignatureUnion.class);
		String[] list = new String[fields.size()];
		fields.forEach(f -> {
			try {
				int i = Integer.valueOf(f.getAnnotation(SignatureUnion.class).value());
				String v = String.valueOf(f.get(t) == null ? "" : f.get(t));
				list[i] = v;
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});

		return getSign(list);
	}

	/**
	 * @param args
	 * @return
	 */
	public static String getSign(String... args) {
		String _data = String.join("", args);
		String _sign = MD5Util.EncoderByMd5(_data);
		LOGGER.info("===>>>Sign Original Data: {}", _data);
		LOGGER.info("===>>>Sign: {}", _sign);

		return _sign;
	}
}
