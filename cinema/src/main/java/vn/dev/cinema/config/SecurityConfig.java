package vn.dev.cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	
	@SuppressWarnings("deprecation")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests()
		
//		http.csrf().disable().authorizeRequests()
					.requestMatchers(HttpMethod.OPTIONS).permitAll()
					.requestMatchers("/api/auth/login").permitAll()	
					.requestMatchers("/api/schedules/**").permitAll()
					.requestMatchers("/api/categories").permitAll()
					.requestMatchers("/api/cinemas").permitAll()
					.requestMatchers("/api/films").permitAll()
					.requestMatchers("/api/categories/**").hasAnyAuthority("ADMIN", "STAFF")
					.requestMatchers("api/ticket-types/**").hasAnyAuthority("ADMIN", "STAFF")
					.and()
					
					.csrf().disable()
					.authorizeRequests()
					.anyRequest()
					.authenticated()
					.and()
					.authenticationProvider(authenticationProvider)
					.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
