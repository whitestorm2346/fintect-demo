package org.tku.web.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.formLogin(httpSecurityFormLoginConfigurer -> {
            httpSecurityFormLoginConfigurer.loginPage("/login-test")
                    .defaultSuccessUrl("/index")
                    .usernameParameter("account")
                    .passwordParameter("password").failureHandler((request, response, exception)->{
                        log.error("密碼錯誤");
                        //log.debug("account = {}, password = {}", );
                        response.sendRedirect("/login?error=failed");
                    });
        });

        http.authorizeHttpRequests(registry -> {
            // 定義哪些URL需要被保護、哪些不需要被保護
            registry.requestMatchers("/note-share/privates/**").authenticated()
                    .anyRequest().permitAll();

        });

        http.csrf(httpSecurityCsrfConfigurer-> {
            httpSecurityCsrfConfigurer.ignoringRequestMatchers("/api/v1/**", "/callback");
        });

        http.exceptionHandling(configurer -> {
            configurer.authenticationEntryPoint((request, response, authException) -> {
                log.error("未登入 : "+ authException.getMessage());
                response.sendRedirect("/login?error=unauth");
            });
        });
//
//        http.headers(configurer -> {
//            configurer.cacheControl(HeadersConfigurer.CacheControlConfig::disable);
//            configurer.contentSecurityPolicy(httpSecurityHeadersConfigurerContentSecurityPolicyConfig -> {
//                httpSecurityHeadersConfigurerContentSecurityPolicyConfig.policyDirectives("default-src 'self';");
//            });
//            configurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::deny);
//        });
//
        http.logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                .logoutUrl("/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    log.info("登出成功");
                    response.sendRedirect("/login?logout");
                })
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}