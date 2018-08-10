package CorpFood.model.entity;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class UserResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String yourOrder;
    private BigDecimal price;

    @OneToOne
    private User user;

    @ManyToOne
    private Offer offer;

    public UserResponse() {
    }

    public UserResponse(User user, String yourOrder, BigDecimal price, Offer offer) {
        this.user = user;
        this.yourOrder = yourOrder;
        this.price = price;
        this.offer = offer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public String getYourOrder() {
        return yourOrder;
    }

    public void setYourOrder(String yourOrder) {
        this.yourOrder = yourOrder;
    }
}
