package com.example.BookMyShow.Repository;

import com.example.BookMyShow.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByName(String name);
}
