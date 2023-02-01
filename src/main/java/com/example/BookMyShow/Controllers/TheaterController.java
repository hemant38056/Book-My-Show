package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.RequestDtos.TheaterRequestDto;
import com.example.BookMyShow.Models.TheaterEntity;
import com.example.BookMyShow.ResponseDto.TheaterResponse;
import com.example.BookMyShow.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {
    @Autowired
    TheaterService theaterService;

    @PostMapping("/addTheater")
    public String addTheater(@RequestBody()TheaterRequestDto theaterRequestDto){
        return theaterService.createTheater(theaterRequestDto);
    }

    //Get theater by theaterId
    @GetMapping("/theaterById")
    public TheaterResponse getTheaterById(@RequestParam("id") Integer theaterId){
        return theaterService.getTheaterById(theaterId);
    }

    //Get all theaters
    @GetMapping("/allTheaters")
    public List<TheaterResponse> getTheaters(){

        return theaterService.getAllTheaters();
    }


}
