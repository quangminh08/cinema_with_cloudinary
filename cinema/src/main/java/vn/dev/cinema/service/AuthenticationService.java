package vn.dev.cinema.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import vn.dev.cinema.auth.AuthenticationRequest;
import vn.dev.cinema.auth.AuthenticationResponse;
import vn.dev.cinema.entity.Role;
import vn.dev.cinema.entity.User;

@Service
public class AuthenticationService {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private RoleCustomService roleCustonService;
	
	public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), 
														authenticationRequest.getPassword()));

			User user =  (User) userDetailsServiceImpl.loadUserByUsername(authenticationRequest.getUsername());
			List<Role> roles = null;
			
			if(user != null) {
				roles = roleCustonService.getRoles(user);
			}
			
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
			Set<Role> set = new HashSet<Role>();
			roles.stream().forEach(r->set.add(new Role(r.getName())));
			user.setRoles(set);
			set.stream().forEach(u->authorities.add(new SimpleGrantedAuthority(u.getName())));
			var jwtToken = jwtService.generateToken(user, authorities);
			var jwtRefreshToken = jwtService.generateRefreshToken(user, authorities);
			return new AuthenticationResponse(jwtToken, jwtRefreshToken);
		
	}
}
