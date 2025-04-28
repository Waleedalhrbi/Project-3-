package org.example.bankmanagementsystemsecured.Repository;

import org.example.bankmanagementsystemsecured.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Integer> {

    MyUser findMyUserById(Integer id);

    MyUser findMyUserByUsername(String username);
}
