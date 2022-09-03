package ru.raynur.myhomeserver.repository;

import org.springframework.data.repository.CrudRepository;
import ru.raynur.myhomeserver.domain.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
}
