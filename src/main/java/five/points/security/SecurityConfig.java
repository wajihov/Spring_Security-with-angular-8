package five.points.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // pr activer la securité web
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// le fichier des utilisateur s et les roles
//		auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN", "USER").and()
//				.withUser("user").password("{noop}1234").roles("USER");
		/*
		 * auth.jdbcAuthentication().usersByUsernameQuery("")
		 * .authoritiesByUsernameQuery("")
		 */
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// ds cela on definir les droits d'acces
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// http.formLogin();// pr les session
		http.authorizeRequests().antMatchers("/login/**", "/register/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/tasks/**").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/AllRegister/**").hasAuthority("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

//	@Bean
//	public UserDetailsService userDetailsServiceBean() {
//
//		@SuppressWarnings("deprecation")
//		User.UserBuilder users = User.withDefaultPasswordEncoder();
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		manager.createUser(users.username("user").password("password").roles("USER").build());
//		manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
//		return manager;
//
//	}
}
