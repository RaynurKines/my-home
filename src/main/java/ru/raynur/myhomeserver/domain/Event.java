package ru.raynur.myhomeserver.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;

    private String text;

    private Instant startedAt;

    private Instant endedAt;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "event_house",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "house_id")}
    )
    private List<House> houses;
}
