package com.example.RestWebSiteForAlonePeoples.repositories;

import com.example.RestWebSiteForAlonePeoples.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
