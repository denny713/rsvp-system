package com.rsvp.service;

import com.rsvp.dto.BookingDto;
import com.rsvp.dto.ResponseDto;
import com.rsvp.exception.BadRequestException;
import com.rsvp.exception.NotFoundException;
import com.rsvp.mapper.BookingMapper;
import com.rsvp.model.Booking;
import com.rsvp.model.Concert;
import com.rsvp.repository.BookingRepository;
import com.rsvp.repository.ConcertRepository;
import com.rsvp.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @InjectMocks
    BookingServiceImpl bookingService;

    @Mock
    BookingRepository bookingRepository;

    @Mock
    ConcertRepository concertRepository;

    @Mock
    BookingMapper bookingMapper;

    private Concert concert = new Concert();
    private Booking booking = new Booking();
    private BookingDto bookingDto = new BookingDto();
    private List<Booking> bookingList = new ArrayList<>();

    @BeforeEach
    void init() {
        concert = new Concert();
        concert.setId(UUID.randomUUID());
        concert.setName("Test Concert");
        concert.setConcertDate(LocalDateTime.now());
        concert.setActive(true);

        booking = new Booking();
        booking.setConcert(concert);
        booking.setId(UUID.randomUUID());
        booking.setBookingDate(LocalDateTime.now());
        booking.setBookingQty(7);
        booking.setActive(true);

        bookingDto = new BookingDto(booking.getId(), booking.getBookingDate(), booking.getBookingQty());

        bookingList.add(booking);
    }

    @Test
    void givenBookingDto_whenCallSave_thenReturnOk() {
        when(concertRepository.findById(any(UUID.class))).thenReturn(Optional.of(concert));
        when(bookingRepository.findAll(org.mockito.ArgumentMatchers.isA(Specification.class))).thenReturn(bookingList);
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        ResponseDto result = bookingService.createBooking(bookingDto);
        assertEquals("OK", result.getMessage());
    }

    @Test
    void givenBookingDto_whenCallSave_thenThrowNotFoundException() {
        when(concertRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        try {
            ResponseDto result = bookingService.createBooking(bookingDto);
        } catch (NotFoundException ex) {
            assertEquals("Concert Data Not Found", ex.getMessage());
        }
    }

    @Test
    void givenBookingDto_whenCallSave_thenThrowBadRequestException() {
        bookingList.get(0).setBookingQty(10000);

        when(concertRepository.findById(any(UUID.class))).thenReturn(Optional.of(concert));
        when(bookingRepository.findAll(org.mockito.ArgumentMatchers.isA(Specification.class))).thenReturn(bookingList);

        try {
            ResponseDto result = bookingService.createBooking(bookingDto);
        } catch (BadRequestException ex) {
            assertEquals("Booking Failed because Ticket was Overload", ex.getMessage());
        }
    }
}
