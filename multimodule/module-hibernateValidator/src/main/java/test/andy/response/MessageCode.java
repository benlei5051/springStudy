package test.andy.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by zhengjun.jing on 7/13/2017.
 */
public enum MessageCode {
    /** ===========系统公用 Message Code===================*/
    /** 前缀 0000*/
    COMMON_SUCCESS("0000_0","执行成功"),
    COMMON_FAILURE("0000_1", "执行失败"),
    COMMON_NO_AUTHORIZED("0000_2","没有权限执行"),
    COMMON_NO_DATA("0000_3","查询不到对应数据"),
    COMMON_PARAMETER_ERROR("0000_4","参数错误"),
    COMMON_UNKNOWN_ERROR("0000_11","未知异常"),
    COMMON_DB_ERROR("0000_12","数据库操作异常"),
    COMMON_API_ERROR("0000_13","操作异常"),
    COMMON_SERVICE_ERROR("0000_14","服务异常"),
    COMMON_USER_NOT_EXIST("0000_15","用户不存在"),
    COMMON_DATA_NOT_EXIST("0000_16","数据不存在"),
    COMMON_UPLOAD_FILE_EXTENSION_ERROR("0000_17","上传文件格式不正确！"),
    COMMON_USERNAME_IS_USED("0000_18","用户名已被使用"),
    COMMON_PHONE_FORMAT_ERROR("0000_19","该手机号码不符合规则"),
    COMMON_PHONE_IS_USED("0000_20","该联系电话已被使用"),
    COMMON_EMAIL_IS_USED("0000_21","该邮箱已被使用"),
    COMMON_IMPORT_FAILURE("0000_22","文件导入存在失败数据"),

    /** ========= 用户管理 Message Code====================*/
    /** 前缀 0001*/
    USER_INVALIDATE_PHONE("0001_1","手机号码不存在"),
    OLD_PASSWORD_ERROR("0001_2", "旧密码不正确"),
    USER_PHONE_EXIST("0001_3", "该联系电话已被使用"),
    USER_EMAIL_EXIST("0001_4", "该邮箱已被使用"),
    USER_PHONE_NOT_MATCH("0001_5", "手机号码和用户名不匹配"),
    USER_ROLE_NAME_EXIST("0001_6", "角色名称已存在"),
    USER_ROLE_TEMPLATE_NOT_EXIST("0001_7", "角色模板不存在"),
    USER_ONLY_CHANGEINFO_BYSELF("0001_8", "当前用户只能修改自己的基本信息"),
    USER_CREATEUSER_FAILED("0001_9", "创建用户失败"),



    /** ========= 订单管理 Message Code =================== */
    /**
     * 前缀 0002
     */
    ORDER_MONTH_LIMIT_LEFT_OVERDRAFT("0002_1", "月度余额不足"),
    ORDER_RULE_UNSATISFACTORY("0002_2", "用车规则不满足"),
    ORDER_DATE_FORMAT_ERROR("0002_3", "日期格式错误"),





    /**============车辆管理 Message Code=====================*/
    /** 前缀 0003*/

    VEHICLE_NUMBER_EXIST("0003_1","该车牌号已存在，请重新输入！"),
    VEHICLE_IDENTIFICATION_EXIST("0003_2","该车架号已被绑定，请重新输入！"),
    VEHICLE_UPDATE_SPEED_LIMIT_FAIL("0003_3","下发限速失败！"),
    VEHICLE_UPDATE_SPEED_LIMIT_FAIL_WITHOUT_DEVICE("0003_4","车辆未绑定设备,下发限速失败！"),
    VEHICLE_CREATE_ORG_VALIDATION_FAIL("0003_5","无权限在所选部门创建车辆"),
    VEHICLE_NOT_EXIST_ERROR("0003_6","车辆不存在"),
    VEHICLE_NOT_EXIST_CURRENTUSER("0003_7","车辆不属于当前用户"),
    VEHICLE_WITHOUT_STATION("0003_8","车辆尚未分配到站点，无法分配默认司机"),
    VEHICLE_ENT_NOTASSIGN("0003_9","企业用户不能分配车辆给自己或移除车辆"),
    VEHICLE_HAS_ASSIGNED("0003_10","车辆不存在或已分配给部门，不能再次分配"),
    VEHICLE_NOT_EXIST_DEPT("0003_11","移除的车辆不属于部门，不能移除"),
    VEHICLE_DEPTID_DEPTNAME_SYNCH("0003_12","部门ID与部门名称都为空或都不为空"),
    VEHICLE_DEPTID_DEPTNAME_NOT_SYNCH("0003_13","部门ID与部门名称不一致"),
    VEHICLE_PROVINCE_CITY_SYNCH("0003_14","所属省市都为空或都不为空"),
    VEHICLE_PROVINCE_CITY_NOT_SYNCH("0003_15","所属省与所属市不一致"),


