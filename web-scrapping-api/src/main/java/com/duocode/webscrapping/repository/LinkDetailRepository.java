package com.duocode.webscrapping.repository;

import com.duocode.webscrapping.model.LinkDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkDetailRepository extends JpaRepository<LinkDetail, Long> {

   List<LinkDetail> findLinkDetailsByLinkId(long linkId);

   int countAllByLinkId(long linkId);

//   LinkDetail findLinkDetailsById(long id);
}
