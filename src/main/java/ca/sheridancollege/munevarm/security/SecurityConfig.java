package ca.sheridancollege.munevarm.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		http.authorizeRequests()
			.antMatchers("/", "/js/**", "/css/**", "/images/**", "/**").permitAll()
			.antMatchers("/h2-console/**").permitAll();
//			.anyRequest().authenticated().and().formLogin().permitAll();
	
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
//			.withUser("test").password("12").roles("USER")
//				.and()
//			.withUser("guest@guest.com").password("password").roles("GUEST");
//	}


}
