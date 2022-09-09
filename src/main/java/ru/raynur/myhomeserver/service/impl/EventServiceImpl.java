package ru.raynur.myhomeserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.raynur.myhomeserver.domain.Event;
import ru.raynur.myhomeserver.domain.House;
import ru.raynur.myhomeserver.model.request.CreateEventRequest;
import ru.raynur.myhomeserver.model.response.EventResponse;
import ru.raynur.myhomeserver.repository.EventRepository;
import ru.raynur.myhomeserver.repository.HouseRepository;
import ru.raynur.myhomeserver.service.EventService;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private HouseRepository houseRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, HouseRepository houseRepository) {
        this.eventRepository = eventRepository;
        this.houseRepository = houseRepository;
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> findAll() {
        return StreamSupport.stream(eventRepository.findAll().spliterator(), false)
                .map(this::buildEventResponse)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public EventResponse findById(@NotNull Long eventId) {
        return eventRepository.findById(eventId)
                .map(this::buildEventResponse)
                .orElseThrow(() -> new EntityNotFoundException("Event " + eventId + " is not found"));
    }

    @NotNull
    @Override
    @Transactional
    public EventResponse create(@NotNull CreateEventRequest request) {
        Event event = buildEventRequest(request);
        return buildEventResponse(eventRepository.save(event));
    }


    @NotNull
    @Override
    @Transactional
    public EventResponse update(@NotNull Long eventId, @NotNull CreateEventRequest request) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Event " + eventId + " is not found"));
        eventUpdate(event, request);
        return buildEventResponse(eventRepository.save(event));
    }

    @Override
    @Transactional
    public EventResponse delete(@NotNull Long eventId) {
        EventResponse response = eventRepository.findById(eventId)
                .map(this::buildEventResponse)
                .orElseThrow(() -> new EntityNotFoundException("Event " + eventId + " is not found"));
        eventRepository.deleteById(eventId);
        return response;
    }

    @NotNull
    private EventResponse buildEventResponse(@NotNull Event event) {
        return new EventResponse()
                .setId(event.getId())
                .setTitle(event.getTitle())
                .setText(event.getText())
                .setStartedAt(event.getStartedAt())
                .setEndedAt(event.getEndedAt())
                .setHousesIds(event.getHouses().stream()
                        .map(House::getId)
                        .collect(Collectors.toList()));
    }

    @NotNull
    private Event buildEventRequest(@NotNull CreateEventRequest request) {
        List<House> houses = new ArrayList<>();
        request.getHousesIds().forEach(id -> houses.add(houseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("House with id" + id + " is not found"))));
        return new Event()
                .setTitle(request.getTitle())
                .setText(request.getText())
                .setStartedAt(request.getStartedAt())
                .setEndedAt(request.getEndedAt())
                .setHouses(houses);
    }

    private void eventUpdate(@NotNull Event event, @NotNull CreateEventRequest request) {
        List<House> houses = new ArrayList<>();
        ofNullable(request.getTitle()).map(event::setTitle);
        ofNullable(request.getText()).map(event::setText);
        ofNullable(request.getStartedAt()).map(event::setStartedAt);
        ofNullable(request.getEndedAt()).map(event::setEndedAt);
        ofNullable(request.getHousesIds()).ifPresent(ids -> ids.forEach(id -> {
            houses.add(houseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("House with id" + id + " is not found")));
            event.setHouses(houses);
        }));
    }
}
