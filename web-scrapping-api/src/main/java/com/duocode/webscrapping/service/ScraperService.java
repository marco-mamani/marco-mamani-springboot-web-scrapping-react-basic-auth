package com.duocode.webscrapping.service;

import com.duocode.webscrapping.model.Link;
import com.duocode.webscrapping.rest.dto.ResponseDTO;
import java.util.Set;

public interface ScraperService {

   Set<ResponseDTO> getVehicleByModel(String vehicleModel);

   Set<ResponseDTO> scrapLink(Link link);

   Set<ResponseDTO> scrapWebSiteUrl(String url);
}
