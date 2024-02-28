package com.rsvp.mapper;

import com.rsvp.dto.BookingDto;
import com.rsvp.model.Booking;
import com.rsvp.model.Concert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class BookingMapperTest {

    @InjectMocks
    BookingMapperImpl bookingMapper;

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
    void givenBookingEntity_whenMapperEntityToDto_thenReturnDtoData() {
        BookingDto result = bookingMapper.entityToDto(booking);
        assertEquals(booking.getBookingQty(), result.getBookingQty());
        assertEquals(booking.getBookingDate(), result.getBookingDate());
        assertEquals(concert.getId(), result.getConcertId());
    }

    @Test
    void givenNullValue_whenMapperEntityToDto_thenReturnNull() {
        BookingDto result = bookingMapper.entityToDto(null);
        assertNull(result);
    }

    @Test
    void givenNullConcert_whenMapperEntityToDto_thenReturnBookingDataWithNullConcertId() {
        booking.setConcert(null);
        BookingDto result = bookingMapper.entityToDto(booking);
        assertNull(result.getConcertId());
    }

    @Test
    void givenNullConcertId_whenMapperEntityToDto_thenReturnBookingDataWithNullConcertId() {
        booking.getConcert().setId(null);
        BookingDto result = bookingMapper.entityToDto(booking);
        assertNull(result.getConcertId());
    }
}
