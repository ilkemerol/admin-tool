package com.spring.admintool.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("SELECT USERNAME, PASSWORD, ENABLED FROM admintool_users WHERE USERNAME = ?")
		.authoritiesByUsernameQuery("SELECT USERNAME, AUTHORITY FROM admintool_authorities WHERE USERNAME = ?")
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated().and()
		.formLogin().loginPage("/public/login").defaultSuccessUrl("/admin/chooseOperation").passwordParameter("password")
		.usernameParameter("username").permitAll();
	}
}