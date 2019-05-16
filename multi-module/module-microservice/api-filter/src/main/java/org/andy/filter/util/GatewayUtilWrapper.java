package org.andy.filter.util;

import com.pateo.qingcloud.base.auth.common.session.AuthSession;
import com.pateo.qingcloud.gateway.common.GatewayConstant;
import com.pateo.qingcloud.gateway.common.util.GatewayUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class GatewayUtilWrapper {

    private static InheritableThreadLocal<Map<String, String>> contextHolder = new InheritableThreadLocal<>();

    public static void clearAuthSessionAdvice() {
        contextHolder.remove();
    }

    public static void setAuthSessionAdvice(HttpServletRequest servletRequest) {
        AuthSession authSession = GatewayUtil.getAuthSession(servletRequest);
        String authSessionJson = servletRequest.getHeader(GatewayConstant.HEADER_PATEO_SESSION);
        String projectId = GatewayUtil.getProjectId(servletRequest);
        int projectDat = GatewayUtil.getProjectDat(servletRequest);
        String projectKey = GatewayUtil.getProjectKey(servletRequest);
        int projectAccountAuthType = GatewayUtil.getProjectAccountAuthType(servletRequest);
        int projectAccountRoleAuthType = GatewayUtil.getProjectAccountRoleAuthType(servletRequest);
        Map<String, String> map = new HashMap<>();
        map.put("projectId", projectId);
        contextHolder.set(map);
    }

    public static Map<String, String> getAuthSessionAdvice() {
        return contextHolder.get();
    }

    public static Long getAuthSessionUid() {
        Map<String, String> sessionAdvice = getAuthSessionAdvice();
        String authSession = sessionAdvice.get("projectId");
        return 1L;
    }
}
