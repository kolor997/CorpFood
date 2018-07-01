package CorpFood.model.service.impl;

import CorpFood.model.dto.CreateOfferDTO;
import CorpFood.model.dto.OfferDTO;
import CorpFood.model.entity.Offer;
import CorpFood.model.repository.OfferRepository;
import CorpFood.model.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OfferServiceImpl implements OfferService {

    private OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public Offer findOneById(Long id) {
        return offerRepository.getOne(id);
    }

    @Override
    public Set<Offer> findAll() {
        return new HashSet<>(offerRepository.findAll());
    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.delete(id);
    }

    @Override
    public Offer createOffer(CreateOfferDTO createOfferDTO) {
        Offer offer = new Offer();
        offer.setRestaurant(createOfferDTO.getRestaurant());
        offer.setURL(createOfferDTO.getURL());
        offer.setDescription(createOfferDTO.getDescription());
        return offerRepository.save(offer);
    }
}