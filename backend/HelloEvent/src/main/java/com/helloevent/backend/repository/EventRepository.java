package com.helloevent.backend.repository;

import com.helloevent.backend.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepository extends JpaRepository<Event, Long> {
}
