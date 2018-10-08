package com.example.bettor.event;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
    List<Event> findByTypeAndDateTimeGreaterThan(String type, Date date);
}