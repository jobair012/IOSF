package nu.ist.iosf.web.appBasic.rester;

import nu.ist.iosf.web.models.UserDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

public class IOSFRestInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

		HttpHeaders headers = request.getHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(authentication!=null && authentication.getPrincipal() instanceof  UserDetails) {
			UserDetails userDetails = ((UserDetails) authentication.getPrincipal());
			headers.add("Authorization", "Bearer "+userDetails.getToken());
		}

		return execution.execute(request, body);
	}
}
