package com.duocode.webscrapping.runner;

import com.duocode.webscrapping.model.Link;
import com.duocode.webscrapping.service.LinkService;
import com.duocode.webscrapping.service.UserService;
import com.duocode.webscrapping.model.User;
import com.duocode.webscrapping.security.WebSecurityConfig;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final UserService userService;
    private final LinkService linkService;

    @Override
    public void run(String... args) {
        if (!userService.getUsers().isEmpty()) {
            return;
        }
        USERS.forEach(userService::saveUser);
        LINKS.forEach(linkService::saveLink);
        Optional<User> user = userService.getUserByUsername("user");
        List<Link> links = linkService.getLinks();
        if (user.isPresent()) {
            for (Link link : links) {
                link.setUser(user.get());
                linkService.saveLink(link);
            }
        }
        log.info("Database initialized");
    }

    private static final List<User> USERS = Arrays.asList(
            new User("admin", "admin", "Admin", "admin@mycompany.com", WebSecurityConfig.ADMIN),
            new User("user", "user", "User", "user@mycompany.com", WebSecurityConfig.USER)
    );

    private static final List<Link> LINKS = Arrays.asList(
            new Link("https://www.ebay.com", "Ebay products", 14),
            new Link("https://www.amazon.com", "Amazon products", 35),
            new Link("http://www.example.org", "Example site", 3),
            new Link("https://www.marco.com", "marc products", 3),
            new Link("https://www.rahul.com", "rahul products", 13),
            new Link("https://www.kahan.com", "kahan products", 52),
            new Link("https://www.fluffy.com", "fluffy products", 23),
            new Link("https://www.escamilla.com", "escamilla products", 21),
            new Link("https://www.chistosos.com", "chistosos products", 31),
            new Link("https://www.2xxpoi.com", "2xxpool products", 5)
    );

}
