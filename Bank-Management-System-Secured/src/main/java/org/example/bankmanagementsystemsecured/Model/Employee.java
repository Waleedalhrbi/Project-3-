package org.example.bankmanagementsystemsecured.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Employee {

    @Id
    private Integer id;


    @Column(columnDefinition = "varchar(20) not null")
    private String position;

    @Column(columnDefinition = "INT NOT NULL")
    private Integer salary;


    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    @JsonIgnore
    MyUser userEmployee;

}
