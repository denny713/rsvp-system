package com.rsvp.controller;

import com.rsvp.dto.ConcertDto;
import com.rsvp.dto.ResponseDto;
import com.rsvp.mapper.ConcertMapper;
import com.rsvp.service.ConcertService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/concert")
public class ConcertController {

    private final ConcertService concertService;

    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> addConcert(@Valid @RequestBody ConcertDto concertDto) {
        return ResponseEntity.ok(concertService.addConcert(concertDto));
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAllConcert(){
        return ResponseEntity.ok(concertService.getAllConcert());
    }
}
