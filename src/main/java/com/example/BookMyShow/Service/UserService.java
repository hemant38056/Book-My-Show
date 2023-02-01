package com.example.BookMyShow.Service;

import com.example.BookMyShow.RequestDtos.UserRequestDto;
import com.example.BookMyShow.Models.UserEntity;
import com.example.BookMyShow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String createUser(UserRequestDto userRequestDto){
        UserEntity userEntity = UserEntity.builder().name(userRequestDto.getName())
                .mobile(userRequestDto.getMobile()).build();

        try{
            userRepository.save(userEntity);
        }
        catch (Exception e){
            return "User cannot added";
        }

        return "User added successfully!";
    }

    public UserEntity findByName(String name){
        return userRepository.findByName(name);
    }

    public List<UserEntity> findAllUsers(){
        return userRepository.findAll();
    }
}
