package CorpFood.model.dto;

import java.math.BigDecimal;

public class CreateUserResponseDTO {

    private String yourOrder;
    private BigDecimal price;

    public CreateUserResponseDTO(){
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
