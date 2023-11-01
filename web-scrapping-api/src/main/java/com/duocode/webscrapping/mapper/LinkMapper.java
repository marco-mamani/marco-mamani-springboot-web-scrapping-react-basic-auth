package com.duocode.webscrapping.mapper;

import com.duocode.webscrapping.model.Link;
import com.duocode.webscrapping.model.User;
import com.duocode.webscrapping.rest.dto.CreateLinkRequest;
import com.duocode.webscrapping.rest.dto.LinkDto;

public interface LinkMapper {

    Link toLink(CreateLinkRequest createLinkRequest, User user);

    LinkDto toLinkDto(Link link);
}