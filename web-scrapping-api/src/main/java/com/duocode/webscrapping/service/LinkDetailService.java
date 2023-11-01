package com.duocode.webscrapping.service;

import com.duocode.webscrapping.model.Link;
import com.duocode.webscrapping.model.LinkDetail;
import java.util.List;

public interface LinkDetailService {

    List<LinkDetail> getLinkDetailByLinkId(long linkId);

    int countLinkDetailByLinkId(long linkId);

    LinkDetail saveLinkDetail(LinkDetail linkDetail);

    void deleteLinkDetail(LinkDetail linkDetail);

    LinkDetail getLinkDetailById(long id);

}
