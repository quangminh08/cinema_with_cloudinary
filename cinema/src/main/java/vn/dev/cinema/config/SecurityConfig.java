package vn.dev.cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests()
		
//		http.csrf().disable().authorizeRequests()
//					.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//					.antMatchers("/api/v1/auth", "/**").permitAll()
					.requestMatchers("/api/v1/auth", "/**").permitAll()
//					.requestMatchers(new AntPathRequestMatcher("/api/v1/demo/**")).hasAnyAuthority("Staff")
//					.requestMatchers(new AntPathRequestMatcher("/api/v1/medicalrecords/**"))
//					.requestMatchers(new AntPathRequestMatcher("/api/v1/medicalrecords/**"))
//						.hasAnyAuthority("Staff", "Admin")
//					.requestMatchers(new AntPathRequestMatcher("/api/v1/schedule/**"))
//						.hasAnyAuthority("Staff", "Admin")
//					.requestMatchers(new AntPathRequestMatcher("/api/v1/messages/**"))
//						.authenticated()
//					.anyRequest().authenticated()
//					.antMatchers("/api/v1/medicalrecords/**").hasAnyAuthority("User","Staff", "Admin")
					
//					.antMatchers("/api/v1/users/**").hasAnyAuthority("Staff", "Admin")
//					.antMatchers("/api/v1/users", "/**").hasAnyAuthority("Staff", "Admin")
					
					.and()
					
					.csrf().disable()
					.authorizeRequests().anyRequest()
					.authenticated()
					.and()
					.authenticationProvider(authenticationProvider)
					.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
