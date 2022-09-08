package ru.raynur.myhomeserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.raynur.myhomeserver.model.response.ClientEventResponse;
import ru.raynur.myhomeserver.service.ClientEventService;
import ru.raynur.myhomeserver.service.ModeratorEventService;

import java.util.List;

@RestController
@RequestMapping(path = "/events")
@PreAuthorize("hasAuthority('scope:client')")
public class ClientEventController {

    private final ClientEventService eventService;

    @Autowired
    public ClientEventController(ClientEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(path = "/house/{houseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<ClientEventResponse> getAllEventsByHouseId(@PathVariable Long houseId) {
        return eventService.findByHouseId(houseId);
    }

    @GetMapping(path = "/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ClientEventResponse getEvent(@PathVariable Long eventId) {
        return eventService.findById(eventId);
    }
}
