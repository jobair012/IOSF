package nu.ist.iosf.web.appBasic.provider;

import nu.ist.iosf.web.models.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@Component
public class IOSFAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		UserDetails authUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		/*Set some session variables*/
        session.setAttribute("uname", authUser.getUsername());  
        session.setAttribute("authorities", authentication.getAuthorities());
        session.setAttribute("token", authUser.getToken());
        
        /*Set target URL to redirect*/
		String targetUrl = determineTargetUrl(authentication); 
        redirectStrategy.sendRedirect(request, response, targetUrl);
		
	}
	
	protected String determineTargetUrl(Authentication authentication) {
        Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (authorities.contains("SUPERUSER") || authorities.contains("USER")) {
        	return "/dashboard";
        } else if (authorities.contains("ROLE_USER")) {
        	// TODO: need to modify
        	return "/this-is-test";
        } else {
            throw new IllegalStateException();
        }
    }

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
	
	

}
