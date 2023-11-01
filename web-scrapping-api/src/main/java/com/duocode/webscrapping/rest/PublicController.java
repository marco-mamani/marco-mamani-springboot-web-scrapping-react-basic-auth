package com.duocode.webscrapping.rest;

import com.duocode.webscrapping.service.LinkService;
import com.duocode.webscrapping.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/public")
public class PublicController {

    private final UserService userService;
    private final LinkService linkService;

    @GetMapping("/numberOfUsers")
    public Integer getNumberOfUsers() {
        return userService.getUsers().size();
    }

    @GetMapping("/numberOfLinks")
    public Integer getNumberOfLinks() {
        return linkService.getLinks().size();
    }
}
