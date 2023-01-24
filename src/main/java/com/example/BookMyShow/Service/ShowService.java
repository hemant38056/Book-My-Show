package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dtos.ShowRequestDto;
import com.example.BookMyShow.Models.*;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.ShowSeatRepository;
import com.example.BookMyShow.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    ShowRepository showRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;

    @Autowired
    TheaterRepository theaterRepository;


    public String addShow(ShowRequestDto showRequestDto){
        ShowEntity showEntity = ShowEntity.builder().showDate(showRequestDto.getShowDate())
                .showTime(showRequestDto.getShowTime()).multiplier(showRequestDto.getMultiplier()).build();

        MovieEntity movieEntity = movieRepository.findByMovieName(showRequestDto.getMovieName());

        TheaterEntity theaterEntity = theaterRepository.findById(showRequestDto.getTheaterId()).get();

        showEntity.setTheater(theaterEntity);

        showEntity.setMovie(movieEntity);

        movieEntity.getListOfShows().add(showEntity);
        theaterEntity.getListOfShows().add(showEntity);

        List<ShowSeatEntity> showSeatEntities = createShowSeats(theaterEntity.getTheaterSeatEntityList());
        showEntity.setListOfSeats(showSeatEntities);

        for(ShowSeatEntity showSeat : showSeatEntities){
            showSeat.setShow(showEntity);
        }

        movieRepository.save(movieEntity);
        theaterRepository.save(theaterEntity);

        return "Show Added Successfully!";
    }

    public List<ShowSeatEntity> createShowSeats(List<TheaterSeatEntity> theaterSeatEntityList){
        List<ShowSeatEntity> showSeats = new ArrayList<>();
        for(TheaterSeatEntity theaterSeatEntity : theaterSeatEntityList){
            ShowSeatEntity showSeat = ShowSeatEntity.builder().seatNo(theaterSeatEntity.getSeatNo()).seatType(theaterSeatEntity.getSeatType()).build();
            showSeats.add(showSeat);
        }

        showSeatRepository.saveAll(showSeats);
        return showSeats;
    }
}
