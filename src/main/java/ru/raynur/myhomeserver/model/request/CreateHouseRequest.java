package ru.raynur.myhomeserver.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = false)
public class CreateHouseRequest {
    private Long id;
    private String address;
}
