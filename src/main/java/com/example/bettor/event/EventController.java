package com.example.bettor.event;

import com.example.bettor.bettor.Bettor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @PostMapping("/massEntry/{eventType}")
    public void massEntry(@PathVariable String eventType, @RequestBody String text, @RequestParam(value="date") String date) {
        try {
            int linesPerGame = 18;
            String decodedText = java.net.URLDecoder.decode(text, "UTF-8");
            String[] lines = decodedText.split("\r\n|\r|\n");
            int numberOfGames = (lines.length - 1)/linesPerGame;
            for (int i = 0; i < numberOfGames; i++){
                Event event = new Event();
                event.setAwayTeam(lines[i*linesPerGame + 1]);
                event.setHomeTeam(lines[i*linesPerGame + 2]);
                String dateString = date+" "+lines[i*linesPerGame + 3];
                SimpleDateFormat dateFormat = new SimpleDateFormat("M d y h:mm a z");
                event.setDateTime(dateFormat.parse(dateString));
                String trimmedLine = StringUtils.trimWhitespace(lines[i*linesPerGame + 4]);
                event.setLine(trimmedLine.substring(0, trimmedLine.length() - 4));
                event.setTotal(lines[i*linesPerGame + 6]);
                event.setType(eventType);
                if (event.isValidEvent()){
                    this.addNewEvent(event);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
