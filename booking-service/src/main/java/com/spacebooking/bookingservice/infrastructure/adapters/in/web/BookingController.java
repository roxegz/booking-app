package com.spacebooking.bookingservice.infrastructure.adapters.in.web;

import com.spacebooking.bookingservice.domain.model.Booking;
import com.spacebooking.bookingservice.infrastructure.adapters.in.web.dto.BookingResponse;
import com.spacebooking.bookingservice.infrastructure.adapters.in.web.dto.CreateBookingRequest;
import com.spacebooking.bookingservice.infrastructure.adapters.out.persistence.BookingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingRepository bookingRepository;

    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @GetMapping
    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    public BookingResponse createBooking(@RequestBody CreateBookingRequest request) {
        Booking booking = new Booking();
        booking.setUserId(request.getUserId());
        booking.setResourceId(request.getResourceId());
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        booking.setStatus("CONFIRMED");

        Booking saved = bookingRepository.save(booking);
        return mapToResponse(saved);
    }

    private BookingResponse mapToResponse(Booking booking) {
        return new BookingResponse(
                booking.getId(),
                booking.getUserId(),
                booking.getResourceId(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getStatus()
        );
    }
}