package ru.raynur.myhomeserver.service;

import ru.raynur.myhomeserver.model.request.CreateEventRequest;
import ru.raynur.myhomeserver.model.response.EventResponse;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface EventService {
    @NotNull
    List<EventResponse> findAll();

    @NotNull
    EventResponse findById(@NotNull Long eventId);

    @NotNull
    EventResponse create(@NotNull CreateEventRequest request);

    @NotNull
    EventResponse update(@NotNull Long eventId, @NotNull CreateEventRequest request);

    EventResponse delete(@NotNull Long eventId);
}
