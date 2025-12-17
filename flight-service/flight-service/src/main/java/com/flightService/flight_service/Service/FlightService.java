package com.flightService.flight_service.Service;

import com.flightService.flight_service.Dto.SeatQuoteRequest;
import com.flightService.flight_service.Dto.SeatQuoteResponse;
import com.flightService.flight_service.Entities.Flight;
import com.flightService.flight_service.Exception.FlightNotFoundException;
import com.flightService.flight_service.Exception.InsufficientSeatsException;
import com.flightService.flight_service.Repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FlightService {

    private final FlightRepository flightRepo;

    private static final Logger log = LoggerFactory.getLogger(FlightService.class);

    public FlightService(FlightRepository flightRepository){
        this.flightRepo = flightRepository;
    }

    public SeatQuoteResponse quoteSeats(String flightId, SeatQuoteRequest request) {

        Flight flight = flightRepo.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found"));

        int availableSeats = flight.getTotalSeats() - flight.getBookedSeats();

        if (availableSeats < request.getSeats()) {
            throw new InsufficientSeatsException("Insufficient seats available");
        }

        double pricePerSeat = calculateDynamicPrice(flight);

        String quoteId = UUID.randomUUID().toString();

//        flight.setBookedSeats(flight.getBookedSeats() + request.getSeats());
//        flightRepo.save(flight);

        return new SeatQuoteResponse(quoteId, pricePerSeat);
    }

    private double calculateDynamicPrice(Flight flight) {

        double occupancy =
                (double) flight.getBookedSeats() / flight.getTotalSeats();

        if (occupancy < 0.4) {
            return flight.getBasePrice();
        } else if (occupancy < 0.7) {
            return flight.getBasePrice() * 1.2;
        } else {
            return flight.getBasePrice() * 1.5;
        }
    }

}
