package com.ramsrib.springbootmultitenant;

import com.ramsrib.springbootmultitenant.model.User;
import com.ramsrib.springbootmultitenant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController("/api/users")
public class UserController /*implements ApplicationRunner*/ {

//  @Autowired
//  private UserRepository userRepository;
//
//
//  @Override
//  public void run(ApplicationArguments applicationArguments) throws Exception {
//    System.out.println("SRIRAM");
//    userRepository.save(new User(null,"sriram", "Sri", "Ram", "tenant1"));
//  }
//
//  // REST API's
//
//  @GetMapping
//  public List<User> listUsers() {
//    return userRepository.findAll();
//  }
//
//  @PostMapping
//  public User createUser(User user) {
//    return userRepository.save(user);
//  }
//
//  @GetMapping("/{id}")
//  public Optional<User> getUser(@PathVariable("id") String userId) {
//    return userRepository.findById(userId);
//  }
//
//  @DeleteMapping("/{id}")
//  public void deleteUser(@PathVariable("id") String userId) {
//    userRepository.deleteById(userId);
//  }

}
