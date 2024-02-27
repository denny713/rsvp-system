package com.rsvp.controller;

import com.rsvp.dto.BookingDto;
import com.rsvp.dto.ResponseDto;
import com.rsvp.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> doBook(@Valid @RequestBody BookingDto bookingDto) {
        return ResponseEntity.ok(bookingService.createBooking(bookingDto));
    }
}
