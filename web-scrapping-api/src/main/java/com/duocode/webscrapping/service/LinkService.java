package com.duocode.webscrapping.service;

import com.duocode.webscrapping.model.Link;
import java.util.List;

public interface LinkService {

    List<Link> getLinks();

    List<Link> getLinksByUser(long userId);

    List<Link> getLinksContainingText(String text);

    Link saveLink(Link link);

    void deleteLink(Link link);

    Link getLinkById(long id);

}
