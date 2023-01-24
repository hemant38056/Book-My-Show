package com.example.BookMyShow.Repository;

import com.example.BookMyShow.Models.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<ShowEntity, Integer> {

}
