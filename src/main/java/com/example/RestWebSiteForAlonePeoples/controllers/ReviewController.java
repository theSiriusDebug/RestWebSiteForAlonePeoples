package com.example.RestWebSiteForAlonePeoples.controllers;

import com.example.RestWebSiteForAlonePeoples.models.Review;
import com.example.RestWebSiteForAlonePeoples.models.User;
import com.example.RestWebSiteForAlonePeoples.repositories.ReviewRepository;
import com.example.RestWebSiteForAlonePeoples.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private EntityManager entityManager;
    private UserRepository userRepository;

    @Autowired
    public ReviewController(EntityManager entityManager, UserRepository userRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public List<User> getAllStories(){
        return userRepository.findAll();
    }
    @GetMapping("/{user_id}")
    public List<Review> getReviewByUserId(@PathVariable Long user_id) {
        TypedQuery<Review> query = entityManager.createQuery(
                "SELECT p FROM Review p WHERE p.user.id = :user_id", Review.class);
        query.setParameter("user_id", user_id);

        return query.getResultList();
    }
    @PostMapping("/{user_id}")
    public Review createReviewForUserStory(@PathVariable("user_id") long userId, @RequestBody Review newReview) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User id with id " + userId + " not found."));

        newReview.setUser(user);
        user.getReviews().add(newReview);
        userRepository.save(user);

        return newReview;
    }
}

