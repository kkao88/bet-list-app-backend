package com.example.bettor.event;

import com.example.bettor.bettor.Bettor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class EventController {
    private final EventRepository repository;

    public EventController(EventRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/events")
    public List<Event> getAllEvents(@RequestParam(value="type", defaultValue="NFL") String type) {
        return repository.findByTypeAndDateTimeGreaterThanOrderByDateTime(type, new Date());
    }

    @GetMapping("/event/{eventId}")
    public Event getEventById(@PathVariable String eventId) {
        return repository.findById(eventId).get();
    }

    @PutMapping("/event")
    public void addNewEvent(@RequestBody Event event) {
        if (StringUtils.isEmpty(event.getId())) {
            event.setHomeTeamBettors(new ArrayList<>());
            event.setAwayTeamBettors(new ArrayList<>());
            event.setOverBettors(new ArrayList<>());
            event.setUnderBettors(new ArrayList<>());
        }

        repository.save(event);
    }

    @PostMapping("/event/{eventId}/home")
    public void addHomeTeamBettorToEvent(@PathVariable String eventId, @RequestBody Bettor bettor) {
        Event event = repository.findById(eventId).get();
        event.addHomeTeamBettor(bettor.name);
        repository.save(event);
    }

    @PostMapping("/event/{eventId}/away")
    public void addAwayTeamBettorToEvent(@PathVariable String eventId, @RequestBody Bettor bettor) {
        Event event = repository.findById(eventId).get();
        event.addAwayTeamBettor(bettor.name);
        repository.save(event);
    }

    @PostMapping("/event/{eventId}/over")
    public void addOverBettorToEvent(@PathVariable String eventId, @RequestBody Bettor bettor) {
        Event event = repository.findById(eventId).get();
        event.addOverBettor(bettor.name);
        repository.save(event);
    }

    @PostMapping("/event/{eventId}/under")
    public void addUnderBettorToEvent(@PathVariable String eventId, @RequestBody Bettor bettor) {
        Event event = repository.findById(eventId).get();
        event.addUnderBettor(bettor.name);
        repository.save(event);
    }

    @DeleteMapping("/event/{eventId}/home")
    void deleteNameFromHomeBet(@PathVariable String eventId, @RequestParam(value="name") String name) {
        Event event = repository.findById(eventId).get();
        event.removeHomeTeamBettor(name);
        repository.save(event);
    }

    @DeleteMapping("/event/{eventId}/away")
    void deleteNameFromAwayBet(@PathVariable String eventId, @RequestParam(value="name") String name) {
        Event event = repository.findById(eventId).get();
        event.removeAwayTeamBettor(name);
        repository.save(event);
    }

    @DeleteMapping("/event/{eventId}/over")
    void deleteNameFromOverBet(@PathVariable String eventId, @RequestParam(value="name") String name) {
        Event event = repository.findById(eventId).get();
        event.removeOverBettor(name);
        repository.save(event);
    }

    @DeleteMapping("/event/{eventId}/under")
    void deleteNameFromUnderBet(@PathVariable String eventId, @RequestParam(value="name") String name) {
        Event event = repository.findById(eventId).get();
        event.removeUnderBettor(name);
        repository.save(event);
    }

    @DeleteMapping("/event/{eventId}")
    void deleteEvent(@PathVariable String eventId) {
        Event event = repository.findById(eventId).get();
        repository.delete(event);
    }
}
