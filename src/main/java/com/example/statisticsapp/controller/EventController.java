package com.example.statisticsapp.controller;


import com.example.statisticsapp.dto.CreateEventRequest;
import com.example.statisticsapp.mapper.DtoMapper;
import com.example.statisticsapp.model.Event;
import com.example.statisticsapp.service.EventService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;
    private final DtoMapper dtoMapper;

    public EventController(EventService eventService, DtoMapper dtoMapper) {
        this.eventService = eventService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping
    public ResponseEntity<?> getAllEvents(@RequestParam(value = "class_id", required = false) Long class_id,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("date"));
        List<Event> events = eventService.findEvents(class_id, pageable);
        Map<LocalDate, List<Event>> grouped = events.stream().collect(Collectors.groupingBy(event -> event.getDate().toLocalDate()));
        return new ResponseEntity<>(grouped, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody CreateEventRequest createEventRequest) {
        Event event = dtoMapper.mapToEvent(createEventRequest);
        Event saved = eventService.createEvent(event);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

}
