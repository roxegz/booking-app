package com.spacebooking.bookingservice.infrastructure.adapters.out.persistence;

import com.spacebooking.bookingservice.domain.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
}