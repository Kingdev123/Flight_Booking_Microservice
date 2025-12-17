package com.flightService.flight_service.Exception;

public class InsufficientSeatsException extends RuntimeException{
    public InsufficientSeatsException(String message){
        super(message);
    }
}
