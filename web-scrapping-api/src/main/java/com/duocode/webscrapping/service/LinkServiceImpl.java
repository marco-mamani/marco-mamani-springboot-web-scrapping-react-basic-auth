package com.duocode.webscrapping.service;

import com.duocode.webscrapping.exception.LinkNotFoundException;
import com.duocode.webscrapping.model.Link;
import com.duocode.webscrapping.repository.LinkRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;

    @Override
    public List<Link> getLinks() {
        return linkRepository.findAll();
    }

    @Override
    public List<Link> getLinksByUser(long userId) {
        return linkRepository.findAllByUserId(userId);
    }

    @Override
    public List<Link> getLinksContainingText(String text) {
        return linkRepository.findByUrlContainingOrDescriptionContainingIgnoreCaseOrderByUrl(text, text);
    }

    @Override
    public Link saveLink(Link link) {
        return linkRepository.save(link);
    }

    @Override
    public void deleteLink(Link link) {
        linkRepository.delete(link);
    }

    @Override
    public Link getLinkById(long id) {
        return linkRepository.findById(id)
            .orElseThrow(() -> new LinkNotFoundException(String.format("Link with id %s not found", id)));    }
}
