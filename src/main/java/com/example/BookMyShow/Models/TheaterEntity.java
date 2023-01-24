package com.example.BookMyShow.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "theater")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheaterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String city;
    private String address;


    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    List<TheaterSeatEntity> theaterSeatEntityList;


    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    List<ShowEntity> listOfShows;


}
