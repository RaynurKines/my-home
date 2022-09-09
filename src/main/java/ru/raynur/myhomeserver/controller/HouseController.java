package ru.raynur.myhomeserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.raynur.myhomeserver.model.request.CreateHouseRequest;
import ru.raynur.myhomeserver.model.response.HouseResponse;
import ru.raynur.myhomeserver.service.HouseService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "moderator/houses")
@PreAuthorize("hasAuthority('scope:moderator')")
public class HouseController {

    private final HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HouseResponse addHouse(@RequestBody CreateHouseRequest request) {
        return houseService.create(request);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<HouseResponse> getAllHouses() {
        return houseService.findAll();
    }

    @GetMapping(path = "/{houseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HouseResponse getHouse(@PathVariable Long houseId) {
        return houseService.findById(houseId);
    }

    @PatchMapping(path = "/{houseId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HouseResponse updateHouse(@PathVariable Long houseId, @RequestBody CreateHouseRequest house) {
        return houseService.update(houseId, house);
    }

    @DeleteMapping(path = "/{houseId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HouseResponse deleteHouse(@PathVariable Long houseId) {
        return houseService.delete(houseId);
    }
}
