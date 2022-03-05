package pl.umcs.bookstore.app.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.umcs.bookstore.app.role.RoleType;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/register", "/login").not().authenticated()
                .antMatchers("/admin-panel*").hasRole(RoleType.ROLE_ADMIN.getNameWithoutPrefix())
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .failureForwardUrl("/login")
                .successHandler(new CustomAuthenticationSuccessHandler())
            .and()
                .exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
