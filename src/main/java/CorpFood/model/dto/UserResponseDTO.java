package CorpFood.model.dto;

import CorpFood.model.entity.User;
import CorpFood.model.entity.UserResponse;

import java.math.BigDecimal;

public class UserResponseDTO {

    private Long id;
    private User user;
    private String yourOrder;
    private BigDecimal price;
    private Long offerID;
    private String restaurant;

    public UserResponseDTO(UserResponse userResponse){
        this.id = userResponse.getId();
        this.user = userResponse.getUser();
        this.price = userResponse.getPrice();
        this.yourOrder = userResponse.getYourOrder();
        this.offerID = userResponse.getOffer().getId();
        this.restaurant = userResponse.getOffer().getRestaurant();
    }

    public String getYourOrder() {
        return yourOrder;
    }

    public void setYourOrder(String yourOrder) {
        this.yourOrder = yourOrder;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public Long getOfferID() {
        return offerID;
    }

    public String getRestaurant() {
        return restaurant;
    }
}
