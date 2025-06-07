package hsf302.com.hiemmuon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/api/login/**").permitAll()

//                        .requestMatchers(HttpMethod.GET,
//                                "/api/doctors/active",
//                                "/api/doctors/description"
//                        ).hasRole("CUSTOMER")
//
//                        .requestMatchers(HttpMethod.PUT, "/api/doctors/**").hasRole("DOCTOR")

                                .requestMatchers(HttpMethod.GET,
                                        "/api/doctors/all",
                                        "/api/doctors/active",
                                        "/api/doctors/description").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.PATCH, "/api/doctors/status/**").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.POST, "/api/doctors").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.PUT, "/api/doctors/**").hasRole("MANAGER")

                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
