package com.example.BookMyShow.Service;

import com.example.BookMyShow.RequestDtos.MovieRequestDto;
import com.example.BookMyShow.Models.MovieEntity;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.ResponseDto.MovieResponse;
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

    public MovieResponse getMovieByName(String name){
        MovieEntity movieEntity = movieRepository.findByMovieName(name);

        MovieResponse movieResponse = MovieResponse.builder().id(movieEntity.getId()).movieName(movieEntity.getMovieName())
                .duration(movieEntity.getDuration()).releaseDate(movieEntity.getReleaseDate()).build();
        return movieResponse;
    }

}
