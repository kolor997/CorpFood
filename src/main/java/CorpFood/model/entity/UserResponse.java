package CorpFood.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class UserResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String yourOrder;
    private BigDecimal price;

    @OneToOne
    private User user;

    public UserResponse() {
    }

    public UserResponse(User user, String yourOrder, BigDecimal price) {
        this.user = user;
        this.yourOrder = yourOrder;
        this.price = price;
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
}
