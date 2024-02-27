package com.rsvp.mapper;

import com.rsvp.dto.ConcertDto;
import com.rsvp.model.Concert;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConcertMapper {

    @Mapping(target = "name", source = "concertName")
    Concert dtoToEntity(ConcertDto concertDto);

    @Mapping(target = "concertName", source = "name")
    List<ConcertDto> entityListToDtoList(List<Concert> concert);

    @AfterMapping
    public default void mapConcertEntityToConcertDto(Concert concert, @MappingTarget ConcertDto concertDto) {
        concertDto.setConcertName(concert.getName());
    }
}
