package ru.raynur.myhomeserver.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HouseResponse {
    private Long id;
    private String address;
}