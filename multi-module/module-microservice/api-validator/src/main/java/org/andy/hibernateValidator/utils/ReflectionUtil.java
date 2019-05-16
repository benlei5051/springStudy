package org.andy.hibernateValidator.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Title: ReflectionUtil.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 All rights reserved.</p>
 * <p>Company: PATEO Corporation, China, Ltd.</p>
 * <p>Date: Jul 12, 2018
 * @author junhao.zhan
 *
 */
public class ReflectionUtil {

	private ReflectionUtil() {
		super();
	}

	/**
	 * Class.getFields() gives me the array of public attributes
	 * Class.getDeclaredFields() gives me the array of all fields, but none of them includes the inherited fields list.
	 * I want all fields, including inherited fields.
	 * I don't know why Java doesn't support this.
	 *
	 * @param fields
	 * @param type
	 * @return Collection<Field>
	 */
	public static Collection<Field> getAllFields(Collection<Field> fields, Class<?> type) {
		for (Field field : type.getDeclaredFields()) {
			fields.add(field);
		}

		if (type.getSuperclass() != null) {
			getAllFields(fields, type.getSuperclass());
		}

		return fields;
	}

	/**
	 * Return all fields having the specified annotation
	 *
	 * @param annotatedClass
	 * @param annotationClass
	 * @return Set<java.lang.reflect.Field>
	 */
	public static <T extends Annotation> Set<Field> getAnnotatedFields(Class<?> annotatedClass, Class<T> annotationClass) {

		Set<Field> allFields = new HashSet<>();
		ReflectionUtil.getAllFields(allFields, annotatedClass);
		Set<Field> annotatedFields = new HashSet<>();

		for (Field field : allFields) {
			Annotation annotation = field.getAnnotation(annotationClass);
			if (annotation != null) {
				field.setAccessible(true);
				annotatedFields.add(field);
			}
		}

		return annotatedFields;
	}

	/**
	 * Return all fields having the specified annotation
	 *
	 * @param annotatedClass
	 * @param annotationClass
	 *
	 * @return HashMap<Object, java.lang.reflect.Field> key = annotations value() if present.
	 *
	 * @throws SecurityException
	 * @throws NoSuchMethodException thrown if the value() method is not defined on the annotationClass
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Annotation, U> HashMap<U, Field> getAnnotatedFieldsMap(Class<?> annotatedClass, Class<T> annotationClass, Class<U> annotationValueClass)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method valueMethod = annotationClass.getMethod("value");

		Set<Field> allFields = new HashSet<>();
		ReflectionUtil.getAllFields(allFields, annotatedClass);
		HashMap<U, Field> annotatedFields = new HashMap<>();

		for (Field field : allFields) {
			Annotation annotation = field.getAnnotation(annotationClass);
			if (annotation != null) {
				field.setAccessible(true);
				annotatedFields.put((U) valueMethod.invoke(annotation), field);
			}
		}

		return annotatedFields;
	}

}
