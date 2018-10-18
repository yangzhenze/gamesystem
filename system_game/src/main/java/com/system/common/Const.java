/**
 *
 */
package com.system.common;

/**
 * @author y.z
 */
public class Const {
	// 错误代码
	public static final Integer SYS_ERROR_CODE = -1; //系统错误
	public static final Integer ISBLANK_ERROR_CODE = -2; //必传参数不能为空
	public static final Integer ADMIN_ERROR_CODE = -3; //用户登录过期
	public static final Integer OTHER_ERROR_CODE = -4; //自定义错误码
	public static final Integer AUTH_EROOR_CODE = -5; //自定义错误码
	public static final Integer SUCCESS_CODE = 0;//成功码

	//错误消息
	public static final String SYS_ERROR_MSG = "系统错误";
	public static final String ISBLANK_ERROR_MSG = "必传参数不能为空";
	public static final String ADMIN_ERROR_MSG = "用户登录过期,请重新登录";
	public static final String SUCCESS_MSG = "数据获取成功";
	public static final String AUTH_EROOR_MSG = "没有限权";
	public static final String UPDATE_SUCCESS_MSG = "修改成功";
	public static final String ADD_SUCCESS_MSG = "添加成功";
	public static final String DEL_SUCCESS_MSG = "删除成功";

	public static final int PAGE_SIZE = 10;


	/*是否*/
	public final static int BOOLEAN_TYPE = 1001000;
	public final static int BOOLEAN_TYPE_TRUE = 1001001;
	public final static int BOOLEAN_TYPE_FALSE = 1001002;

	/*程序分类*/
	public final static int PROGRAM_TYPE = 1002000;
	public final static int PROGRAM_TYPE_SERVER = 1002001;
	public final static int PROGRAM_TYPE_CLIENT = 1002002;

	/*设备分类*/
	public final static int DEVICE_TYPE = 1003000;
	public final static int DEVICE_TYPE_IPHONE = 1003001;
	public final static int DEVICE_TYPE_ANDROID = 1003002;
	public final static int DEVICE_TYPE_LINUX = 1003003;
	public final static int DEVICE_TYPE_WINDOWS = 1003004;

	/*日志分类*/
	public final static int LOG_TYPE = 1004000;
	public final static int LOG_TYPE_ACTION = 1004001;
	public final static int LOG_TYPE_PROGRAM = 1004002;
	public final static int LOG_TYPE_DEVICE = 1004003;

	/*事件类型*/
	public final static int EVENT_TYPE = 1005000;
	public final static int EVENT_TYPE_PROGRAM_WARN = 1005001;
	public final static int EVENT_TYPE_PROGRAM_INFO = 1005002;
	public final static int EVENT_TYPE_PROGRAM_ERROR = 1005003;
	public final static int EVENT_TYPE_PROGRAM_FATAL = 1005004;
	public final static int EVENT_TYPE_REGISTER = 1005005;
	public final static int EVENT_TYPE_CREATE_ROLE = 1005006;
	public final static int EVENT_TYPE_ROLE_LOGIN = 1005007;
	public final static int EVENT_TYPE_ROLE_LOGOUT = 1005008;
	public final static int EVENT_TYPE_ONLINE_USER = 1005009;
	public final static int EVENT_TYPE_LEVELUP = 1005010;
	public final static int EVENT_TYPE_ADDCASH = 1005011;
	public final static int EVENT_TYPE_ADD_YUANBAO = 1005012;
	public final static int EVENT_TYPE_COST_YUANBAO = 1005013;
	public final static int EVENT_TYPE_SHOP_TRADE = 1005014;
	/*事件类型-客户端专属*/
	public final static int EVENT_TYPE_CLIENT_START = 1005501;

	/*定时任务状态*/
	public final static int JOB_STATUS_TYPE = 2001000;
	public final static int JOB_STATUS_TYPE_NEW = 2001001;
	public final static int JOB_STATUS_TYPE_READY = 2001002;
	public final static int JOB_STATUS_TYPE_COMPLETE = 2001003;
}
