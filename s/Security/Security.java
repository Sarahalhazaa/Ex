package com.example.s.Security;

import com.example.s.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {

    private final MyUserDetailsService myUserDetailsService;
@Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean

    public SecurityFilterChain springSecurityFilterChainn(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/vi/auth/register").permitAll()
                .requestMatchers("/api/vi/auth/login").permitAll()
                .requestMatchers("/api/vi/auth/Update").hasAuthority("CUSTOMER")
                .requestMatchers("/api/vi/auth/logOut").authenticated()
                .requestMatchers("/api/vi/auth/get").hasAuthority("ADMIN")
                .requestMatchers("/api/vi/auth/delete").hasAuthority("ADMIN")

                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/vi/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }


}
