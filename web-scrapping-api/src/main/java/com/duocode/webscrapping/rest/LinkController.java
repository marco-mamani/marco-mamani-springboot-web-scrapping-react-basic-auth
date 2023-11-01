package com.duocode.webscrapping.rest;

import com.duocode.webscrapping.config.SwaggerConfig;
import com.duocode.webscrapping.mapper.LinkMapper;
import com.duocode.webscrapping.model.Link;
import com.duocode.webscrapping.model.User;
import com.duocode.webscrapping.rest.dto.CreateLinkRequest;
import com.duocode.webscrapping.rest.dto.LinkDto;
import com.duocode.webscrapping.service.LinkService;
import com.duocode.webscrapping.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/links")
public class LinkController {

    private final LinkService linkService;
    private final LinkMapper linkMapper;
    private final UserService userService;

    @Operation(security = {@SecurityRequirement(name = SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping
    public List<LinkDto> getLinks(@RequestParam(value = "text", required = false) String text) {
        List<Link> links = (text == null) ? linkService.getLinks() : linkService.getLinksContainingText(text);
        return links.stream()
                .map(linkMapper::toLinkDto)
                .collect(Collectors.toList());
    }

    @Operation(security = {@SecurityRequirement(name = SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping("/user")
    public List<LinkDto> getLinksByUser(Principal principal) {
        Optional<User> user = userService.getUserByUsername(principal.getName());
        List<Link> resultLinks = null;
        if (user.isPresent()) {
            resultLinks = linkService.getLinksByUser(user.get().getId());
        } else {
            resultLinks = linkService.getLinks();
        }
        return resultLinks.stream()
                .map(linkMapper::toLinkDto)
                .collect(Collectors.toList());
    }

    @Operation(security = {@SecurityRequirement(name = SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME)})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public LinkDto createLink(@Valid @RequestBody CreateLinkRequest createLinkRequest, Principal principal) {

        Link link = null;
        try {
            Optional<User> user = userService.getUserByUsername(principal.getName());
            if (user.isPresent()) {
                link = linkMapper.toLink(createLinkRequest, user.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return linkMapper.toLinkDto(linkService.saveLink(link));
    }

    @Operation(security = {@SecurityRequirement(name = SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME)})
    @DeleteMapping("/{id}")
    public LinkDto deleteLink(@PathVariable String id) {
        Link link = linkService.getLinkById(Long.parseLong(id));
        linkService.deleteLink(link);
        return linkMapper.toLinkDto(link);
    }
}
