package com.pr.expense.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

	public TokenAuthenticationProvider() {

	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UserToken token = (UserToken) authentication.getPrincipal();
		if (token != null) {
			return new PreAuthenticatedAuthenticationToken(token, null, authentication.getAuthorities());
		} else {
			throw new BadCredentialsException("Invalid token or token expired");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(PreAuthenticatedAuthenticationToken.class);
	}
}
