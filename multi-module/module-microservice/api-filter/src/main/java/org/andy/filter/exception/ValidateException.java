package org.andy.filter.exception;

import lombok.Getter;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author wiiyaya
 * @date 2018/11/29.
 */
@Getter
public class ValidateException extends RuntimeException {
	private static final long serialVersionUID = -387933392322727143L;

	private final List<String> codeList;

	private final Map<String, Object[]> argumentsMap;

	public ValidateException(List<String> codeList, Map<String, Object[]> argumentsMap, List<String> messageList) {
		super(StringUtils.join(messageList, "|"));
		this.codeList = codeList;
		this.argumentsMap = argumentsMap;
	}
}
