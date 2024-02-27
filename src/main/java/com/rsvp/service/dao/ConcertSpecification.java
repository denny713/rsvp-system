package com.rsvp.service.dao;

import com.rsvp.model.Concert;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConcertSpecification {

    public static Specification<Concert> buildConcertSpecification() {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("active"), true));
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("concertDate"), LocalDateTime.now()));

            Predicate[] predicateArr = new Predicate[predicates.size()];
            return criteriaBuilder.and(predicates.toArray(predicateArr));
        };
    }
}