	/**============部门管理 Message Code=====================*/
    /** 前缀 0004*/

	DEPT_HAS_RESOURCE_UPDATE("0004_1","该部门有资源信息，无法修改上级部门！"),
	DEPT_HAS_RESOURCE_DELETE("0004_2","该部门有资源信息，删除失败！"),
	DEPT_NOT_EXIST_CURRENTUSER("0004_3","部门不属于当前用户！"),

	/**============设备管理Message Code=====================*/
    /** 前缀 0005*/
	SNNUMBER_EXIST("0005_1","snNumber已经存在！"),
	IMEINUMBER_EXIST("0005_2","imeiNumber已经存在！"),
	ICCIDNUMBER_EXIST("0005_3","iccidNumber已经存在！"),
	SIMNUMBER_EXIST("0005_4","SIM卡已经存在！"),
	VEHICLE_NUMBER_ALREADY_BIND("0005_5","该车牌号已经绑定！"),
	SIM_NUMBER_ALREADY_BIND("0005_6","该SIM卡号已经绑定！"),
	DEVICE_BIND_LICENSE_FAILTRUE("0005_7"),
	LICENSE_ACTIVATE_FAILTRUE("0005_8"),
	LICENSE_SUSPEND_FAILTRUE("0005_9"),
	LICENSE_REACTIVATE_FAILTRUE("0005_10"),
	LICENSE_TERMINATED_FAILTRUE("0005_11"),
	LICENSE_UNBIND_FAILTRUE("0005_12"),
	DEVICE_NOT_EXIST_ERROR("0005_13","设备不存在"),
	DEVICE_CREATE_SET_SPEED_LIMIT_FAIL("0005_14","添加成功，下发限速失败！"),
	DEVICE_UPDATE_SET_SPEED_LIMIT_FAIL("0005_15","修改成功，下发限速失败！"),
	DEVICE_BATCH_IMPORT_FAILTRUE("0005_16"),
	DEVICE_NOT_SELECTED_ERROR("0005_17","请选择出库的设备"),

	/**============司机管理Message Code=====================*/
    /** 前缀 0006*/
	DRIVER_LICENSEBEGINTIME_FORMAT_ERROR("0006_1","初次领证时间格式错误！"),
	DRIVER_LICENSEEXPIRETIME_FORMAT_ERROR("0006_2","驾照到期时间格式错误！"),
	DRIVER_BIRTHDAY_FORMAT_ERROR("0006_3","出生日期格式错误！"),
	DRIVER_IMPORT_LICENSETYPE_ISBLANK("0006_4","驾照类型为空，导入失败！"),
	DRIVER_IMPORT_LICENSENUMBER_ISBLANK("0006_5","驾照号码为空，导入失败！"),
	DRIVER_IMPORT_LICENSENUMBER_EXIST("0006_6","驾照号码已存在，导入失败！"),
	DRIVER_IMPORT_LICENSENUMBER_FORMAT_ERROR("0006_7","驾照号码格式错误，导入失败！"),
	DRIVER_IMPORT_LICENSEBEGINTIME_ISBLANK("0006_8","初次领证时间为空，导入失败！"),
	DRIVER_IMPORT_LICENSEBEGINTIME_GREATER_NOW("0006_9","初次领证时间大于现在的时间，导入失败！"),
	DRIVER_IMPORT_BIRTHDAY_ISBLANK("0006_10","出生日期为空，导入失败！"),
	DRIVER_IMPORT_ISMINOR_ERROR("0006_11","出生日期未满18周岁，导入失败！"),
	DRIVER_IMPORT_LICENSEBEGINTIME_ISMINOR("0006_12","初次领证时间未满18周岁，导入失败！"),
	DRIVER_IMPORT_LICENSEEXPIRETIME_ISBLANK("0006_13","驾照到期时间为空，导入失败！"),
	DRIVER_IMPORT_LICENSEBEGINTIME_GREATER_EXPIRETIME("0006_14","驾照到期时间应大于初次领证时间，导入失败！"),
	DRIVER_IMPORT_STATION_ENT_DRIVER_DIFF("0006_15","站点所属企业与司机所属企业不一致，导入失败！"),
	DRIVER_IMPORT_STATION_NAME_NOT_EXIST("0006_16","所属企业找不到站点名称，导入失败！"),
	DRIVER_NOT_EXIST("0006_17","司机不存在"),
	DRIVER_LICENSE_NUMBER_IS_USED("0006_18","该驾照号码已被使用"),
	DRIVER_IMPORT_REALNAME_ISBLANK("0006_19","司机姓名为空，导入失败！"),
	DRIVER_DRIVER_HAS_ALLOCATE_VEHICLE("0006_20","该司机被分配为默认司机,删除失败"),
	DRIVER_HAS_UNFINISHED_ORDER("0006_21","该司机有未完成的订单,删除失败"),

