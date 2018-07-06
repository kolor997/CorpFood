package CorpFood.model.service.impl;

import CorpFood.model.dto.CreateContentDTO;
import CorpFood.model.dto.UserResponseDTO;
import CorpFood.model.entity.Content;
import CorpFood.model.entity.UserResponse;
import CorpFood.model.repository.ContentRepository;
import CorpFood.model.service.ContentService;
import CorpFood.model.service.UserResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
public class ContentServiceImpl implements ContentService {

    private ContentRepository contentRepository;
    private UserResponseService userResponseService;

    @Autowired
    public ContentServiceImpl(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
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

    public String getAllFoodOrder() {

        StringBuilder sb = new StringBuilder();
        Set<UserResponseDTO> result = new HashSet<>();

        Set<UserResponse> all = userResponseService.findAll();
        all.forEach(b -> result.add(new UserResponseDTO(b)));

        for (UserResponseDTO udto : result) {
            sb.append(udto.getUser().getFirstName() + "\n");
            sb.append(udto.getUser().getLastName() + "\n");
            sb.append(udto.getYourOrder() + "\n");
            sb.append(udto.getPrice().toString() + "\n");
            sb.append("\n");
        }

        return sb.toString();
    }

    public Sort sortedBy(){
        return new Sort(Sort.Direction.ASC, "restaurant", "login");
    }
    public boolean isInSeasion(){
        return true;
    }
    public BigDecimal getAllPrices(UserResponseDTO urdto) {

        Set<BigDecimal> temp = new HashSet<>();

        Set<UserResponse> all = userResponseService.findAll();

        all.forEach(p-> temp.add(urdto.getPrice()));

        return temp.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
