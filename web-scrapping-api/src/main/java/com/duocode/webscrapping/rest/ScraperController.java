package com.duocode.webscrapping.rest;

import com.duocode.webscrapping.config.SwaggerConfig;
import com.duocode.webscrapping.model.Link;
import com.duocode.webscrapping.model.User;
import com.duocode.webscrapping.rest.dto.CreateLinkRequest;
import com.duocode.webscrapping.rest.dto.ResponseDTO;
import com.duocode.webscrapping.service.LinkService;
import com.duocode.webscrapping.service.ScraperService;
import com.duocode.webscrapping.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/scrap")
public class ScraperController extends BaseController {

   private final LinkService linkService;
   private final UserService userService;

   @Autowired
   ScraperService scraperService;

   @Operation(security = {@SecurityRequirement(name = SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME)})
   @GetMapping(path = "/{vehicleModel}")
   public Set<ResponseDTO> getVehicleByModel(@PathVariable String vehicleModel) {
      return scraperService.getVehicleByModel(vehicleModel);
   }

   @Operation(security = {@SecurityRequirement(name = SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME)})
   @GetMapping("/link/{id}")
   public Set<ResponseDTO> scrapLink(@PathVariable String id) {
      Link link = linkService.getLinkById(Long.parseLong(id));
      if (link != null) {
         return scraperService.scrapLink(link);
      }
      return Collections.emptySet();
   }

   @Operation(security = {@SecurityRequirement(name = SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME)})
   @GetMapping("/url/{url}")
   public Set<ResponseDTO> scrapUrl(@PathVariable String url, Principal principal) {
      Link link = null;
      try {
         Optional<User> user = userService.getUserByUsername(principal.getName());
         if (user.isPresent()) {
            link = linkService.saveLink(new Link(url, url, 0, user.get()));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      if (link != null) {
         return scraperService.scrapLink(link);
      }
      return Collections.emptySet();
   }

   @Operation(security = {@SecurityRequirement(name = SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME)})
   @ResponseStatus(HttpStatus.CREATED)
   @PostMapping
   public Set<ResponseDTO> scrapWebSiteUrl(@Valid @RequestBody CreateLinkRequest createLinkRequest) {
      return scraperService.scrapWebSiteUrl(createLinkRequest.getUrl());
   }


}
