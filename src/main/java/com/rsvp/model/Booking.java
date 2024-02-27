package com.rsvp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "booking")
public class Booking extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "concert_id", referencedColumnName = "id")
    private Concert concert;

    @Column(name = "booking_date")
    private LocalDateTime bookingDate;

    @Column(name = "booking_qty")
    private Integer bookingQty;
}
