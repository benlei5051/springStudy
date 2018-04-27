/**
 *
 */
package org.andy.authority.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author seangogo
 */
@ConfigurationProperties(prefix = "operation.security")
@Getter
@Setter
public class SecurityProperties {
	/**
	 * 浏览器环境配置
	 */
	private BrowserProperties browser = new BrowserProperties();
	/**
	 * OAuth2认证服务器配置
	 */
	private OAuth2Properties oauth2 = new OAuth2Properties();

}

