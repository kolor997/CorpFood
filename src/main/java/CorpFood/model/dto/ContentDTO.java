
package CorpFood.model.dto;

import CorpFood.model.entity.Content;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ContentDTO {

    private Long id;

    private LocalDateTime contentTime;
    private BigDecimal sumPrice;
    private Boolean isOfferOpen = false;

    public ContentDTO() {
    }

    public ContentDTO(Content content) {
        this.id = content.getId();
        this.contentTime = content.getContentTime();
        this.sumPrice = content.getSumPrice();
        this.isOfferOpen = content.getOfferOpen();
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
