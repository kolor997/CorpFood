package CorpFood.model.service;

import CorpFood.model.dto.CreateContentDTO;
import CorpFood.model.dto.UserResponseDTO;
import CorpFood.model.entity.Content;

import java.util.Map;
import java.util.Set;

public interface ContentService {

    Content findOneById(Long id);

    Set<Content> findAll();

    Content createContent(CreateContentDTO createContentDTO);

    void deleteContent(Long id);

    Map<String, Set<UserResponseDTO>> getAllFoodOrder();
}
