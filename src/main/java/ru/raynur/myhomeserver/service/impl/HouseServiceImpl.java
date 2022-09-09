package ru.raynur.myhomeserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.raynur.myhomeserver.domain.House;
import ru.raynur.myhomeserver.model.request.CreateHouseRequest;
import ru.raynur.myhomeserver.model.response.HouseResponse;
import ru.raynur.myhomeserver.repository.HouseRepository;
import ru.raynur.myhomeserver.service.HouseService;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private HouseRepository houseRepository;

    @Autowired
    public HouseServiceImpl(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<HouseResponse> findAll() {
        return StreamSupport.stream(houseRepository.findAll().spliterator(), false)
                .map(this::buildHouseResponse)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public HouseResponse findById(@NotNull Long houseId) {
        return houseRepository.findById(houseId)
                .map(this::buildHouseResponse)
                .orElseThrow(() -> new EntityNotFoundException("House " + houseId + " is not found"));
    }

    @NotNull
    @Override
    @Transactional
    public HouseResponse create(@NotNull CreateHouseRequest request) {
        House house = buildHouseRequest(request);
        return buildHouseResponse(houseRepository.save(house));
    }


    @NotNull
    @Override
    @Transactional
    public HouseResponse update(@NotNull Long houseId, @NotNull CreateHouseRequest request) {
        House house = houseRepository.findById(houseId).orElseThrow(() -> new EntityNotFoundException("House " + houseId + " is not found"));
        houseUpdate(house, request);
        return buildHouseResponse(houseRepository.save(house));
    }

    @Override
    @Transactional
    public HouseResponse delete(@NotNull Long houseId) {
        HouseResponse response = houseRepository.findById(houseId)
                .map(this::buildHouseResponse)
                .orElseThrow(() -> new EntityNotFoundException("House " + houseId + " is not found"));
        houseRepository.deleteById(houseId);
        return response;
    }

    @NotNull
    private HouseResponse buildHouseResponse(@NotNull House house) {
        return new HouseResponse()
                .setId(house.getId())
                .setAddress(house.getAddress());
    }

    @NotNull
    private House buildHouseRequest(@NotNull CreateHouseRequest request) {
        return new House()
                .setAddress(request.getAddress());
    }

    private void houseUpdate(@NotNull House house, @NotNull CreateHouseRequest request) {
        ofNullable(request.getAddress()).map(house::setAddress);
    }
}
