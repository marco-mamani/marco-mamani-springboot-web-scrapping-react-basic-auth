package com.duocode.webscrapping.mapper;

import com.duocode.webscrapping.model.Link;
import com.duocode.webscrapping.model.User;
import com.duocode.webscrapping.rest.dto.CreateLinkRequest;
import com.duocode.webscrapping.rest.dto.LinkDto;
import org.springframework.stereotype.Service;

@Service
public class LinkMapperImpl implements LinkMapper {

    @Override
    public Link toLink(CreateLinkRequest createLinkRequest, User user) {
        if (createLinkRequest == null || user == null) {
            return null;
        }
        Link link = new Link();
        link.setUrl(createLinkRequest.getUrl());
        link.setDescription(createLinkRequest.getDescription());
        link.setUser(user);
        return link;
    }

    @Override
    public LinkDto toLinkDto(Link link) {
        if (link == null) {
            return null;
        }
        return new LinkDto(link.getId(),link.getUrl(), link.getDescription(), link.getLinkCount());
    }
}
