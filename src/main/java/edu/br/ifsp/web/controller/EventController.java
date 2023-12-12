package edu.br.ifsp.web.controller;

import edu.br.ifsp.domain.entities.event.Event;
import edu.br.ifsp.domain.usecases.events.CreateEventUseCase;
import edu.br.ifsp.domain.usecases.events.FindEventUseCase;
import edu.br.ifsp.domain.usecases.events.RemoveEventUseCase;
import edu.br.ifsp.domain.usecases.events.UpdateEventUseCase;
import edu.br.ifsp.web.exception.GenericResourceException;
import edu.br.ifsp.web.model.event.request.EventRequest;
import edu.br.ifsp.web.model.event.request.UpdateSituationRequest;
import edu.br.ifsp.web.model.event.response.EventResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    CreateEventUseCase createEventUseCase;
    UpdateEventUseCase updateEventUseCase;
    FindEventUseCase findEventUseCase;
    RemoveEventUseCase removeEventUseCase;

    public EventController(CreateEventUseCase createEventUseCase, UpdateEventUseCase updateEventUseCase, FindEventUseCase findEventUseCase, RemoveEventUseCase removeEventUseCase) {
        this.createEventUseCase = createEventUseCase;
        this.updateEventUseCase = updateEventUseCase;
        this.findEventUseCase = findEventUseCase;
        this.removeEventUseCase = removeEventUseCase;
    }

    @PostMapping("/save")
    public ResponseEntity<EventResponse> createNewEvent(@RequestBody EventRequest eventRequest){
        Event event = createEventUseCase.insert(eventRequest.toEvent());
        return ResponseEntity.ok(EventResponse.fromEvent(event));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> findEventById(@PathVariable("id") UUID id){
        Event event = findEventUseCase.findByUUID(id).orElseThrow(
                () -> new GenericResourceException("Event not found!", "Event")
        );
        return ResponseEntity.ok(EventResponse.fromEvent(event));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EventResponse>> findAllEvents(){
        List<Event> events = findEventUseCase.findAll();
        return ResponseEntity.ok(events.stream()
                .map(EventResponse::fromEvent)
                .collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> updateEventById(@PathVariable("id") UUID id, @RequestBody UpdateSituationRequest updateSituationRequest){
        Event eventUpdate = updateSituationRequest.toEvent(id);
        Event event = updateEventUseCase.update(eventUpdate);
        return ResponseEntity.ok(EventResponse.fromEvent(event));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteEventById(@PathVariable("id") UUID id){
        removeEventUseCase.remove(id);
        return ResponseEntity.ok(id);
    }
}
