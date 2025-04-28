package org.example.bankmanagementsystemsecured.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class MyUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "Please enter a user name")
    @Size(min = 4, max = 10, message = "The length of user name must be between 4 and 10")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;


    @NotEmpty(message = "Please enter a password")
    @Size(min = 6, message = "The length of password must be grater than 5")
    @Column(columnDefinition = "varchar(13) not null")
    private String password;


    @NotEmpty(message = "Please enter a name")
    @Size(min = 2, max = 20, message = "The length of user name must be between 2 and 20")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;



    @NotEmpty(message = "Please enter an email")
    @Email
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;

    @Pattern(regexp = "ADMIN|CUSTOMER|EMPLOYEE")
    @Column(columnDefinition = "varchar(13) not null")
    private String role;

    public MyUser(String username, String password, String name, String role, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
        this.email = email;
    }



    @OneToOne(cascade = CascadeType.ALL,mappedBy = "userEmployee")
    private Employee userEmployee;


    @OneToOne(cascade = CascadeType.ALL,mappedBy = "userCustomer")
    private Customer userCustomer;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
