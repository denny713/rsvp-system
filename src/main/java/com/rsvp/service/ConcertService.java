package com.rsvp.service;

import com.rsvp.dto.ConcertDto;
import com.rsvp.dto.ResponseDto;
import com.rsvp.model.Concert;

public interface ConcertService {

    ResponseDto addConcert(ConcertDto dto);

    ResponseDto getAllConcert();
}
