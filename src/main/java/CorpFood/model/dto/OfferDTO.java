package CorpFood.model.dto;

import CorpFood.model.entity.Offer;
import CorpFood.model.entity.UserResponse;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class OfferDTO {

    private Long id;
    private String restaurant;
    private String URL;
    private String description;
    private LocalDateTime creationTime;
    private String expirationTime;
    private Set<UserResponse> userResponses;
    private String duration;

    public OfferDTO(Offer offer) {
        this.id = offer.getId();
        this.restaurant = offer.getRestaurant();
        this.URL = offer.getURL();
        this.description = offer.getDescription();
        this.creationTime = offer.getCreationTime();
        this.expirationTime = offer.getExpirationTime();
        this.userResponses = offer.getUserResponses();
        this.duration = offer.getDuration();
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

    public void setUserResponses(Set<UserResponse> userResponses) {
        this.userResponses = userResponses;
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
}
