package com.bookingService.booking_service.Controller;

import com.bookingService.booking_service.Dto.QuoteRequest;
import com.bookingService.booking_service.Dto.QuoteResponse;
import com.bookingService.booking_service.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/quote")
    public ResponseEntity<QuoteResponse> createQuote(
            @RequestBody QuoteRequest request) {

        QuoteResponse response = bookingService.createQuote(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{quoteId}/confirm")
    public String confirmBooking(@PathVariable String quoteId) {
        bookingService.confirmBooking(quoteId);
        return "Booking confirmed successfully";
    }

}
