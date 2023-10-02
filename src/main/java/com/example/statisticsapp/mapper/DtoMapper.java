package com.example.statisticsapp.mapper;

import com.example.statisticsapp.dto.CreateEventRequest;
import com.example.statisticsapp.model.Event;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
    private final ModelMapper modelMapper;

    public DtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Event mapToEvent(CreateEventRequest createEventRequest) {
        return modelMapper.map(createEventRequest, Event.class);
    }
}
