package com.example.product.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.product.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		    .requestMatchers("/").hasAnyAuthority("USER","ADMIN")
			.requestMatchers("/new").hasAnyAuthority("ADMIN","USER")
			.requestMatchers("/newp").hasAnyAuthority("ADMIN")
			.requestMatchers("/edit/**").hasAnyAuthority("ADMIN","USER")
			.requestMatchers("/update/**").hasAnyAuthority("ADMIN")
			.requestMatchers("/delete/**").hasAnyAuthority("ADMIN","USER")
			.requestMatchers("/remove/**").hasAuthority("ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin().permitAll()
            .and()
            .logout().permitAll()
            .and()
			
			/*.formLogin().permitAll().loginPage("/login")
			.usernameParameter("email")
			.and()
			.logout().permitAll()
			.logoutUrl("/logout");*/
			.exceptionHandling().accessDeniedPage("/403");
		return http.build();
	}
}
