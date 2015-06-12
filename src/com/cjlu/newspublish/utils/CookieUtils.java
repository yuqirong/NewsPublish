package com.cjlu.newspublish.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;

public final class CookieUtils {

	/**
	 * 涓嶅彲瀹炰緥鍖�
	 */
	private CookieUtils() {

	}

	/**
	 * 娣诲姞cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie鍚嶇О
	 * @param value
	 *            cookie鍊�
	 * @param maxAge
	 *            鏈夋晥璧�鍗曚綅: 绉�
	 * @param path
	 *            璺緞
	 * @param domain
	 *            鍩�
	 * @param secure
	 *            鏄惁鍚敤鍔犲瘑
	 */
	public static void addCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value,
			Integer maxAge, String path, String domain, Boolean secure) {
		Assert.notNull(request);
		Assert.notNull(response);
		Assert.hasText(name);
		try {
			name = URLEncoder.encode(name, "UTF-8");
			value = URLEncoder.encode(value, "UTF-8");
			Cookie cookie = new Cookie(name, value);
			if (maxAge != null) {
				cookie.setMaxAge(maxAge);
			}
			if (ValidateUtils.isValid(path)) {
				cookie.setPath(path);
			}
			if (ValidateUtils.isValid(domain)) {
				cookie.setDomain(domain);
			}
			if (secure != null) {
				cookie.setSecure(secure);
			}
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 娣诲姞cookie
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie鍚嶇О
	 * @param value
	 *            cookie鍊�
	 * @param maxAge
	 *            鏈夋晥鏈�鍗曚綅: 绉�
	 */
	public static void addCookie(HttpServletResponse response, String name,
			String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 鑾峰彇cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param name
	 *            cookie鍚嶇О
	 * @return 鑻ヤ笉瀛樺湪鍒欒繑鍥瀗ull
	 * @throws UnsupportedEncodingException 
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) throws UnsupportedEncodingException {
		name=URLDecoder.decode(name,"UTF-8");
		Assert.notNull(request);
		Assert.hasText(name);
		Cookie[] cookies = request.getCookies();
		Cookie c = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ((name).equals(cookie.getName())) {
					c = cookie;
					break;
				}
			}
		}
		if (c != null) {
			return c;
		}
		return null;
	}

	/**
	 * 绉婚櫎cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie鍚嶇О
	 * @param path
	 *            璺緞
	 * @param domain
	 *            鍩�
	 */
	public static void removeCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String path,
			String domain) {
		Assert.notNull(request);
		Assert.notNull(response);
		Assert.hasText(name);
		try {
			name = URLEncoder.encode(name, "UTF-8");
			Cookie cookie = new Cookie(name, null);
			cookie.setMaxAge(0);
			if (ValidateUtils.isValid(path)) {
				cookie.setPath(path);
			}
			if (ValidateUtils.isValid(domain)) {
				cookie.setDomain(domain);
			}
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 绉婚櫎cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie鍚嶇О
	 */
	// public static void removeCookie(HttpServletRequest request,
	// HttpServletResponse response, String name) {
	// Setting setting = SettingUtils.get();
	// removeCookie(request, response, name, setting.getCookiePath(),
	// setting.getCookieDomain());
	// }

}