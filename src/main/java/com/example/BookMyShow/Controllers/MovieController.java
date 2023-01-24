package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.Dtos.MovieRequestDto;
import com.example.BookMyShow.Models.MovieEntity;
import com.example.BookMyShow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/addMovie")
    public String addMovie(@RequestBody()MovieRequestDto movieRequestDto){
        return movieService.addMovie(movieRequestDto);
    }

    //Get movie By name

    @GetMapping("/getMovie")
    public MovieEntity getMovieByName(@RequestParam("name") String name){
        return movieService.getMovieByName(name);
    }
}
