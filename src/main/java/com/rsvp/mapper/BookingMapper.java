package com.rsvp.mapper;

import com.rsvp.dto.BookingDto;
import com.rsvp.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(target = "concertId", source = "concert.id")
    BookingDto entityToDto(Booking booking);
}
