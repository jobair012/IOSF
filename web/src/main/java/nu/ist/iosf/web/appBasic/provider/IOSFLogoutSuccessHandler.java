package nu.ist.iosf.web.appBasic.provider;

import nu.ist.iosf.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class IOSFLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler{

	@Autowired private TokenService tokenService;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String redirectUrl = "/login?logout";
		if (authentication != null) {
			tokenService.deleteToken();
		}
		request.getSession().invalidate();
		response.sendRedirect(redirectUrl);
		super.onLogoutSuccess(request, response, authentication);
	}
}
