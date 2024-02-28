package com.rsvp.service.impl;

import com.rsvp.dto.ConcertDto;
import com.rsvp.dto.ResponseDto;
import com.rsvp.mapper.ConcertMapper;
import com.rsvp.model.Concert;
import com.rsvp.repository.ConcertRepository;
import com.rsvp.service.ConcertService;
import com.rsvp.service.dao.ConcertSpecification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConcertServiceImpl implements ConcertService {

    private final ConcertRepository concertRepository;
    private final ConcertMapper concertMapper;

    public ConcertServiceImpl(ConcertRepository concertRepository, ConcertMapper concertMapper) {
        this.concertRepository = concertRepository;
        this.concertMapper = concertMapper;
    }

    @Override
    @Transactional
    public ResponseDto addConcert(ConcertDto dto) {
        return new ResponseDto(
                "OK",
                concertRepository.save(concertMapper.dtoToEntity(dto))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto getAllConcert() {
        List<Concert> concertList = concertRepository.findAll(ConcertSpecification.buildConcertSpecification());

        return new ResponseDto(
                "OK",
                concertMapper.entityListToDtoList(concertList)
        );
    }
}
