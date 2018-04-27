/**
 *
 */
package org.andy.authority.server;

import org.andy.authority.domain.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author seangogo
 */
public class TokenJwtEnhancer implements TokenEnhancer {
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap(4);
		User user=(User)authentication.getPrincipal();
		info.put("username",user.getUsername());
//		info.put("orgCode",user.getOrgCode());
//		info.put("brandCodes",user.getBrandCodes());
		info.put("projectCodes",user.getProjectCodes());
		((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
