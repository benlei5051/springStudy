package org.andy.filter.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "统一返回泛型结果集")
public class ResultData<T> {

	@ApiModelProperty("返回码")
	private int statusCode;

	@ApiModelProperty("返回码描述")
	private String statusMessage;

	@ApiModelProperty("返回数据")
	private T data;

	private ResultData() {
		this(null);
	}

	private ResultData(T t) {
		this.statusCode = ResultStatusCode.SUCCESS.getCode();
		this.statusMessage = ResultStatusCode.SUCCESS.getMsg();
		this.data = t;
	}

	private ResultData(int statusCode, String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public static <T> ResultData<T> success() {
		return new ResultData<>();
	}

	public static <T> ResultData<T> success(T t) {
		return new ResultData<>(t);
	}

	public static <T> ResultData<T> failed(int statusCode, String statusMessage) {
		return new ResultData<>(statusCode, statusMessage);
	}
}
