package ru.raynur.myhomeserver.service;

import ru.raynur.myhomeserver.model.request.CreateHouseRequest;
import ru.raynur.myhomeserver.model.response.HouseResponse;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface HouseService {
    @NotNull
    List<HouseResponse> findAll();

    @NotNull
    HouseResponse findById(@NotNull Long houseId);

    @NotNull
    HouseResponse create(@NotNull CreateHouseRequest request);

    @NotNull
    HouseResponse update(@NotNull Long houseId, @NotNull CreateHouseRequest request);

    HouseResponse delete(@NotNull Long houseId);
}