	/**============文件导入导出管理Message Code=====================*/
    /** 前缀 0007*/
	FILE_IMPORT_USERNAME_ISBLANK("0007_1","用户名为空，导入失败！"),
	FILE_IMPORT_REALNAME_ISBLANK("0007_2","真实姓名为空，导入失败！"),
	FILE_IMPORT_PHONE_ISBLANK("0007_3","手机号码为空，导入失败！"),
	FILE_IMPORT_PHONE_FORMAT_ERROR("0007_4","手机号码不合法，导入失败！"),
	FILE_IMPORT_ORGANIZATIONNAME_ISBLANK("0007_5","所属企业为空，导入失败！"),
	FILE_IMPORT_SEX_ISBLANK("0007_6","性别为空，导入失败！"),
	FILE_IMPORT_USERNAME_EXIST("0007_7","用户名已被使用，导入失败！"),
	FILE_IMPORT_PHONE_EXIST("0007_8","手机号码已被使用，导入失败！"),
	FILE_IMPORT_EMAIL_EXIST("0007_9","邮箱已被使用，导入失败！"),
	FILE_IMPORT_EMAIL_FORMAT_ERROR("0007_10","邮箱格式错误，导入失败！"),
	FILE_IMPORT_PASSWORD_FORMAT_ERROR("0007_11","密码不符合要求，密码由6-20位数字、字母组成，导入失败！"),
	FILE_IMPORT_ENT_DEFF_ERROR("0007_12","企业名称与当前企业不一致，导入失败！"),
	FILE_IMPORT_ENTNAME_ISBLANK("0007_13","请填写所属企业名称，导入失败！"),
	FILE_IMPORT_ENTNAME_NOT_EXIST("0007_14","找不到企业名称，导入失败！"),
	FILE_IMPORT_DEP_DEFF_ERROR("0007_15","所属部门不属于当前企业,导入失败!"),
	FILE_IMPORT_DEPNAME_ISBLANK("0007_16","请填写正确的部门名称，导入失败！"),
	FILE_IMPORT_DEPNAME_NOT_EXIST("0007_17","找不到部门名称，导入失败！"),


	/**============员工管理Message Code=====================*/
    /** 前缀 0008*/
	EMP_NOT_EXIST("0008_1","员工不存在"),
	EMP_HAS_UNFINISHED_ORDER("0008_2","该员工有未完成的订单,删除失败"),
	EMP_CANNOT_DELETE_SELF("0008_3","无法删除登录用户,删除失败"),

	/**============公告消息Message Code=====================*/
	MESSAGE_CENTER_EXIST("0009_1","消息不存在"),
	MESSAGE_CENTER_READ("0009_2","消息已经置为已读"),

