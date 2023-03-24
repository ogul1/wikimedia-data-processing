package com.ogul.kafkaconsumer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "${spring.kafka.topic.name}")
@Getter
@Setter
public class WikimediaData {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Lob
    private String wikiEventData;
}
