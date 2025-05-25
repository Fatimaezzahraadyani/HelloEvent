package com.helloevent.backend.service;


import com.helloevent.backend.dto.EventRequest;
import com.helloevent.backend.model.Category;
import com.helloevent.backend.model.Event;
import com.helloevent.backend.model.User;
import com.helloevent.backend.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;


    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getEventsByName(String name) {
        return eventRepository.findEventsByName(name);
    }

    public Event getEventById(long id) {
        return eventRepository.getEventById(id);
    }


    public Event updateEvent(long id, EventRequest request, User user, Category category){
        return eventRepository.findById(id).map(event -> {
            event.setName(request.getName());
            event.setDescription(request.getDescription());
            event.setDate(request.getDate());
            event.setLieu(request.getLieu());
            event.setStatus(request.getStatus());
            event.setUser(user);
            event.setCategory(category);
            return eventRepository.save(event);
        }).orElse(null);
    }

    public boolean deleteEvent(long id) {
        if (!eventRepository.existsById(id)) {
            return false;
        }
        eventRepository.deleteById(id);
        return true;
    }


}