	/**============车辆维保Message Code=====================*/
	HEAD_MAINTENANCE_MILEAGE_LESSTHAN_LASTTIME("0010_1","本次保养表头里程数小于上次保养表头里程数,未更新！"),
	MAINTENANCE_TIME_BEFORE_CURRENT_MAINTENANCE_TIME("0010_2","本次保养时间小于当前记录的保养时间,未更新！"),
	MAINTENANCE_NOT_EXIST_ERROR("0010_3","保养记录不存在！"),

	/**============IAM Message Code=====================*/
	IAM_PATCH_GROUP_ERRO("0011_2","设置用户与组失败"),
	IAM_CREATE_USER_ERRO("0011_1","在IAM中创建用户失败"),

	/**============Marker管理 Message Code=====================*/
    /** 前缀 0012*/
	MARKER_NOT_EXIST_ERROR("0012_1","Marker不存在"),
	MARKER_ASSIGNED_VEHICEL_NOT_EXIST("0012_2","分配给地理围栏的车辆不存在"),
	MARKER_NOT_EXIST_CURRENTUSER("0012_3","地理围栏不属于当前用用户"),
	MARKER_CREATE_FAILED("00012_4","创建地理围栏失败"),
	MARKER_CREATE_PATTERN_ERROR("00012_5","输入pattern格式错误"),
	MARKER_VEHICLE_NOT_UNASSIGNED("0012_6","车辆已经分配到地理围栏，不能再次分配"),
	MARKER_VEHICLE_NOT_ASSIGNED("0012_7","车辆还未分配到地理围栏不能移除"),
	MARKER_AREAID_NOT_EMPTY("0012_8","行政区域id不能为空"),
	MARKER_UPDATE_FAILED("00012_9","修改地理围栏失败"),

	/**============保险年检Message Code=====================*/
	CHECK_INSURANCETIME_ERROR("0013_1","设置的时间必须大于上次保险时间"),
	CHECK_INSPECTIONTIME_ERROR("0013_2","设置的时间必须大于下次年检时间"),

	/**============规则管理Message Code=====================*/
	RULE_CHECK_RULEID_EXIST("0014_1","用户已绑定该规则,不可重复绑定"),
	RULE_CHECK_RULEID_NOT_EXIST("0014_2","用户未绑定该规则,不可进行解绑"),
	GET_ON_ADDRESS_LIST_IS_NULL("0014_3","上车地址列表为空"),
	GET_OFF_ADDRESS_LIST_IS_NULL("0014_4","下车地址列表为空"),

	/**==========站点管理Message Code========================*/
	STATION_NOT_EXIST_CURRENTUSER("0015_1","站点不属于当前用用户"),
	STATION_VEHICLE_NOT_EXIST_CURRENTUSER("0015_2","分配到站点的车辆不属于当前用用户"),
	STATION_VEHICLE_NOT_UNASSIGNED("0015_3","车辆已经分配到站点，不能再次分配"),
	STATION_VEHICLE_NOT_ASSIGNED("0015_4","车辆还未分配到站点不能移除"),
	STATION_CREATE_FAILED("00015_5","创建站点失败"),
	STATION_UPDATE_FAILED("00015_6","修改站点失败"),

	/**==========组织管理Message Code========================*/
	CREATE_ORGANIZATION_NAME_IS_EXIST("0016_1","该企业名已存在,添加企业失败"),
	UPDATE_ORGANIZATION_NAME_IS_EXIST("0016_2","该企业名已存在,更新企业失败"),
	FIND_ORGANIZATION_ID_IS_NOT_EXIST("0016_3","该企业ID不存在");

    //Message 编码
    private String code;
    //Message 描叙
    private String message;

    MessageCode(String code){
        this.code = code;
    }

    MessageCode(String code, String message){
        this.code = code;
        this.message = message;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    public String getMsg() {
        return message;
    }


    @JsonCreator
    public static MessageCode getStatusCode(String status) {
        for (MessageCode unit : MessageCode.values()) {
            if (unit.getCode().equals(status)) {
                return unit;
            }
        }

        return null;
    }


    @Override
    public String toString() {
        return "{code:'" + code + '\'' +
                ", message:'" + message + '\'' +
                '}';
    }
}
