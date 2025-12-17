package com.flightService.flight_service.Controller;

import com.flightService.flight_service.Dto.SeatQuoteRequest;
import com.flightService.flight_service.Dto.SeatQuoteResponse;
import com.flightService.flight_service.Service.FlightService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService){
        this.flightService = flightService;
    }

    @PostMapping("/{flightId}/quote")
    public SeatQuoteResponse reserve(@PathVariable String flightId, @RequestBody SeatQuoteRequest request){
        return flightService.quoteSeats(flightId, request);
    }

}
