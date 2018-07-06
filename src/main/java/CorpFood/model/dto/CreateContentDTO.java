package CorpFood.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateContentDTO {

    private LocalDateTime contentTime;
    private BigDecimal sumPrice;
    private Boolean isOfferOpen = false;

    public CreateContentDTO() {
    }

    public LocalDateTime getContentTime() {
        return contentTime;
    }

    public void setContentTime(LocalDateTime contentTime) {
        this.contentTime = contentTime;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Boolean getOfferOpen() {
        return isOfferOpen;
    }

    public void setOfferOpen(Boolean offerOpen) {
        isOfferOpen = offerOpen;
    }
}
