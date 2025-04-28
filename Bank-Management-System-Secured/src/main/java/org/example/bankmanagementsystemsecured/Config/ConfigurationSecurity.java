package org.example.bankmanagementsystemsecured.Config;


import lombok.RequiredArgsConstructor;
import org.example.bankmanagementsystemsecured.Service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigurationSecurity {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return authenticationProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/add-user", "/api/v1/auth/update").permitAll()
                .requestMatchers("/api/v1/auth/get-all-accounts", "/api/v1/auth/get-all-users").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/auth/delete/**").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/employee/get-all-employees").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/employee/add-employee").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/employee/update-employee").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/employee/delete/**").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/customer/get-all-customers").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/customer/add-customer").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/customer/update").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/customer/delete").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/get-all-accounts").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/account/get-my-accounts").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/add").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/update/**").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/delete/**").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/view-account-details/**").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/active-bank-account/**").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/deposit-account/**").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/withdraw-account/**").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/transfer-funds-between-accounts/**").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/block-account/**").hasAuthority("ADMIN")
                .and()
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();

        return httpSecurity.build();
    }

}
