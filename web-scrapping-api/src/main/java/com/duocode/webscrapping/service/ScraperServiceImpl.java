package com.duocode.webscrapping.service;

import com.duocode.webscrapping.model.Link;
import com.duocode.webscrapping.model.LinkDetail;
import com.duocode.webscrapping.model.User;
import com.duocode.webscrapping.repository.LinkRepository;
import com.duocode.webscrapping.rest.dto.ResponseDTO;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScraperServiceImpl implements ScraperService {

   @Value("#{'${website.urls}'.split(',')}")
   List<String> urls;

   private final LinkService linkService;
   private final LinkDetailService linkDetailService;

   @Override
   public Set<ResponseDTO> getVehicleByModel(String vehicleModel) {

      Set<ResponseDTO> responseDTOS = new HashSet<>();

      for (String url: urls) {

         if (url.contains("ikman")) {

            extractDataFromIkman(responseDTOS, url + vehicleModel);
         } else if (url.contains("riyasewana")) {
            extractDataFromRiyasewana(responseDTOS, url + vehicleModel);
         }

      }

      return responseDTOS;
   }

   @Override
   public Set<ResponseDTO> scrapLink(Link link) {
      Set<ResponseDTO> responseDTOS = new HashSet<>();
      extractDataFromUrl(responseDTOS, link);
      return responseDTOS;
   }

   @Override
   public Set<ResponseDTO> scrapWebSiteUrl(String url) {
      return null;
   }

   private void extractDataFromUrl(Set<ResponseDTO> responseDTOS, Link savedLink) {
      try {
         Document doc = Jsoup.connect(savedLink.getUrl()).get();
         Elements links = doc.select("a[href]");
         Elements media = doc.select("[src]");
         Elements imports = doc.select("link[href]");
         int totalLinks = links.size() + media.size() + imports.size();

         Link linkFromUrl = updateLinkObject(savedLink, totalLinks);
         print("\nMedia: (%d)", media.size());
         for (Element src : media) {
            LinkDetail linkDetail = null;
            if (src.normalName().equals("img")) {
               print(" * %s: <%s> %sx%s (%s)",
                   src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                   trim(src.attr("alt"), 20));
               linkDetail = createLinkDetail(linkFromUrl, "Media", src.tagName(), src.attr("abs:src"), "size: " + src.attr("width") + "x" + src.attr("height"));
            } else {
               print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
               linkDetail = createLinkDetail(linkFromUrl, "Media", src.tagName(), src.attr("abs:src"), "");
            }

            ResponseDTO responseDTO = new ResponseDTO();
            if (linkDetail != null) {
               responseDTO.setTitle(linkDetail.getInfo());
               responseDTO.setUrl(linkDetail.getUrl());
            }
            if (responseDTO.getUrl() != null) responseDTOS.add(responseDTO);
         }

         print("\nImports: (%d)", imports.size());
         for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
            LinkDetail linkDetail = createLinkDetail(linkFromUrl, "Imports", link.tagName(), link.attr("abs:src"), link.attr("rel"));
            ResponseDTO responseDTO = new ResponseDTO();
            if (linkDetail != null) {
               responseDTO.setTitle(linkDetail.getInfo());
               responseDTO.setUrl(linkDetail.getUrl());
            }
            if (responseDTO.getUrl() != null) responseDTOS.add(responseDTO);
         }

         print("\nLinks: (%d)", links.size());
         for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
            LinkDetail linkDetail = createLinkDetail(linkFromUrl, "Links", link.tagName(), link.attr("abs:src"), trim(link.text(), 35));
            ResponseDTO responseDTO = new ResponseDTO();
            if (linkDetail != null) {
               responseDTO.setTitle(linkDetail.getInfo());
               responseDTO.setUrl(linkDetail.getUrl());
            }
            if (responseDTO.getUrl() != null) responseDTOS.add(responseDTO);
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   private Link updateLinkObject(Link link, int totalLinks) {
      link.setLinkCount(totalLinks);
      return linkService.saveLink(link);
   }

   private LinkDetail createLinkDetail(Link linkFromUrl, String media, String tagName, String attrTag, String otherInfo) {
      LinkDetail linkDetail = new LinkDetail(media + ": " + tagName, attrTag, otherInfo, linkFromUrl);
      return linkDetailService.saveLinkDetail(linkDetail);
   }

   private static void print(String msg, Object... args) {
      System.out.println(String.format(msg, args));
   }

   private static String trim(String s, int width) {
      if (s.length() > width) {
         return s.substring(0, width-1) + ".";
      } else {
         return s;
      }
   }

   private void extractDataFromRiyasewana(Set<ResponseDTO> responseDTOS, String url) {

      try {
         Document document = Jsoup.connect(url).get();
         Element element = document.getElementById("content");

         Elements elements = element.getElementsByTag("a");

         for (Element ads: elements) {
            ResponseDTO responseDTO = new ResponseDTO();

            if (!StringUtils.isEmpty(ads.attr("title")) ) {
               responseDTO.setTitle(ads.attr("title"));
               responseDTO.setUrl(ads.attr("href"));
            }
            if (responseDTO.getUrl() != null) responseDTOS.add(responseDTO);
         }
      } catch (IOException ex) {
         ex.printStackTrace();
      }
   }

   private void extractDataFromIkman(Set<ResponseDTO> responseDTOS, String url) {
      try {
         Document document = Jsoup.connect(url).get();
         Element element = document.getElementsByClass("list--3NxGO").first();

         Elements elements = element.getElementsByTag("a");

         for (Element ads: elements) {

            ResponseDTO responseDTO = new ResponseDTO();

            if (StringUtils.isNotEmpty(ads.attr("href"))) {
               responseDTO.setTitle(ads.attr("title"));
               responseDTO.setUrl("https://ikman.lk"+ ads.attr("href"));
            }
            if (responseDTO.getUrl() != null) responseDTOS.add(responseDTO);

         }
      } catch (IOException ex) {
         ex.printStackTrace();
      }
   }

}

