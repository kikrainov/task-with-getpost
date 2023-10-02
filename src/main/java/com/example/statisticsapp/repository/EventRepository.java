package com.example.statisticsapp.repository;

import com.example.statisticsapp.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findByClassId(Long id, Pageable pageable);


}
