package org.andy.filter.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultStatusCode {

	SUCCESS(0, "success"),
	NO_PERMISSION_TO_ACCESS(1, "无权访问."),
	SIGN_ERROR(1000, "签名错误"),
	PARAM_ERROR(1001, "参数错误"),
	SYS_EXCEPTION(1002, "系统异常"),
	SERVICE_TIMEOUT(1003, "请求超时"),

	DUPLICATE_FENCE_NAME(12079001, "电子围栏名称重复了"),
	FENCE_MAX(12079002, "可创建的电子围栏数量已达到最大值"),
	FENCE_NOT_FOUND(12079003, "找不到指定电子围栏"),
	FENCE_ENABLE_NOT_FOUND(12079004, "找不到启用的电子围栏"),
	DEVICE_OWNER_NOT_FOUNT(12079005, "该设备找不到指定车主"),
	DEVICE_BIND_VEHICLE_NOT_FOUNT(12079006, "该设备找不到绑定的车辆"),
	VEHICLE_BIND_DEVICE_NOT_FOUNT(12079007, "该车辆找不到绑定的设备"),
	CURR_USER_IS_NOT_VEHICLE_OWNER(12079008, "当前用户不是车主，无法添加电子围栏"),
	MQTT_ERROR(12079009, "向Tbox发送消息失败！无法执行当前命令"),
	;

	private int code;
	private String msg;
}
