package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dtos.MovieRequestDto;
import com.example.BookMyShow.Models.MovieEntity;
import com.example.BookMyShow.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieRequestDto movieRequestDto){
        MovieEntity movieEntity = MovieEntity.builder().movieName(movieRequestDto.getName())
                .duration(movieRequestDto.getDuration()).releaseDate(movieRequestDto.getReleaseDate()).build();

        movieRepository.save(movieEntity);
        return "Movie added Successfully";
    }

    public MovieEntity getMovieByName(String name){
        return movieRepository.findByMovieName(name);
    }

}
