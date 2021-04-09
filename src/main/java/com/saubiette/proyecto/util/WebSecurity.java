package com.saubiette.proyecto.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailService myUserDetailService;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/areas").hasRole("Admin").antMatchers("/areas/alta")
				.hasRole("Admin").antMatchers("/establecimientos/alta").hasRole("Admin").antMatchers("/usuarios/alta")
				.hasRole("Admin").antMatchers("/usuarios").hasRole("Admin").antMatchers("/roles").hasRole("Admin")
				.antMatchers("/roles/alta").hasRole("Admin").antMatchers("/establecimientos").permitAll()
				.antMatchers("/personal").permitAll().antMatchers("/").permitAll().and().formLogin();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
