package CorpFood.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private String duration;
    private String email;

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
        this.duration = getDuration();
        this.email = email;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm:ss");
        this.duration = String.format(LocalTime.parse(getExpirationTime()).atDate(LocalDate.now()).toString(), formatter);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}