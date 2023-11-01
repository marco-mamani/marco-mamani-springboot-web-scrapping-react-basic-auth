package com.duocode.webscrapping.rest;

import com.duocode.webscrapping.config.SwaggerConfig;
import com.duocode.webscrapping.mapper.LinkDetailMapper;
import com.duocode.webscrapping.mapper.LinkMapper;
import com.duocode.webscrapping.model.Link;
import com.duocode.webscrapping.model.LinkDetail;
import com.duocode.webscrapping.model.User;
import com.duocode.webscrapping.rest.dto.CreateLinkRequest;
import com.duocode.webscrapping.rest.dto.LinkDetailDto;
import com.duocode.webscrapping.rest.dto.LinkDto;
import com.duocode.webscrapping.service.LinkDetailService;
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
@RequestMapping("/api/detail")
public class LinkDetailController {

    private final LinkDetailService linkDetailService;
    private final LinkDetailMapper linkDetailMapper;
    private final UserService userService;

    @Operation(security = {@SecurityRequirement(name = SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping("/link/{id}")
    public List<LinkDetailDto> getLinkDetailsByLink(@PathVariable String id) {
        List<LinkDetail> linkDetails = linkDetailService.getLinkDetailByLinkId(Long.parseLong(id));
        return linkDetails.stream()
                .map(linkDetailMapper::toLinkDetailDto)
                .collect(Collectors.toList());
    }

}
