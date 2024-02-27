package com.rsvp.service.impl;

import com.rsvp.dto.BookingDto;
import com.rsvp.dto.ResponseDto;
import com.rsvp.exception.BadRequestException;
import com.rsvp.exception.NotFoundException;
import com.rsvp.mapper.BookingMapper;
import com.rsvp.model.Booking;
import com.rsvp.model.Concert;
import com.rsvp.repository.BookingRepository;
import com.rsvp.repository.ConcertRepository;
import com.rsvp.service.BookingService;
import com.rsvp.service.dao.BookingSpecification;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ConcertRepository concertRepository;
    private final BookingMapper bookingMapper;

    public BookingServiceImpl(BookingRepository bookingRepository, ConcertRepository concertRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.concertRepository = concertRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    @Transactional
    public ResponseDto createBooking(BookingDto bookingDto) {
        Optional<Concert> concert = concertRepository.findById(bookingDto.getConcertId());

        if (concert.isEmpty()) {
            throw new NotFoundException("Concert Data Not Found");
        }

        List<Booking> bookings = bookingRepository.findAll(BookingSpecification.buildBookingSpecification(bookingDto));

        int bookTotal = (bookings.stream().mapToInt(Booking::getBookingQty).sum()) + bookingDto.getBookingQty();
        if (bookTotal > 10000) {
            throw new BadRequestException("Booking Failed because Ticket was Overload");
        }

        Booking book = new Booking();
        BeanUtils.copyProperties(bookingDto, book);
        book.setConcert(concert.get());

        book = bookingRepository.save(book);

        return new ResponseDto<>(
                "OK",
                bookingMapper.entityToDto(book)
        );
    }
}
