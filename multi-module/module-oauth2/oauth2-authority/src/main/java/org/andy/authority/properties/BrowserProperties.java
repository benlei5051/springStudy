/**
 *
 */
package org.andy.authority.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 浏览器环境配置项
 *
 * @author zhailiang
 *
 */
@Getter
@Setter
public class BrowserProperties {

	/**
	 * session管理配置项
	 */
	private SessionProperties session = new SessionProperties();
	/**
	 * 登录页面，当引发登录行为的url以html结尾时，会跳到这里配置的url上
	 */
	private String signInPage;
	/**
	 * '记住我'功能的有效时间，默认1小时
	 */
	private int rememberMeSeconds = 3600;

	/**
	 * 超级管理员角色的code
	 */
	private String Sysadmin;

	/**
	 * 超级管理员角色的初始密码
	 */
	private String password;
}
