package ru.raynur.myhomeserver.service;

import javax.validation.constraints.NotNull;
import ru.raynur.myhomeserver.model.request.CreateEventRequest;
import ru.raynur.myhomeserver.model.response.ModeratorEventResponse;

import java.util.List;

public interface ModeratorEventService {
    @NotNull
    List<ModeratorEventResponse> findAll();

    @NotNull
    ModeratorEventResponse findById(@NotNull Long eventId);

    @NotNull
    ModeratorEventResponse create(@NotNull CreateEventRequest request);

    @NotNull
    ModeratorEventResponse update(@NotNull Long eventId, @NotNull CreateEventRequest request);

    ModeratorEventResponse delete(@NotNull Long eventId);
}
