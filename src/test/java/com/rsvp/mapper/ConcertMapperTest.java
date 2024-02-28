package com.rsvp.mapper;

import com.rsvp.dto.ConcertDto;
import com.rsvp.model.Concert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ConcertMapperTest {

    @InjectMocks
    ConcertMapperImpl concertMapper;

    private Concert concert = new Concert();
    private ConcertDto concertDto = new ConcertDto();
    private List<Concert> concertList = new ArrayList<>();
    private List<ConcertDto> concertDtoList = new ArrayList<>();


    @BeforeEach
    void init() {
        concertDto = new ConcertDto("Test Concert", LocalDateTime.now());

        BeanUtils.copyProperties(concertDto, concert);
        concert.setId(UUID.randomUUID());
        concert.setName(concertDto.getConcertName());
        concert.setActive(true);

        concertDtoList.add(new ConcertDto("Test Concert 1", LocalDateTime.now()));
        concertDtoList.add(new ConcertDto("Test Concert 2", LocalDateTime.now()));
        concertDtoList.add(new ConcertDto("Test Concert 3", LocalDateTime.now()));

        Concert concertData = new Concert();
        concertData.setId(UUID.randomUUID());
        concertData.setName("Test Concert 1");
        concertData.setConcertDate(LocalDateTime.now());
        concertData.setActive(true);
        concertList.add(concertData);
        concertData.setId(UUID.randomUUID());
        concertData.setName("Test Concert 2");
        concertData.setConcertDate(LocalDateTime.now());
        concertData.setActive(true);
        concertList.add(concertData);
        concertData.setId(UUID.randomUUID());
        concertData.setName("Test Concert 3");
        concertData.setConcertDate(LocalDateTime.now());
        concertData.setActive(true);
        concertList.add(concertData);
    }

    @Test
    void givenConcertDto_whenMapperDtoToEntity_thenReturnEntityData() {
        Concert concertData = concertMapper.dtoToEntity(concertDto);
        assertEquals(concertData.getName(), concertDto.getConcertName());
        assertEquals(concertData.getConcertDate(), concertDto.getConcertDate());
    }

    @Test
    void givenNullConcertDto_whenMapperDtoToEntity_thenReturnNull() {
        Concert concertData = concertMapper.dtoToEntity(null);
        assertNull(concertData);
    }

    @Test
    void givenListData_whenMapperEntityToDto_thenReturnDtoListData() {
        List<ConcertDto> dtoList = concertMapper.entityListToDtoList(concertList);
        assertNotNull(dtoList);
        assertEquals(dtoList.get(0).getConcertName(), concertList.get(0).getName());
        assertEquals(dtoList.get(0).getConcertDate(), concertList.get(0).getConcertDate());
    }

    @Test
    void givenNullValueList_whenMapperEntityToDto_thenReturnNull() {
        List<ConcertDto> dtos = new ArrayList<>();
        dtos.add(null);
        dtos.add(null);
        List<Concert> entities = new ArrayList<>();
        entities.add(null);
        entities.add(null);
        List<ConcertDto> result = concertMapper.entityListToDtoList(entities);
        assertEquals(result, dtos);
    }

    @Test
    void givenNullValue_whenMapperEntityToDto_thenReturnNull() {
        List<ConcertDto> result = concertMapper.entityListToDtoList(null);
        assertNull(result);
    }
}
