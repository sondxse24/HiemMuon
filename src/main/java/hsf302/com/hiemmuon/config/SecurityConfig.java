package hsf302.com.hiemmuon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/api/login/**").permitAll()


                        .requestMatchers(HttpMethod.GET,
                                "/api/doctors/id/**",
                                "/api/doctors/name/**",
                                "/api/doctors/active",
                                "/api/doctors/specification").permitAll()

                        .requestMatchers(HttpMethod.GET,
                                "api/appointment-services/doctors/{doctorId}/available-schedules",
                                "api/appointment-services/appointments/reexam",
                                "/api/cycles/meC",
                                "api/test-results/customer").hasRole("CUSTOMER")

                        .requestMatchers(HttpMethod.GET,
                                "/api/doctors/me",
                                "api/appointment-services/appointments/history",
                                "/api/cycles/meD").hasRole("DOCTOR")

                        .requestMatchers(HttpMethod.GET,
                                "/api/doctors/all",
                                "/api/treatment-services/all",
                                "api/appointment-services/appointments/overview").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.GET,
                                "/api/admin/customers").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,
                                "/api/cycle-steps/cycleId/**",
                                "/api/customer/info",
                                "api/appointment-services/appointments/detail",
                                "api/test-results/step/{stepId}",
                                "api/appointment-services/appointments/{appointmentId}/detail").hasAnyRole("CUSTOMER", "DOCTOR")

                        .requestMatchers(HttpMethod.GET,
                                "api/login/roles").hasAnyRole("CUSTOMER", "DOCTOR", "ADMIN", "MANAGER")

                        .requestMatchers(HttpMethod.GET,
                                "/api/treatment-services/**").permitAll()


                        .requestMatchers(HttpMethod.POST,
                                "/api/appointment-services/register/appointments").hasRole("CUSTOMER")

                        .requestMatchers(HttpMethod.POST,
                                "/api/appointment-services/appointments/reexam",
                                "/api/cycles/create",
                                "api/test-results/create").hasRole("DOCTOR")

                        .requestMatchers(HttpMethod.POST,
                                "/api/doctors",
                                "/api/treatment-services").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.POST,
                                "/api/register/customer").permitAll()


                        .requestMatchers(HttpMethod.PUT,
                                "/api/customer/update").hasRole("CUSTOMER")

                        .requestMatchers(HttpMethod.PUT,
                                "/api/doctors/me",
                                "api/test-results/update/{id}").hasRole("DOCTOR")

                        .requestMatchers(HttpMethod.PUT,
                                "/api/treatment-services/**").hasRole("MANAGER")


                        .requestMatchers(HttpMethod.PATCH,
                                "/api/appointment-services/appointments/cancel/{appointmentId}",
                                "/api/medicine/cycles/*/step/*/medicines/*/status").hasRole("CUSTOMER")

                        .requestMatchers(HttpMethod.PATCH,
                                "/api/cycles/cycleId/*/note",
                                "/api/cycle-steps/cycleId/*/stepOrder/*/status",
                                "/api/cycle-steps/cycleId/*/stepOrder/*/note",
                                "api/appointment-services/appointments/{appointmentId}/update-service").hasRole("DOCTOR")

                        .requestMatchers(HttpMethod.PATCH,
                                "/api/doctors/status/**",
                                "/api/treatment-services/status/**").hasRole("MANAGER")


                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/swagger-ui.html",
                                "/webjars/**",
                                "/v3/api-docs.yaml"
                        ).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated()

                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:8080", "http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}