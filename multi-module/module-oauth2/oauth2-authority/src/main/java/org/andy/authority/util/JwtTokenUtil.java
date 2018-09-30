package org.andy.authority.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.andy.authority.domain.Account;
import org.andy.authority.domain.User;
import org.andy.authority.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author seangogo
 */
@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    private static final String CLAIM_KEY_CREATED = "created";

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 从redis中获取登陆账户的相关信息
     *
     * @param request
     * @param key
     * @param clz
     * @return
     * @throws UnsupportedEncodingException
     */
    public <T> T getObjectFromToken(HttpServletRequest request, String key, Class<T> clz) throws UnsupportedEncodingException {
        String token = StringUtils.substringAfter(request.getHeader("Authorization"), "bearer ");
        Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
                .parseClaimsJws(token).getBody();
        return claims.get(key, clz);
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
        } catch (Exception e) {
            username = null;
        }
        return "Sysadmin";
    }

    private Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    private Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public String getUserName(HttpServletRequest request) throws UnsupportedEncodingException {
        return getObjectFromToken(request, "username", String.class);
    }

    public String getOrgCode(HttpServletRequest request) throws UnsupportedEncodingException {
        return getObjectFromToken(request, "orgCode", String.class);
    }

    public String getDealerNoFromLogin(HttpServletRequest request) throws UnsupportedEncodingException {
        String orgCode = getObjectFromToken(request, "orgCode", String.class);
        String[] array = orgCode.split("-");
        if (array.length < 4) {
            return null;
        }
        return array[3];
    }

    /**
     * 0 pateo 1 车厂  2 经销商
     *
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getOrgType(HttpServletRequest request) throws UnsupportedEncodingException {
        String orgCode = getObjectFromToken(request, "orgCode", String.class);
        String[] array = orgCode.split("-");
        return String.valueOf(array.length - 1);
    }

    public Set<String> getProjectCodes(HttpServletRequest request) throws UnsupportedEncodingException {
        return new HashSet<String>(getObjectFromToken(request, "projectCodes", List.class));
    }

    public Set<String> getBrandCodes(HttpServletRequest request) throws UnsupportedEncodingException {
        return new HashSet<String>(getObjectFromToken(request, "brandCodes", List.class));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && !isTokenExpired(token);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        Account account = user.getAccount();
        final String username = getUsernameFromToken(token);
        final Date created = getCreatedDateFromToken(token);
        return (
                username.equals(account.getUsername())
                        && !isTokenExpired(token)
                        && !isCreatedBeforeLastPasswordReset(created, account.getExpireTime()));
    }
}