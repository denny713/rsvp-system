package com.rsvp.service;

import com.rsvp.dto.ConcertDto;
import com.rsvp.dto.ResponseDto;
import com.rsvp.mapper.ConcertMapper;
import com.rsvp.model.Concert;
import com.rsvp.repository.ConcertRepository;
import com.rsvp.service.impl.ConcertServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConcertServiceTest {

    @InjectMocks
    ConcertServiceImpl concertService;

    @Mock
    ConcertMapper concertMapper;

    @Mock
    ConcertRepository concertRepository;

    private List<Concert> concertList = new ArrayList<>();
    private List<ConcertDto> concertDtoList = new ArrayList<>();
    private Concert concert = new Concert();
    private ConcertDto concertDto = new ConcertDto();

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
    void givenConcertDto_whenCallSave_thenReturnOk() {
        ResponseDto response = new ResponseDto("OK", concert);

        when(concertMapper.dtoToEntity(concertDto)).thenReturn(concert);
        when(concertRepository.save(any(Concert.class))).thenReturn(concert);

        ResponseDto result = concertService.addConcert(concertDto);
        assertEquals(result.getMessage(), response.getMessage());
    }

    @Test
    void searchConcertData_thenReturnListOfData() {
        when(concertRepository.findAll(org.mockito.ArgumentMatchers.isA(Specification.class))).thenReturn(concertList);

        ResponseDto result = concertService.getAllConcert();
        assertNotNull(result.getData());
    }
}
