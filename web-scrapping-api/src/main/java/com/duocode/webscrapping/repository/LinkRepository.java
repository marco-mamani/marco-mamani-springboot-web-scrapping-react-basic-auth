package com.duocode.webscrapping.repository;

import com.duocode.webscrapping.model.Link;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

    List<Link> findByUrlContainingOrDescriptionContainingIgnoreCaseOrderByUrl(String url, String description);

    List<Link> findAllByUserId(Long userId);

}
