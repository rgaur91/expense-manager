package com.pr.expense.security;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import org.springframework.security.authentication.BadCredentialsException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenService {

	private static Map<String, UserToken> validTokens = new ConcurrentHashMap<String, UserToken>();
	//Number of second till the token is valid is 10 minutes
	private static final Integer NUMBER_OF_SECONDS_TILL_TOKEN_IS_VALID = 600;
	private static final String SECERET_KEY = "$Fb!s1ks5aq"; //TODO

//	private static final Logger logger= LoggerFactory.getLogger(TokenService.class);

	public static void removeToken(String token)
	{
		validTokens.remove(token);
	}
	public static void addToken(String token,UserToken userToken)
	{
		validTokens.put(token, userToken);
	}

	public static String generateNewToken(Long UserId) {
		JWTSigner jwtSigner = new JWTSigner(SECERET_KEY);
		long currentTimeInSeconds = System.currentTimeMillis() / 1000L;
		long tokenDurationInSeconds = currentTimeInSeconds + NUMBER_OF_SECONDS_TILL_TOKEN_IS_VALID;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userInfo", UserId);
		map.put("exp", tokenDurationInSeconds);
		map.put("iat", currentTimeInSeconds);
		return jwtSigner.sign(map);
	}

	public static UserToken isValidToken(String token) {
		if(token == null){
			return null;
		}
		UserToken userToken = null;
		JWTVerifier jWTVerifier = new JWTVerifier(SECERET_KEY);
		try {
			Map<String, Object> map = jWTVerifier.verify(token);
			Integer userId= (Integer) map.get("userInfo");
			userToken =TokenService.validTokens.get(token);
			if(userId.equals(userToken.getUserId()))
					return userToken;
		} catch (NoSuchAlgorithmException e) {
			return null;
		} catch (InvalidKeyException e) {
			return null;
		} catch (IOException e) {
			return null;
		} catch (SignatureException e) {
			return null;
		} catch (JWTVerifyException e) {
			return null;
		}
		return userToken;
	}


	public static boolean checkValidityOfToken(String token)
	{
		UserToken userToken = TokenService.isValidToken(token);
		if(userToken==null)
		{
			throw new BadCredentialsException("Token is not Valid");
		}
		else if(userToken.isFirstLogin())
		{
			throw new BadCredentialsException("Accept Terms and Conditions Before Continuing");
		}
		return true;
	}
}
