package com.bookingService.booking_service.Dto;

public class QuoteResponse {
    private String quoteId;
    private String flightId;
    private int seats;
    private double pricePerSeat;
    private double totalPrice;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

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

    public double getPricePerSeat() {
        return pricePerSeat;
    }

    public void setPricePerSeat(double pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }

    public QuoteResponse(String quoteId, String flightId, int seats, double pricePerSeat) {
        this.quoteId = quoteId;
        this.flightId = flightId;
        this.seats = seats;
        this.pricePerSeat = pricePerSeat;
    }
}
