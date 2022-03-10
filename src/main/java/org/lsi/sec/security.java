package org.lsi.sec;

import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
@Configuration
@EnableWebSecurity
public class security extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
		.withUser("admin").password("{noop}1234").roles("ADMIN","USER");
		auth.inMemoryAuthentication()
		.withUser("user").password("{noop}1234").roles("USER");
	
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.formLogin().loginPage("/login");

		http.authorizeRequests().antMatchers("/deleteclient","/saveclient","/newemploye","/affectation","/","/saveafectation","/deleteEmploye","/employe","/newgroup","/savegroupe","/group","/employes","/deletegroup","/consulterc","/saveoperation","/consulter","/operations","/compte","/newcompte").hasRole("USER");
        http.authorizeRequests().antMatchers("/createcompte","/clients","/").hasRole("ADMIN");

	}
}