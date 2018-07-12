package CorpFood.model.repository;

import CorpFood.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    Set<Offer> findAllByCreationTimeAfter(LocalDateTime localDateTime);
}
