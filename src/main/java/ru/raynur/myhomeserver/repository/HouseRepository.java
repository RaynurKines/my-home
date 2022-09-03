package ru.raynur.myhomeserver.repository;

import org.springframework.data.repository.CrudRepository;
import ru.raynur.myhomeserver.domain.House;

public interface HouseRepository extends CrudRepository<House, Long> {
}
