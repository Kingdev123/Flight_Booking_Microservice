package com.bookingService.booking_service.Repository;

import com.bookingService.booking_service.Entities.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking, String> {

}
