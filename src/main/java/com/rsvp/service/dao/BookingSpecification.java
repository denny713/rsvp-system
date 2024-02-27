package com.rsvp.service.dao;

import com.rsvp.dto.BookingDto;
import com.rsvp.model.Booking;
import com.rsvp.model.Concert;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BookingSpecification {

    public static Specification<Booking> buildBookingSpecification(BookingDto bookingDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<Booking, Concert> concertJoin = root.join("concert");

            predicates.add(criteriaBuilder.isTrue(root.get("active")));
            predicates.add(criteriaBuilder.isTrue(concertJoin.get("active")));
            predicates.add(criteriaBuilder.equal(concertJoin.get("id"), bookingDto.getConcertId()));
            predicates.add(criteriaBuilder.between(
                    root.get("bookingDate"),
                    bookingDto.getBookingDate().minusMinutes(20),
                    bookingDto.getBookingDate().plusMinutes(20)
            ));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
