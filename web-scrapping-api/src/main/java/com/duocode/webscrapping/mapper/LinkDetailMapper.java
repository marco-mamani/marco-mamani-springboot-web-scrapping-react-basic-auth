package com.duocode.webscrapping.mapper;

import com.duocode.webscrapping.model.Link;
import com.duocode.webscrapping.model.LinkDetail;
import com.duocode.webscrapping.model.User;
import com.duocode.webscrapping.rest.dto.CreateLinkRequest;
import com.duocode.webscrapping.rest.dto.LinkDetailDto;
import com.duocode.webscrapping.rest.dto.LinkDto;

public interface LinkDetailMapper {

    LinkDetailDto toLinkDetailDto(LinkDetail linkDetail);
}