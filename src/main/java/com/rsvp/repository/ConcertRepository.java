package com.rsvp.repository;

import com.rsvp.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, UUID>, JpaSpecificationExecutor<Concert> {
}
