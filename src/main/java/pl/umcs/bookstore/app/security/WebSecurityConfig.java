package pl.umcs.bookstore.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import pl.umcs.bookstore.app.role.RoleType;

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/register", "/login").permitAll()
                .antMatchers("/user*").hasRole(RoleType.ROLE_USER.getNameWithoutPrefix())
                .antMatchers("/admin*").hasRole(RoleType.ROLE_ADMIN.getNameWithoutPrefix())
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .failureForwardUrl("/login")
                .successHandler(new CustomAuthenticationSuccessHandler())
                .usernameParameter("login")
            .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler());
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
}
