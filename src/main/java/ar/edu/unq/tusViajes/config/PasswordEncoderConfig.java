package ar.edu.unq.tusViajes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
 
// Bean minimo para poder hashear passwords sin traer spring-boot-starter-security
// completo todavia. Cuando armemos el login real con JWT, esto probablemente
// se mueva adentro de una SecurityConfig mas grande junto con el filtro de auth.
@Configuration
public class PasswordEncoderConfig {
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
 