package ru.raynur.myhomeserver.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.List;

@Data
@Accessors(chain = false)
public class CreateEventRequest {
    private Long id;
    private String title;
    private String text;
    private Instant startedAt;
    private Instant endedAt;
    private List<Long> housesIds;
}
