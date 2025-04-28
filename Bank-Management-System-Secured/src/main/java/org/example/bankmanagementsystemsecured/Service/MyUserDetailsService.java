package org.example.bankmanagementsystemsecured.Service;

import lombok.RequiredArgsConstructor;
import org.example.bankmanagementsystemsecured.Api.ApiException;
import org.example.bankmanagementsystemsecured.Model.MyUser;
import org.example.bankmanagementsystemsecured.Repository.MyUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final MyUserRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUser myUser = myUserRepository.findMyUserByUsername(username);
        if (myUser == null){
            throw new ApiException("Wrong user name or password");
        }

        return myUser;
    }
}
