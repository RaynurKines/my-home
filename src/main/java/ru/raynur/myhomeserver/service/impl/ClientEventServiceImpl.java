package ru.raynur.myhomeserver.service.impl;

import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.raynur.myhomeserver.domain.Event;
import ru.raynur.myhomeserver.domain.House;
import ru.raynur.myhomeserver.model.response.ClientEventResponse;
import ru.raynur.myhomeserver.repository.EventRepository;
import ru.raynur.myhomeserver.service.ClientEventService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ClientEventServiceImpl implements ClientEventService {

    private EventRepository eventRepository;

    @Autowired
    public ClientEventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<ClientEventResponse> findByHouseId(@NotNull Long houseId) {
        return StreamSupport.stream(eventRepository.findAll().spliterator(), false)
                .filter(event -> event.getHouses().stream().map(House::getId).toList().contains(houseId))
                .map(this::buildEventResponse)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public ClientEventResponse findById(@NotNull Long eventId) {
        return eventRepository.findById(eventId)
                .map(this::buildEventResponse)
                .orElseThrow(() -> new EntityNotFoundException("Event " + eventId + " is not found"));
    }

    @NotNull
    private ClientEventResponse buildEventResponse(@NotNull Event event) {
        return new ClientEventResponse()
                .setId(event.getId())
                .setTitle(event.getTitle())
                .setText(event.getText())
                .setStartedAt(event.getStartedAt())
                .setEndedAt(event.getEndedAt());
    }
}
