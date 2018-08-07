package CorpFood.model.service.impl;

import CorpFood.model.dto.CreateOfferDTO;
import CorpFood.model.entity.Offer;
import CorpFood.model.repository.OfferRepository;
import CorpFood.model.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

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
/*        offer.setExpirationTime(createOfferDTO.getExpirationTime());
        offer.setDuration(createOfferDTO.getDuration());*/
        return offerRepository.save(offer);
    }

    @Override
    public List<Offer> findActiveOffers() {

        Set<Offer> activeOffers = offerRepository.findAllByCreationTimeAfter(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS));
        return activeOffers.stream()
                .sorted(comparing(Offer::getCreationTime))
                .collect(Collectors.toList());
    }

    //    @Override
//    public Map<Offer, Long> offerDuration(){
//        Map<Offer, Long> offersDurations = new HashMap<>();
//        LocalDate dateNow = LocalDate.now();
//        List<Offer> activeOffers = findActiveOffers();
//        for (Offer o : activeOffers) {
//            LocalDateTime endTime = LocalTime.parse(o.getExpirationTime()).atDate(dateNow);
//            Long offerDuration = endTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - o.getCreationTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//            offersDurations.put(o, offerDuration);
//        }
//        return offersDurations;
//    }

}
