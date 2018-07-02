package CorpFood.model.dto;

import CorpFood.model.entity.Offer;
import CorpFood.model.entity.UserResponse;

import java.util.Set;

public class OfferDTO {

    private Long id;
    private String restaurant;
    private String URL;
    private String description;
    private Set<UserResponse> userResponses;

    public OfferDTO(Offer offer) {
        this.id = offer.getId();
        this.restaurant = offer.getRestaurant();
        this.URL = offer.getURL();
        this.description = offer.getDescription();
        this.userResponses = offer.getUserResponses();
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
}
