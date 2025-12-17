package com.bookingService.booking_service.Exception;

public class BookingFailedException extends RuntimeException{
    public BookingFailedException(String message){
        super(message);
    }
}
