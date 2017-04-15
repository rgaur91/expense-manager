package com.pr.expense.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TokenAuthenticationFilter extends GenericFilterBean {

	public static final String HEADER_SECURITY_TOKEN = "X-Auth-Token";

	public TokenAuthenticationFilter() {

	}

	private static final String ORIGIN = "Origin";


	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		UserToken userToken = null;
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		HttpServletResponse httpResponse = (HttpServletResponse) res;
		String token = httpRequest.getHeader(HEADER_SECURITY_TOKEN);
		if (!httpRequest.getMethod().equalsIgnoreCase("OPTIONS")) {

			userToken = TokenService.isValidToken(token);
			if (userToken == null) {
				sendUnauthorizedError(httpRequest, httpResponse);
				return;
			}
			try {
				TokenService.checkValidityOfToken(token);
			} catch (BadCredentialsException bce) {
				sendUnauthorizedError(httpRequest, httpResponse);
				return;
			}
			if (userToken.isFirstLogin()) {
				sendError(httpRequest, httpResponse, HttpServletResponse.SC_PRECONDITION_FAILED, "Please Accept terms and Conditions");
			}
			userToken.setLastAccessedTime(new Date());
		}
		List<GrantedAuthority> gaList = new ArrayList<>(2);
		gaList.add(new SimpleGrantedAuthority("ROLE_USER"));
		if (userToken != null && userToken.isAdmin()) {
			gaList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		PreAuthenticatedAuthenticationToken requestAuthentication = new PreAuthenticatedAuthenticationToken(userToken,
				null, gaList);
		SecurityContextHolder.getContext().setAuthentication(requestAuthentication);
		chain.doFilter(httpRequest, httpResponse);
	}

	private void sendUnauthorizedError(HttpServletRequest httpRequest, HttpServletResponse httpResponse)
			throws IOException {
		sendError(httpRequest, httpResponse, HttpServletResponse.SC_UNAUTHORIZED, "Token is not Valid");
	}

	private void sendError(HttpServletRequest httpRequest, HttpServletResponse httpResponse, int statusCode,
			String msg) throws IOException {
		String origin = httpRequest.getHeader("Origin");
		String header = httpRequest.getHeader("Access-Control-Request-Headers");

		httpResponse.setHeader("Access-Control-Allow-Origin", origin);
		httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
		httpResponse.setHeader("Access-Control-Allow-Headers", header + ", x-auth-token");
		httpResponse.sendError(statusCode, msg);
	}

}


