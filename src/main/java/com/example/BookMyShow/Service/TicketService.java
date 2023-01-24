package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dtos.BookTicketRequestDto;
import com.example.BookMyShow.Models.ShowEntity;
import com.example.BookMyShow.Models.ShowSeatEntity;
import com.example.BookMyShow.Models.TicketEntity;
import com.example.BookMyShow.Models.UserEntity;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.TicketRepository;
import com.example.BookMyShow.Repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShowRepository showRepository;

    public String bookTicket(BookTicketRequestDto bookTicketRequestDto) throws Exception{
        List<String> requestedSeats = bookTicketRequestDto.getRequestSeats();

        ShowEntity showEntity = showRepository.findById(bookTicketRequestDto.getShowId()).get();

        UserEntity userEntity = userRepository.findById(bookTicketRequestDto.getUserId()).get();

        //Get available seats from showEntity
        List<ShowSeatEntity> showSeats = showEntity.getListOfSeats();

        //Validating for allotment of seats
        List<ShowSeatEntity> bookedSeats = new ArrayList<>();

        for(ShowSeatEntity showSeat: showSeats){
            String seatNo = showSeat.getSeatNo();

            if(showSeat.isBooked() == false && requestedSeats.contains(seatNo)){
                bookedSeats.add(showSeat);
            }
        }

        // if all seats are not allocated
        if(bookedSeats.size() != requestedSeats.size()){
            throw new Exception("Requested seats are not available");
        }

        // After successfully allocating the seats
        // making the ticket
        TicketEntity ticketEntity = new TicketEntity();
        double totalAmount = 0;
        double multiplier = showEntity.getMultiplier();

        String allocatedSeats = "";

        int rate = 0;

        //Updating ticket details
        for(ShowSeatEntity bookedSeat : bookedSeats){
            bookedSeat.setBooked(true);
            bookedSeat.setBookedAt(new Date());
            bookedSeat.setTicket(ticketEntity);
            bookedSeat.setShow(showEntity);

            String seatNo = bookedSeat.getSeatNo();

            allocatedSeats += seatNo + ",";

            if(seatNo.charAt(0) == '1'){
                rate = 100;
            }
            else {
                rate = 200;
            }

            totalAmount += multiplier * rate;

        }

        ticketEntity.setBookedAt(new Date());
        ticketEntity.setAmount((int)totalAmount);
        ticketEntity.setShow(showEntity);
        ticketEntity.setUser(userEntity);
        ticketEntity.setBookedSeats(bookedSeats);
        ticketEntity.setAllocatedSeats(allocatedSeats);

        ticketRepository.save(ticketEntity);

        return "Ticket Successfully created";

    }
}
