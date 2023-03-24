package com.ogul.kafkaconsumer.repository;

import com.ogul.kafkaconsumer.entity.WikimediaData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WikimediaDataRepository extends MongoRepository<WikimediaData, String> {
}
