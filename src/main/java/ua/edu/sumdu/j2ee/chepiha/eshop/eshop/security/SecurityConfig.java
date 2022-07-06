package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Autowired
    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery(
                    "select login, password, 'true' as enabled from lab3_chepihavv_user " +
                            "where login=?")
            .authoritiesByUsernameQuery(
                    "select login, authority from lab3_chepihavv_user " +
                            "where login=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
            .authorizeRequests()

            .antMatchers("/signin", "/signup")
                .permitAll()
            .antMatchers("/users")
                .hasRole("ADMIN")
            .antMatchers("/orders","/products","/clients","/brands","/locations","/storages","/")
                .hasAnyRole("USER", "ADMIN")

            .and()
                .formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/authenticateTheUser")
            .and()
                .logout()
                .logoutSuccessUrl("/signin")
            .permitAll();
    }
}
