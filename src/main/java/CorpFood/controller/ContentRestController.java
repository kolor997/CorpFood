package CorpFood.controller;

import CorpFood.model.dto.ContentDTO;
import CorpFood.model.dto.CreateContentDTO;
import CorpFood.model.dto.UserResponseDTO;
import CorpFood.model.entity.Content;
import CorpFood.model.entity.UserResponse;
import CorpFood.model.service.ContentService;
import CorpFood.model.service.UserResponseService;
import CorpFood.model.service.impl.ContentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/corpFood/content")
public class ContentRestController {

    private ContentService contentService;

    @Autowired
    public ContentRestController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/{id}")
    public ContentDTO findById(Long id) {
        Content content = contentService.findOneById(id);
        return new ContentDTO(content);
    }

    @GetMapping
    public Set<ContentDTO> findAll() {
        Set<ContentDTO> result = new HashSet<>();
        Set<Content> all = contentService.findAll();
        all.forEach(c -> result.add(new ContentDTO(c)));
        return result;
    }

    @PostMapping
    public ContentDTO create(@RequestBody CreateContentDTO createContentDTO) {
        return new ContentDTO(contentService.createContent(createContentDTO));
    }
    @DeleteMapping("/{id}")
    public void deleteContent(@PathVariable Long id){
        contentService.deleteContent(id);
    }

}
