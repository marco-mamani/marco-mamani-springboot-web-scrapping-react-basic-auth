package com.duocode.webscrapping.mapper;

import com.duocode.webscrapping.model.Link;
import com.duocode.webscrapping.model.LinkDetail;
import com.duocode.webscrapping.model.User;
import com.duocode.webscrapping.rest.dto.CreateLinkRequest;
import com.duocode.webscrapping.rest.dto.LinkDetailDto;
import com.duocode.webscrapping.rest.dto.LinkDto;
import org.springframework.stereotype.Service;

@Service
public class LinkDetailMapperImpl implements LinkDetailMapper {

    @Override
    public LinkDetailDto toLinkDetailDto(LinkDetail linkDetail) {
        if (linkDetail == null) {
            return null;
        }
        return new LinkDetailDto(linkDetail.getId(), linkDetail.getType(), linkDetail.getUrl(), linkDetail.getInfo());
    }
}
