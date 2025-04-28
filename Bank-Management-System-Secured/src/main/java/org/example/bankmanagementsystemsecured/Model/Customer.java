package org.example.bankmanagementsystemsecured.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Customer {

    @Id
    private Integer id;

    @Column(columnDefinition = "varchar(10) not null unique")
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    @JsonIgnore
    MyUser userCustomer;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    Set<Account> accounts;
}
