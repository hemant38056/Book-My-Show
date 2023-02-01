package com.example.BookMyShow.Service;

import com.example.BookMyShow.RequestDtos.TheaterRequestDto;
import com.example.BookMyShow.Enums.SeatType;
import com.example.BookMyShow.Models.TheaterEntity;
import com.example.BookMyShow.Models.TheaterSeatEntity;
import com.example.BookMyShow.Repository.TheaterRepository;
import com.example.BookMyShow.Repository.TheaterSeatRepository;
import com.example.BookMyShow.ResponseDto.TheaterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    public String createTheater(TheaterRequestDto theaterRequestDto){
        TheaterEntity theaterEntity = TheaterEntity.builder().name(theaterRequestDto.getName())
                .city(theaterRequestDto.getCity()).address(theaterRequestDto.getAddress()).build();

        List<TheaterSeatEntity> theaterSeats = createTheaterSeats();

        theaterEntity.setTheaterSeatEntityList(theaterSeats);

        // Setting the theaterEntity for each theater seat
        for(TheaterSeatEntity theaterSeat : theaterSeats){
            theaterSeat.setTheater(theaterEntity);
        }

        theaterRepository.save(theaterEntity);

        return "Theater Added Successfully!";

    }

    public List<TheaterSeatEntity> createTheaterSeats(){
        List<TheaterSeatEntity> seats = new ArrayList<>();

//        TheaterSeatEntity theaterSeat1 = new TheaterSeatEntity("1A", SeatType.CLASSIC,100);
//        TheaterSeatEntity theaterSeat2 = new TheaterSeatEntity("1B", SeatType.CLASSIC,100);
//        TheaterSeatEntity theaterSeat3 = new TheaterSeatEntity("1C", SeatType.CLASSIC,100);
//        TheaterSeatEntity theaterSeat4 = new TheaterSeatEntity("1D", SeatType.CLASSIC,100);
//        TheaterSeatEntity theaterSeat5 = new TheaterSeatEntity("1E", SeatType.CLASSIC,100);

//         TheaterSeatEntity theaterSeat6 = new TheaterSeatEntity("2A", SeatType.PLATINUM,200);
//        TheaterSeatEntity theaterSeat7 = new TheaterSeatEntity("2B", SeatType.PLATINUM,200);
//        TheaterSeatEntity theaterSeat8 = new TheaterSeatEntity("2C", SeatType.PLATINUM,200);
//        TheaterSeatEntity theaterSeat9 = new TheaterSeatEntity("2D", SeatType.PLATINUM,200);
//        TheaterSeatEntity theaterSeat10 = new TheaterSeatEntity("2E", SeatType.PLATINUM,200);
//
//
//        seats.add(theaterSeat1);
//        seats.add(theaterSeat2);
//        seats.add(theaterSeat3);
//        seats.add(theaterSeat4);
//        seats.add(theaterSeat5);
//        seats.add(theaterSeat6);
//        seats.add(theaterSeat7);
//        seats.add(theaterSeat8);
//        seats.add(theaterSeat9);
//        seats.add(theaterSeat10);


        //Optimising way of adding seats

        for(int i = 0; i < 5; i++){
            char ch = (char) ('A' + i);

            String seatNo = "1" + ch;
            TheaterSeatEntity theaterSeatEntity = new TheaterSeatEntity();
            theaterSeatEntity.setSeatNo(seatNo);
            theaterSeatEntity.setSeatType(SeatType.CLASSIC);
            theaterSeatEntity.setRate(100);

            seats.add(theaterSeatEntity);
        }

        for(int i = 0; i < 5; i++){
            char ch = (char) ('A' + i);

            String seatNo = "2" + ch;
            TheaterSeatEntity theaterSeatEntity = new TheaterSeatEntity();
            theaterSeatEntity.setSeatNo(seatNo);
            theaterSeatEntity.setSeatType(SeatType.PLATINUM);
            theaterSeatEntity.setRate(100);

            seats.add(theaterSeatEntity);
        }
        theaterSeatRepository.saveAll(seats);
        return seats;
    }

    public TheaterResponse getTheaterById(Integer theaterId){
        TheaterEntity theaterEntity = theaterRepository.findById(theaterId).get();
        TheaterResponse theaterResponse = TheaterResponse.builder().id(theaterEntity.getId()).name(theaterEntity.getName())
                .city(theaterEntity.getCity()).address(theaterEntity.getAddress()).build();
        return theaterResponse;
    }

    public List<TheaterResponse> getAllTheaters(){
        List<TheaterEntity> listOfTheaters = theaterRepository.findAll();
        List<TheaterResponse> allTheaters = new ArrayList<>();

        for(TheaterEntity theaterEntity : listOfTheaters){
            TheaterResponse theaterResponse = TheaterResponse.builder().id(theaterEntity.getId()).name(theaterEntity.getName())
                    .city(theaterEntity.getCity()).address(theaterEntity.getAddress()).build();

            allTheaters.add(theaterResponse);
        }
        return allTheaters;
    }
}
