package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.RequestDtos.UserRequestDto;
import com.example.BookMyShow.Models.UserEntity;
import com.example.BookMyShow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody()UserRequestDto userRequestDto){
        return userService.createUser(userRequestDto);
    }

    // Find User by name
    @GetMapping("/findByName")
    public UserEntity findUserByName(@RequestParam("name") String name){
        return userService.findByName(name);
    }

    // Find All Users
    @GetMapping("/allUser")
    public List<UserEntity> findAllUser(){
        return userService.findAllUsers();
    }
}
