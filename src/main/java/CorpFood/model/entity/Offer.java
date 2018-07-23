package CorpFood.model.entity;

import org.apache.catalina.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String restaurant;
    private String URL;
    private String description;
    private LocalDateTime creationTime = LocalDateTime.now();
    private String expirationTime;

    @OneToMany(mappedBy = "offer")
    private Set<UserResponse> userResponses = new HashSet<>();

    public Offer() {
    }

    public Offer(String restaurant, String URL, String description, String expirationTime) {
        this.expirationTime = expirationTime;
        this.restaurant = restaurant;
        this.URL = URL;
        this.description = description;
        this.creationTime = getCreationTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public Set<UserResponse> getUserResponses() {
        return userResponses;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Long getDuration() {
        Long duration;
        return duration = LocalTime.parse(getExpirationTime()).atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - getCreationTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
