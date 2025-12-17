package com.flightService.flight_service.Repository;

import com.flightService.flight_service.Entities.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlightRepository extends MongoRepository<Flight, String> {
}
