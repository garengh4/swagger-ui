package com.world.hello.service;

import com.world.hello.entity.dao.User;
import com.world.hello.entity.pojo.UserParam;
import com.world.hello.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> getUserList() {
    return userRepository.findAll();
  }

  public User addUser(UserParam param) {

    User user = new User();

    user.setName(param.getName());
    user.setSalary(param.getSalary());

    return userRepository.save(user);
  }
}
