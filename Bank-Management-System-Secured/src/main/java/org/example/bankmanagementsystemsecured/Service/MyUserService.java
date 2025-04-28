package org.example.bankmanagementsystemsecured.Service;

import lombok.RequiredArgsConstructor;
import org.example.bankmanagementsystemsecured.Api.ApiException;
import org.example.bankmanagementsystemsecured.Model.MyUser;
import org.example.bankmanagementsystemsecured.Repository.MyUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserService {

    private final MyUserRepository myUserRepository;

    public List<MyUser> getAllUsers() {
        return myUserRepository.findAll();
    }

    public void addUser(MyUser myUser) {

        String hashPassword = new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hashPassword);
       myUserRepository.save(myUser);
    }

    public void updateUser(String username, MyUser myUser) {
        MyUser oldUser = myUserRepository.findMyUserByUsername(username);
        if (oldUser == null) {
            throw new ApiException("User not found");
        }
        oldUser.setPassword(new BCryptPasswordEncoder().encode(myUser.getPassword()));
        oldUser.setEmail(myUser.getEmail());
        oldUser.setRole(myUser.getRole());
        oldUser.setName(myUser.getName());
        oldUser.setUsername(myUser.getUsername());
        myUserRepository.save(oldUser);
    }

    public void deleteUser(String username) {
        MyUser oldUser = myUserRepository.findMyUserByUsername(username);

        if (oldUser == null) {
            throw new ApiException("User not found");
        }
        myUserRepository.delete(oldUser);
    }

}
