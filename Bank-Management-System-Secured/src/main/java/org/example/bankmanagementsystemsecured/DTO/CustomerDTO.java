package org.example.bankmanagementsystemsecured.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {

    private Integer id;

    @NotEmpty(message = "Please enter a phone number")
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with '05' and have 10 digits")
    private String phoneNumber;


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
