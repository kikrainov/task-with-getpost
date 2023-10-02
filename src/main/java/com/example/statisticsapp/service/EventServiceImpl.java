package com.example.statisticsapp.service;

import com.example.statisticsapp.model.Event;
import com.example.statisticsapp.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    @Transactional
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> findEvents(Long class_id, Pageable pageable) {
        Page<Event> events;
        if (class_id == null) {
            events = eventRepository.findAll(pageable);
        } else {
            events = eventRepository.findByClassId(class_id, pageable);
        }
        return events.getContent();
    }

}
