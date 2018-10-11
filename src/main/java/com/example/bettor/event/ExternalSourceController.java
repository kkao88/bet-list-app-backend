package com.example.bettor.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class ExternalSourceController {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/loadEvents")
    public void loadEvents(@RequestParam(value="type", defaultValue="nfl") String type) {
        String resourceURL = "https://api-secure.sports.yahoo.com/v1/editorial/s/scoreboard?lang=en-US&region=US&tz=America%2FChicago&ysp_redesign=1&ysp_platform=desktop&leagues=nfl&week=6&season=current&sched_states=2&v=2&ysp_enable_last_update=1";

        ResponseEntity<String> response
                = restTemplate().getForEntity(resourceURL, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = mapper.readTree(response.getBody());
            //JsonNode games = root.path("service.scoreboard.games");
            System.out.println(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
