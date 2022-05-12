package com.example.restfulwebservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//      h2-console 뒤에 어떤 메소드로 전달되어도 통과될수있게
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
//      csrf에 관련된거 사용안한다는
        http.csrf().disable();
//      헤더의 프레임값을 사용 안한다.
        http.headers().frameOptions().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
        throws Exception {
        auth.inMemoryAuthentication()
                .withUser("goyoun")
//                인코딩없이 사용할수 있는 {noop}
                .password("{noop}test1234")
                .roles("USERS");
    }
}
