package com.helloevent.backend.controller;


import com.helloevent.backend.dto.EventRequest;
import com.helloevent.backend.model.Category;
import com.helloevent.backend.model.Event;
import com.helloevent.backend.model.Status;
import com.helloevent.backend.model.User;
import com.helloevent.backend.repository.CategoryRepository;
import com.helloevent.backend.repository.UserRepository;
import com.helloevent.backend.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {


    private final EventService eventService;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public EventController(EventService eventService, UserRepository userRepository, CategoryRepository categoryRepository){
        this.eventService = eventService;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @PostMapping
    public ResponseEntity<?> addEvent(@RequestBody EventRequest eventRequest) {
        User user = userRepository.findById(eventRequest.getUserId()).orElse(null);
        Category category = categoryRepository.findById(eventRequest.getCategoryId()).orElse(null);

        if (user==null || category==null) {
            return ResponseEntity.badRequest().body("User ou Category introuvable");
        }

        Event event = new Event();
        event.setName(eventRequest.getName());
        event.setDescription(eventRequest.getDescription());
        event.setDate(eventRequest.getDate());
        event.setLieu(eventRequest.getLieu());
        event.setStatus(Status.Closed);
        event.setUser(user);
        event.setCategory(category);

        eventService.saveEvent(event);

        return ResponseEntity.ok("Événement créé avec succès");


    }
}
