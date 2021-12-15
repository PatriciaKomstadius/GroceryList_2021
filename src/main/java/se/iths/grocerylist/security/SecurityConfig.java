package se.iths.grocerylist.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final GroceryUserDetailsService userDetailsService;
    public SecurityConfig(GroceryUserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/home", "/users/signup").permitAll()
                .antMatchers("/grocerylists", "/mypage").hasAnyRole("CUSTOMER", "EMPLOYEE", "ADMIN")
                .antMatchers(HttpMethod.GET, "/products").hasAnyRole("CUSTOMER", "EMPLOYEE", "ADMIN")
                .antMatchers("/products", "/users").hasAnyRole("EMPLOYEE", "ADMIN")
                .antMatchers("/admin", "/companies", "/companyinfo", "/departments", "/layouts", "/roles", "/users").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll();
    }

}
