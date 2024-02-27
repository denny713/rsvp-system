package com.rsvp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class BookingDto implements Serializable {

    private UUID concertId;
    private LocalDateTime bookingDate;
    private Integer bookingQty;
}
