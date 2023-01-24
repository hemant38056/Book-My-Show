package com.example.BookMyShow.Dtos;

import lombok.Data;

import java.util.Date;

@Data
public class MovieRequestDto {
    private String name;
    private Date releaseDate;
    private int duration;
}
