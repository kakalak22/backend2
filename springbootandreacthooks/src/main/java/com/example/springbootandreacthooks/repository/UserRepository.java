package com.example.springbootandreacthooks.repository;

import com.example.springbootandreacthooks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //all crud method
}
