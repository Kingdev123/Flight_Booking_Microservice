package com.bookingService.booking_service.Repository;

import com.bookingService.booking_service.Entities.BookingQuote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingQuoteRepository extends MongoRepository<BookingQuote, String> {
}
