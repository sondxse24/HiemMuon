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

                        // Customer: chỉ đọc
                        .requestMatchers(HttpMethod.GET,
                                "/api/doctor/active",
                                "/api/doctor/{id}",
                                "/api/doctor/description"
                        ).hasRole("CUSTOMER")

                        .requestMatchers(HttpMethod.PUT, "/api/doctor/**").hasRole("DOCTOR")

                        .requestMatchers(HttpMethod.GET, "/api/doctor").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PATCH, "/api/doctor/status/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/doctor").hasRole("MANAGER")

                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

}
