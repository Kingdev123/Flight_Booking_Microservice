package com.bookingService.booking_service.Service;

import com.bookingService.booking_service.Dto.QuoteRequest;
import com.bookingService.booking_service.Dto.QuoteResponse;
import com.bookingService.booking_service.Dto.SeatQuoteRequest;
import com.bookingService.booking_service.Dto.SeatQuoteResponse;
import com.bookingService.booking_service.Entities.Booking;
import com.bookingService.booking_service.Entities.BookingQuote;
import com.bookingService.booking_service.Entities.BookingStatus;
import com.bookingService.booking_service.Exception.BookingFailedException;
import com.bookingService.booking_service.FeignClient.FlightClientService;
import com.bookingService.booking_service.Repository.BookingQuoteRepository;
import com.bookingService.booking_service.Repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final FlightClientService flightClientService;
    private final BookingQuoteRepository bookingQuoteRepo;
    private final BookingRepository bookingRepo;

    private static final Logger log = LoggerFactory.getLogger(BookingService.class);



    public BookingService(FlightClientService flightClientService,
                          BookingQuoteRepository bookingQuoteRepo,
                          BookingRepository bookingRepository) {
        this.flightClientService = flightClientService;
        this.bookingQuoteRepo = bookingQuoteRepo;
        this.bookingRepo = bookingRepository;
    }

    public QuoteResponse createQuote(QuoteRequest request){

        SeatQuoteRequest seatQuoteRequest = new SeatQuoteRequest();
        seatQuoteRequest.setSeats(request.getSeats());

        SeatQuoteResponse response = null;
        try{
            response = flightClientService.reserveSeats(request.getFlightId(), seatQuoteRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        BookingQuote quote = new BookingQuote(
                request.getFlightId(),
                request.getSeats(),
                response.getPricePerSeat()
            );
        bookingQuoteRepo.save(quote);


        return new QuoteResponse(
                quote.getQuoteId(),
                quote.getFlightId(),
                quote.getSeats(),
                quote.getPricePerSeat()
        );

    }

    public void confirmBooking(String quoteId) {

        log.info("Confirm booking request received for quoteId={}", quoteId);

        if (quoteId == null || quoteId.isBlank()) {
            throw new IllegalArgumentException("QuoteId cannot be null");
        }

        BookingQuote quote = bookingQuoteRepo.findById(quoteId)
                .orElseThrow(() -> {
                    log.error("Quote not found for quoteId={}", quoteId);
                    return new BookingFailedException("Invalid or expired quote");
                });

        if (quote.getStatus() == BookingStatus.CONFIRMED) {
            log.warn("Quote already confirmed: {}", quoteId);
            return;
        }

        try {

            Booking booking = new Booking(
                    quote.getFlightId(),
                    quote.getSeats(),
                    quote.getPricePerSeat(),
                    BookingStatus.CONFIRMED
            );

            bookingRepo.save(booking);

            quote.setStatus(BookingStatus.CONFIRMED);
            bookingQuoteRepo.save(quote);

            log.info("Booking confirmed successfully for quoteId={}", quoteId);

        } catch (Exception ex) {
            log.error("Booking confirmation failed for quoteId={}", quoteId, ex);
            throw new BookingFailedException("Booking confirmation failed");
        }
    }

}
