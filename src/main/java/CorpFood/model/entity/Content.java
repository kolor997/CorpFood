package CorpFood.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime contentTime;
    private BigDecimal sumPrice;
    private Boolean isOfferOpen = false;

    public Content() {
    }

    public Content(LocalDateTime contentTime, BigDecimal sumPrice, Boolean isOfferOpen) {
        this.contentTime = contentTime;
        this.sumPrice = sumPrice;
        this.isOfferOpen = isOfferOpen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
