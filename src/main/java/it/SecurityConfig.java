package it;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        List<UserDetails> list = Arrays.asList(
                User.builder().username("director").password(passwordEncoder().encode("123")).roles("DIRECTOR").build(),
                User.builder().username("manager").password(passwordEncoder().encode("123")).roles("MANAGER").build(),
                User.builder().username("worker").password(passwordEncoder().encode("123")).roles("WORKER").build()
        );
        return new InMemoryUserDetailsManager(list);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/manager").hasRole("DIRECTOR")
                .antMatchers("/worker1").hasRole("MANAGER")
                .antMatchers("/worker").hasRole("WORKER")
                .antMatchers("/manager1").hasRole("MANAGER")
                .and().formLogin().permitAll()
                .and().build();
    }
}