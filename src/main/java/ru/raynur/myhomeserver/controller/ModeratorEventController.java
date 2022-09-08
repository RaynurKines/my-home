package ru.raynur.myhomeserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.raynur.myhomeserver.model.request.CreateEventRequest;
import ru.raynur.myhomeserver.model.response.ModeratorEventResponse;
import ru.raynur.myhomeserver.service.ModeratorEventService;

import java.util.List;

@RestController
@RequestMapping(path = "moderator/events")
@PreAuthorize("hasAuthority('scope:moderator')")
public class ModeratorEventController {

    private final ModeratorEventService moderatorEventService;

    @Autowired
    public ModeratorEventController(ModeratorEventService moderatorEventService) {
        this.moderatorEventService = moderatorEventService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ModeratorEventResponse addEvent(@RequestBody CreateEventRequest request) {
        return moderatorEventService.create(request);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<ModeratorEventResponse> getAllEvents() {
        return moderatorEventService.findAll();
    }

    @GetMapping(path = "/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ModeratorEventResponse getEvent(@PathVariable Long eventId) {
        return moderatorEventService.findById(eventId);
    }

    @PatchMapping(path = "/{eventId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ModeratorEventResponse updateEvent(@PathVariable Long eventId, @RequestBody CreateEventRequest event) {
        return moderatorEventService.update(eventId, event);
    }

    @DeleteMapping(path = "/{eventId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ModeratorEventResponse deleteEvent(@PathVariable Long eventId) {
        return moderatorEventService.delete(eventId);
    }
}
