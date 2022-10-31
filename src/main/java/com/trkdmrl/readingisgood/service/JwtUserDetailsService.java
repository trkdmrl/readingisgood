package com.trkdmrl.readingisgood.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	private final String USERNAME = "trkdmrl";
	private final String PASSWORD = "$2a$12$KtX4.fWrgROKpPt1grSymug87FfnDSCBAlnsVZq7H6hkxvb8a0Fp.";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (USERNAME.equals(username)) {
			return new User(USERNAME, PASSWORD,
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}