package org.example.bankmanagementsystemsecured.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {

    private Integer id;

    @NotEmpty(message = "Please enter position")
    private String position;

    @NotNull(message = "Please enter salary")
    @Positive(message = "Salary must be positive")
    @Min(value = 3000,message = "Salary must be > than 3000")
    private Integer salary;

    @NotEmpty(message = "Please enter user name")
    @Size(min = 4, max = 10, message = "The length of user name must be between 4 and 10")
    private String username;

    @NotEmpty(message = "Please enter a password")
    @Size(min = 6, message = "The length of password must be grater than 5")
    private String password;

    @NotEmpty(message = "Please enter a name")
    @Size(min = 2, max = 20, message = "The length of user name must be between 2 and 20")
    private String name;


    @NotEmpty(message = "Please enter an email")
    @Email
    private String email;

}
