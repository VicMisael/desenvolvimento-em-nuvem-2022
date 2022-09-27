package br.ufc.nuvem.patrimoniomanager.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    UserDetailsServiceImpl userDetails;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/alive/**", "/auth/**", "/bem/{id}", "/validation/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/user")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/user/root")
                .hasAuthority("ROOT")
                .antMatchers(HttpMethod.GET, "/user", "/user/{id}")
                .hasAnyAuthority("ROOT")
                .antMatchers(HttpMethod.DELETE, "/user")
                .hasAuthority("ROOT")
                .antMatchers("/bem/**")
                .hasAnyAuthority("USER", "ROOT")
                .and()
                .httpBasic();
        return http.build();
    }
}
