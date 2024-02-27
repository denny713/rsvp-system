package com.rsvp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ConcertDto implements Serializable {

    @NotBlank(message = "Concert Name Cannot be Null or Blank")
    @Size(min = 1, max = 100)
    private String concertName;
    private LocalDateTime concertDate;
}
