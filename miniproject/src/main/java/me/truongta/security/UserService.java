package me.truongta.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import me.truongta.dao.UserRepository;
import me.truongta.entity.Users;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	PasswordEncoder pe;
	
	@Autowired
	UserRepository udao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = udao.findById(username).get();
		return User.withUsername(users.getUsername()).password(pe.encode(users.getPassword())).roles(users.getRole().getId()).build();
	}

}
