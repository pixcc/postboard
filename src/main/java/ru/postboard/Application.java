package ru.postboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.postboard.interceptor.SecurityInterceptor;

@SpringBootApplication
public class Application implements WebMvcConfigurer {
    private SecurityInterceptor securityInterceptor;

    @Autowired
    public void setSecurityInterceptor(SecurityInterceptor securityInterceptor) {
        this.securityInterceptor = securityInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
