package CorpFood.model.service.impl;

import CorpFood.model.dto.CreateContentDTO;
import CorpFood.model.dto.OfferDTO;
import CorpFood.model.dto.UserResponseDTO;
import CorpFood.model.entity.Content;
import CorpFood.model.entity.Offer;
import CorpFood.model.entity.UserResponse;
import CorpFood.model.repository.ContentRepository;
import CorpFood.model.service.ContentService;
import CorpFood.model.service.OfferService;
import CorpFood.model.service.UserResponseService;
import com.google.common.base.Functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

@Service
public class ContentServiceImpl implements ContentService {

    private ContentRepository contentRepository;
    private OfferService offerService;
    private UserResponseService userResponseService;

    @Autowired
    public ContentServiceImpl(ContentRepository contentRepository, UserResponseService userResponseService, OfferService offerService) {
        this.contentRepository = contentRepository;
        this.userResponseService = userResponseService;
        this.offerService = offerService;
    }

    @Override
    public Content findOneById(Long id) {
        return contentRepository.findOne(id);
    }

    @Override
    public Set<Content> findAll() {
        return new HashSet<>(contentRepository.findAll());
    }

    @Override
    public Content createContent(CreateContentDTO createContentDTO) {
        Content content = new Content();
        content.setContentTime(createContentDTO.getContentTime());
        content.setOfferOpen(createContentDTO.getOfferOpen());
        content.setSumPrice(createContentDTO.getSumPrice());

        return contentRepository.save(content);
    }

    @Override
    public void deleteContent(Long id) {
        contentRepository.delete(id);
    }

    @Override
    public Map<String, Set<UserResponseDTO>> getAllFoodOrder() {
        List<Offer> listedOffers = offerService.findActiveOffers();

        Set<OfferDTO> listedOfferDTO = listedOffers.stream()
                .map(OfferDTO::new)
                .collect(toSet());

        return listedOfferDTO.stream()
                .flatMap(o -> o.getUserResponses().stream())
                .map(UserResponseDTO::new)
                .collect(groupingBy(UserResponseDTO::getRestaurant, toSet()));

//        listedOfferDTO.stream().forEachOrdered(o -> result.put(o, o.getUserResponses()));



//        userResponses.forEach(b -> userResponseDTOS.add(new UserResponseDTO(b)));
//        Set<OfferDTO> offerDTOS = new HashSet<>();
//        Set<Offer> offers = offerService.findAll();
//        offers.forEach(c -> offerDTOS.add(new OfferDTO(c)));
//        for (OfferDTO offerDTO : offerDTOS) {
//            Set<UserResponseDTO> temp = new HashSet<>();
//            for (UserResponseDTO userResponseDTO : userResponseDTOS) {
//                if (offerDTO.getId().equals(userResponseDTO.getOfferID())) { //warunek czas
//                    temp.add(userResponseDTO);
//                }
//            }
//            result.put(offerDTO, temp);
//        }
//        return result;
    }

    public Sort sortedBy() {
        return new Sort(Sort.Direction.ASC, "restaurant", "login");
    }

    public boolean isInSeasion() {
        return true;
    }

    public BigDecimal getAllPrices() {

        Set<BigDecimal> temp = new HashSet<>();

        Set<UserResponse> all = userResponseService.findAll();

        all.forEach(p -> temp.add(p.getPrice()));

        return temp.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
