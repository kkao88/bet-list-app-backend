package com.example.bettor;

import com.example.bettor.event.Event;
import com.example.bettor.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;

@SpringBootApplication
public class BettorApplication {
	public static void main(String[] args) {
		SpringApplication.run(BettorApplication.class, args);
	}
}
