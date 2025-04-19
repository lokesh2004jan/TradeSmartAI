import com.example.TradeSmart.AI.services.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(private val userService: UserService) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
                    .anyRequest().authenticated()
            }
            .formLogin { form ->
                form
                    .loginProcessingUrl("/api/auth/login")
                    .permitAll()
                    .successHandler { _, response, _ ->
                        response.status = 200
                        response.writer.write("Login Successful!")
                    }
                    .failureHandler { _, response, _ ->
                        response.status = 401
                        response.writer.write("Invalid Credentials")
                    }
            }
            .logout { logout ->
                logout.logoutUrl("/api/auth/logout").logoutSuccessHandler { _, response, _ ->
                    response.writer.write("Logged out successfully!")
                }
            }
            .csrf { it.disable() }

        return http.build()
    }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager {
        return config.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
