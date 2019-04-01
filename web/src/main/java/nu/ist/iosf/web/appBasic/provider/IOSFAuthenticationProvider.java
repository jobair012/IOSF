package nu.ist.iosf.web.appBasic.provider;

import nu.ist.iosf.commons.enums.ResponseCode;
import nu.ist.iosf.commons.models.appBasic.Response;
import nu.ist.iosf.commons.models.response.Token;
import nu.ist.iosf.web.appBasic.i18n.MessageKey;
import nu.ist.iosf.web.appBasic.i18n.ServiceMessage;
import nu.ist.iosf.web.models.UserDetails;
import nu.ist.iosf.web.service.TokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class IOSFAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired private TokenService tokenService;
	@Autowired private ServiceMessage serviceMessage;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		if(!StringUtils.isEmpty(authentication.getName()) && !StringUtils.isEmpty(authentication.getCredentials().toString())) {
			Response<Token> tokenResponse = tokenService.getToken(authentication.getName(), authentication.getCredentials().toString());
			if (ResponseCode.OPERATION_SUCCESSFUL.getCode() == tokenResponse.getResponseCode() && tokenResponse.getItems() != null) {
				Token token = tokenResponse.getItems();
				UserDetails userDetails = new UserDetails();
            	BeanUtils.copyProperties(tokenResponse.getItems(), userDetails);

				List<GrantedAuthority> AUTHORITIES = this.getAuthorities(userDetails.getUserRoles());

                return new UsernamePasswordAuthenticationToken(userDetails, token, AUTHORITIES);

			}else {
				throw new IOSFUserStatusException(tokenResponse.getResponseMessage());
			}
		}else {
			throw new IOSFUserStatusException(serviceMessage.getMessage(MessageKey.USERNAME_PASSWORD_REQUIRED));
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	private List<GrantedAuthority> getAuthorities(Set<String> userRoles){
		List<GrantedAuthority> AUTHORITIES = new ArrayList<>();
		for(String role : userRoles){
			AUTHORITIES.add(new SimpleGrantedAuthority(role));
		}
		return AUTHORITIES;
	}
}
