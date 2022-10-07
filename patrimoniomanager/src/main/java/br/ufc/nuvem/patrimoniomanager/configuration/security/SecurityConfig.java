package br.ufc.nuvem.patrimoniomanager.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/alive/**", "/bem/{id}", "/validation/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/user")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/user/root")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/user/me", "user/bens")
                .hasAnyAuthority("USER", "ROOT")
                .antMatchers(HttpMethod.GET, "/user", "/user/{id}", "user/bens/{id}")
                .hasAnyAuthority("ROOT")
                .antMatchers(HttpMethod.DELETE, "/user")
                .hasAuthority("ROOT")
                .antMatchers(HttpMethod.GET, "/bem")
                .permitAll()
                .and()
                .httpBasic();
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

}
