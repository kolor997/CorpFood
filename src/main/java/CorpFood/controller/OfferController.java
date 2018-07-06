package CorpFood.controller;


import CorpFood.model.dto.CreateOfferDTO;
import CorpFood.model.dto.OfferDTO;
import CorpFood.model.entity.Offer;
import CorpFood.model.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/corpFood/offers")
public class OfferController {

    private OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/{id}")
    public OfferDTO findById(Long id) {
        Offer offer = offerService.findOneById(id);
        return new OfferDTO(offer);
    }

    @GetMapping
    public Set<OfferDTO> findAll() {
        Set<OfferDTO> result = new HashSet<>();

        Set<Offer> all = offerService.findAll();
        all.forEach(b -> result.add(new OfferDTO(b)));

        return result;
    }

    @PostMapping
    public OfferDTO create(@RequestBody CreateOfferDTO createOfferDTO) {
        return new OfferDTO(offerService.createOffer(createOfferDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
    }
}
