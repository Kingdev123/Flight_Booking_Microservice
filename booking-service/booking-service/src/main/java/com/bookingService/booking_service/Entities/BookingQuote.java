package com.bookingService.booking_service.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "booking_quote")
public class BookingQuote {
    @Id
    private String quoteId;
    private String flightId;
    private int seats;
    private double pricePerSeat;
    private BookingStatus status;

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public double getPricePerSeat() {
        return pricePerSeat;
    }

    public void setPricePerSeat(double pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }

    public BookingQuote(String flightId, int seats, double pricePerSeat) {
        this.flightId = flightId;
        this.seats = seats;
        this.pricePerSeat = pricePerSeat;
        this.status = BookingStatus.QUOTED;
    }
}
