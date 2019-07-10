/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mnzit.springsecurity.configuration;

import com.mnzit.springsecurity.configuration.service.AuthService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Mnzit
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthService authService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .antMatchers("admin/**").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/login")
                .permitAll()
                .and()
                .logout().invalidateHttpSession(true).clearAuthentication(true);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring().antMatchers("/webjars/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        String userSQL = "SELECT username,password,active from tbl_users WHERE username=?";
//        String roleSQL = "SELECT u.username, r.role_name FROM tbl_users u"
//                            + " INNER JOIN tbl_user_roles ur"
//                            + " ON u.id = ur.user_id"
//                            + " INNER JOIN tbl_roles r"
//                            + " ON r.id = ur.role_id"
//                            + " WHERE u.username = ?";
//        auth
//                .jdbcAuthentication()
//                .usersByUsernameQuery(userSQL)
//                .authoritiesByUsernameQuery(roleSQL)
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder());

//                .inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .withUser("admin")
//                .roles("USER")
//                .password(passwordEncoder().encode("admin1234"));
        try {
            auth.authenticationProvider(getAuthProvider());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Bean
    public DaoAuthenticationProvider getAuthProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(authService);
        return provider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
