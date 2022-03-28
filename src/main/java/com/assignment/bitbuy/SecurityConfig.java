package com.assignment.bitbuy;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration

	public  class SecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http.csrf().disable().authorizeRequests()
		     .antMatchers("/api/create").permitAll() //Excluding api/create from basic auth
		     .antMatchers("/api/login").permitAll() //Excluding api/login from basic auth
		     .antMatchers("/h2-console/**").permitAll() //Excluding api/login from basic auth
		   //securing the /api/users/** end point with Basic Auth
		     .antMatchers("/api/users/**").authenticated().and().httpBasic(); 
			// below should not be disabled, disabling for h2 console
			//http.headers().frameOptions().disable();

		}
}