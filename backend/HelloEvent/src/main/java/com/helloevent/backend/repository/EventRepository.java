package com.helloevent.backend.repository;

import com.helloevent.backend.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findEventsByName(String name);

    Event getEventById(long id);
}
