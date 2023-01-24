package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.Dtos.ShowRequestDto;
import com.example.BookMyShow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    ShowService showService;

    @PostMapping("/addShow")
    public String addShow(@RequestBody()ShowRequestDto showRequestDto){
        return showService.addShow(showRequestDto);
    }
}
