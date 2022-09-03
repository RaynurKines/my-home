package ru.raynur.myhomeserver.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class ClientEventResponse {
    private Long id;
    private String title;
    private String text;
    private Instant startedAt;
    private Instant endedAt;
}