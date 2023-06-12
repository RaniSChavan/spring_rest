package com.phrc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.phrc.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecation")
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
public UserDetailsService userDetailsService() {
	return new CustomUserDetailsService();
}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider; 
		}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(auth);
		
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		/* super.configure(http); */
		 http.authorizeRequests()
			 .antMatchers("/getAllEmployees").hasAnyAuthority("ADMIN","CREATOR","EDITOR") 
             .antMatchers("/saveEmp").hasAnyAuthority("ADMIN", "CREATOR")
             .antMatchers("/update").hasAnyAuthority("ADMIN", "EDITOR")
             .antMatchers("/delete").hasAnyAuthority("ADMIN")
             .anyRequest().authenticated()
             .and()
             .formLogin().permitAll()
             .and()
             .logout().permitAll()
             .and()
             .exceptionHandling().accessDeniedPage("/403");
		    http.csrf().disable();
		    http.headers().frameOptions().disable();
	}
}
