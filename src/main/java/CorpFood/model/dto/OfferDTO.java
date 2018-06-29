package CorpFood.model.dto;

import CorpFood.model.entity.Offer;

public class OfferDTO {

    private Long id;
    private String restaurant;
    private String URL;
    private String description;

    public OfferDTO(Offer offer) {
        this.id = offer.getId();
        this.restaurant = offer.getRestaurant();
        this.URL = offer.getURL();
        this.description = offer.getDescription();
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
