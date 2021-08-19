package ink.kangaroo.common.core.utils;

import ink.kangaroo.common.core.constant.SecurityConstants;
import ink.kangaroo.common.core.text.Convert;
import io.jsonwebtoken.Claims;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/5 14:33
 */
public class SecurityUtils {
    public static Claims getClaims() {
        return JwtTokenUtil.parseJwtRsa256(getToken());
    }

    /**
     * 获取用户
     */
    public static String getUsername() {
        return SecurityUtils.getClaims().getSubject();
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        return Convert.toLong(SecurityUtils.getClaims().get(SecurityConstants.DETAILS_USER_ID));
    }

    /**
     * 获取请求token
     */
    public static String getToken() {
        return getToken(ServletUtils.getRequest());
    }

    /**
     * 根据request获取请求token
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.TOKEN_AUTHENTICATION);
        return replaceTokenPrefix(token);
    }

    /**
     * 替换token前缀
     */
    public static String replaceTokenPrefix(String token) {
        if (StringUtils.isNotEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
