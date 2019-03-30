package nu.ist.iosf.commons.models.request;

import nu.ist.iosf.commons.models.appBasic.IOSFRestClient;
import org.hibernate.validator.constraints.NotBlank;

public class TokenRequest extends IOSFRestClient {

	@NotBlank
	private String username;
	
	@NotBlank
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
