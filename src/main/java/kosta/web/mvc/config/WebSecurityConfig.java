package kosta.web.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kosta.web.mvc.member.service.MemberAuthenticationFailureHandler;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private MemberAuthenticationFailureHandler memberAuthenticationFailureHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/**").permitAll();
		
		http.formLogin()
		.loginPage("/member/loginForm")
		.usernameParameter("memberId")
		.passwordParameter("pwd")
		.loginProcessingUrl("/login")
		.defaultSuccessUrl("/loginSuccess")
		.failureHandler(memberAuthenticationFailureHandler);
	}
	
	@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }	
	
}
