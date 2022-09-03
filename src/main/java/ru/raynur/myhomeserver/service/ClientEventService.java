package ru.raynur.myhomeserver.service;

import ru.raynur.myhomeserver.model.response.ClientEventResponse;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ClientEventService {
    @NotNull
    List<ClientEventResponse> findByHouseId(@NotNull Long houseId);

    @NotNull
    ClientEventResponse findById(@NotNull Long eventId);
}
