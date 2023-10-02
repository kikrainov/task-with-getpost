package com.example.statisticsapp.service;

import com.example.statisticsapp.model.Event;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {
    Event createEvent(Event event);

    List<Event> findEvents(Long id, Pageable pageable);


}
