package com.mcquaids.service.interfaces;

import java.util.List;

import com.mcquaids.model.User;

public interface IUserService {

  int add(User user);

  User edit(int id);

  User getBySessionId(String sessionId);

  int save(User user);

  List<User> getAll();

}
