package ru.raynur.myhomeserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.raynur.myhomeserver.model.request.CreateEventRequest;
import ru.raynur.myhomeserver.model.response.EventResponse;
import ru.raynur.myhomeserver.service.EventService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "moderator/events")
@PreAuthorize("hasAuthority('scope:moderator')")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EventResponse addEvent(@RequestBody CreateEventRequest request) {
        return eventService.create(request);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<EventResponse> getAllEvents() {
        return eventService.findAll();
    }

    @GetMapping(path = "/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody EventResponse getEvent(@PathVariable Long eventId) {
        return eventService.findById(eventId);
    }

    @PatchMapping(path = "/{eventId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EventResponse updateEvent(@PathVariable Long eventId, @RequestBody CreateEventRequest event) {
        return eventService.update(eventId, event);
    }

    @DeleteMapping(path = "/{eventId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EventResponse deleteEvent(@PathVariable Long eventId) {
        return eventService.delete(eventId);
    }
}
