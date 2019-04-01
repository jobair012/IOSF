package nu.ist.iosf.web.config;

import nu.ist.iosf.web.appBasic.provider.IOSFAuthenticationProvider;
import nu.ist.iosf.web.appBasic.provider.IOSFAuthenticationSuccessHandler;
import nu.ist.iosf.web.appBasic.provider.IOSFLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan("nu.ist.iosf.web")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired private IOSFAuthenticationProvider authenticationProvider;
	@Autowired private IOSFAuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired private IOSFLogoutSuccessHandler logoutSuccessHandler;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        	.authorizeRequests()
	            .antMatchers("/**").permitAll()
	            .antMatchers("/user**").hasAnyRole("USER", "SUPERUSER")
				.antMatchers("/dashboard**").hasAnyRole("USER", "SUPERUSER")
	            .anyRequest().authenticated()
	            .and()
	        .formLogin()
            	.loginPage("/")
            	.loginProcessingUrl("/login")
            	.successHandler(authenticationSuccessHandler)
            	.and()
            .logout()
            	.logoutUrl("/logout")
            	.logoutSuccessHandler(logoutSuccessHandler);
	}
}
