package com.duocode.webscrapping.service;

import com.duocode.webscrapping.exception.LinkDetailNotFoundException;
import com.duocode.webscrapping.model.LinkDetail;
import com.duocode.webscrapping.repository.LinkDetailRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LinkDetailServiceImpl implements LinkDetailService {

    private final LinkDetailRepository linkDetailRepository;

    @Override
    public List<LinkDetail> getLinkDetailByLinkId(long linkId) {
        return linkDetailRepository.findLinkDetailsByLinkId(linkId);
    }

    @Override
    public int countLinkDetailByLinkId(long linkId) {
        return linkDetailRepository.countAllByLinkId(linkId);
    }

    @Override
    public LinkDetail saveLinkDetail(LinkDetail linkDetail) {
        return linkDetailRepository.save(linkDetail);
    }

    @Override
    public void deleteLinkDetail(LinkDetail linkDetail) {
        linkDetailRepository.delete(linkDetail);
    }

    @Override
    public LinkDetail getLinkDetailById(long id) {
        return linkDetailRepository.findById(id)
            .orElseThrow(() -> new LinkDetailNotFoundException(String.format("LinkDetail with id %s not found", id)));
    }

}
