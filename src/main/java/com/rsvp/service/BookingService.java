package com.rsvp.service;

import com.rsvp.dto.BookingDto;
import com.rsvp.dto.ResponseDto;

public interface BookingService {

    ResponseDto createBooking(BookingDto bookingDto);
}
