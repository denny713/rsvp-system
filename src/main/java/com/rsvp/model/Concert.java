package com.rsvp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "concert")
public class Concert extends BaseEntity {

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "concert_date")
    private LocalDateTime concertDate;
}
