package com.world.hello.controller;

import com.world.hello.entity.dao.User;
import com.world.hello.entity.pojo.UserParam;
import com.world.hello.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public List<User> getUserList() {
    return userService.getUserList();
  }

  @PostMapping
  public User addUser(
      @RequestBody UserParam user
  ) {
    return userService.addUser(user);
  }
}
