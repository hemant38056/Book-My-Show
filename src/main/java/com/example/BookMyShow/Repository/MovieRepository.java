package com.example.BookMyShow.Repository;

import com.example.BookMyShow.Models.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
    MovieEntity findByMovieName(String movieName);
}
