package com.bookingService.booking_service.FeignClient;

import com.bookingService.booking_service.Dto.SeatQuoteRequest;
import com.bookingService.booking_service.Dto.SeatQuoteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "flight-service", url = "http://localhost:8081/flights")
public interface FlightClientService {

    @PostMapping("/{flightId}/quote")
    SeatQuoteResponse reserveSeats(
            @PathVariable String flightId,
            @RequestBody SeatQuoteRequest request
    );
}
