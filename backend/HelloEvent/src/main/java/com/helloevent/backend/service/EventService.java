package com.helloevent.backend.service;


import com.helloevent.backend.dto.EventRequest;
import com.helloevent.backend.model.Category;
import com.helloevent.backend.model.Event;
import com.helloevent.backend.model.Role;
import com.helloevent.backend.model.User;
import com.helloevent.backend.repository.EventRepository;
import com.helloevent.backend.repository.UserRepository;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final WebInvocationPrivilegeEvaluator webInvocationPrivilegeEvaluator;
    private final CategoryService categoryService;


    public EventService(EventRepository eventRepository, JwtService jwtService, UserRepository userRepository, WebInvocationPrivilegeEvaluator webInvocationPrivilegeEvaluator, CategoryService categoryService) {
        this.eventRepository = eventRepository;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.webInvocationPrivilegeEvaluator = webInvocationPrivilegeEvaluator;
        this.categoryService = categoryService;
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

    public Event createEvent(EventRequest eventRequest, String token) {

        String usernameFromToken = jwtService.extractUsername(token.substring( 7));

        User user = userRepository.getUserByUsernameOrEmail(usernameFromToken);

        Category category = categoryService.getCategoryById(eventRequest.getCategoryId());

        if (user.getRole() == Role.Admin){
            Event event = new Event();

            event.setName(eventRequest.getName());
            event.setDescription(eventRequest.getDescription());
            event.setCategory(category);
            event.setUser(user);
            event.setLieu(eventRequest.getLieu());
            event.setStatus(eventRequest.getStatus());
            event.setDate(eventRequest.getDate());

            return eventRepository.save(event);
        }else {
            System.out.println("unauthoriezed action");
            throw new Error("unauthoriezed action");
        }



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

