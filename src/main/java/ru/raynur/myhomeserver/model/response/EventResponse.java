package ru.raynur.myhomeserver.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.List;

@Data
@Accessors(chain = true)
public class EventResponse {
    private Long id;
    private String title;
    private String text;
    private Instant startedAt;
    private Instant endedAt;
    private List<Long> housesIds;
}
