package ru.raynur.myhomeserver.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.List;

@Data
@Accessors(chain = false)
public class CreateHouseRequest {
    private Long id;
    private String address;
}
