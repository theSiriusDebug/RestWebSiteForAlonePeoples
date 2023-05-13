package com.example.RestWebSiteForAlonePeoples.controllers;

import com.example.RestWebSiteForAlonePeoples.models.User;
import com.example.RestWebSiteForAlonePeoples.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/")
    public User createStory(@RequestBody User newUser){
        return userRepository.save(newUser);
    }
    @GetMapping("/{id}")
    public User getStoryById(@PathVariable("id") long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User id with id " + id + " not found."));
    }
    @PostMapping("/{user_id}/like")
    public void likeUser(@PathVariable("user_id") long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User id with id " + userId + " not found."));

        user.setLikes(user.getLikes() + 1);
        userRepository.save(user);
    }
}
