package CorpFood.model.service;

import CorpFood.model.dto.CreateOfferDTO;
import CorpFood.model.entity.Offer;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OfferService {

    Offer findOneById(Long id);

    Set<Offer> findAll();

    void deleteOffer(Long id);

    Offer createOffer(CreateOfferDTO createOfferDTO);

    List<Offer> findActiveOffers();

//    Map<Offer, Long> offerDuration();
}
