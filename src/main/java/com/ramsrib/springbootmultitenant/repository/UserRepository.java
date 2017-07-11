package com.ramsrib.springbootmultitenant.repository;

import com.ramsrib.springbootmultitenant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
