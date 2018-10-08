package com.example.bettor.event;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {

    @Id
    private String id;

    private String homeTeam;
    private String awayTeam;
    private Date dateTime;
    private String line;
    private String type;
    private String total;

    private List<String> homeTeamBettors;

    private List<String> awayTeamBettors;

    private List<String> overBettors;

    private List<String> underBettors;

    public Event(String awayTeam, String homeTeam, Date dateTime, String type, String line) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.dateTime = dateTime;
        this.line = line;
        this.type = type;
        this.homeTeamBettors = new ArrayList<String>();
        this.awayTeamBettors = new ArrayList<String>();
        this.overBettors = new ArrayList<String>();
        this.underBettors = new ArrayList<String>();
    }

    public Event(String awayTeam, String homeTeam, Date dateTime, String type, String line, String total) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.dateTime = dateTime;
        this.line = line;
        this.type = type;
        this.total = total;
        this.homeTeamBettors = new ArrayList<String>();
        this.awayTeamBettors = new ArrayList<String>();
        this.overBettors = new ArrayList<String>();
        this.underBettors = new ArrayList<String>();
    }

    public Event() {
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public List<String> getHomeTeamBettors() {
        return homeTeamBettors;
    }

    public List<String> getAwayTeamBettors() {
        return awayTeamBettors;
    }

    public List<String> getOverBettors() {
        return overBettors;
    }

    public List<String> getUnderBettors() {
        return underBettors;
    }

    public void setHomeTeamBettors(List<String> homeTeamBettors) {
        this.homeTeamBettors = homeTeamBettors;
    }

    public void setAwayTeamBettors(List<String> awayTeamBettors) {
        this.awayTeamBettors = awayTeamBettors;
    }

    public void setOverBettors(List<String> overBettors) {
        this.overBettors = overBettors;
    }

    public void setUnderBettors(List<String> underBettors) {
        this.underBettors = underBettors;
    }

    public void addHomeTeamBettor (String name) {
        this.homeTeamBettors.add(name);
    }

    public void addAwayTeamBettor (String name) {
        this.awayTeamBettors.add(name);
    }

    public void removeHomeTeamBettor (String name) {
        this.homeTeamBettors.remove(name);
    }

    public void removeAwayTeamBettor (String name) {
        this.awayTeamBettors.remove(name);
    }

    public void addOverBettor (String name) {
        this.overBettors.add(name);
    }

    public void addUnderBettor (String name) {
        this.underBettors.add(name);
    }

    public void removeOverBettor (String name) {
        this.overBettors.remove(name);
    }

    public void removeUnderBettor (String name) {
        this.underBettors.remove(name);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", dateTime=" + dateTime +
                ", line='" + line + '\'' +
                ", type='" + type + '\'' +
                ", total='" + total + '\'' +
                ", homeTeamBettors=" + homeTeamBettors +
                ", awayTeamBettors=" + awayTeamBettors +
                '}';
    }
}