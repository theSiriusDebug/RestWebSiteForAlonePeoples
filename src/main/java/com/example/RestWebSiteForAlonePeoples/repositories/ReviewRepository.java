package com.example.RestWebSiteForAlonePeoples.repositories;

import com.example.RestWebSiteForAlonePeoples.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
