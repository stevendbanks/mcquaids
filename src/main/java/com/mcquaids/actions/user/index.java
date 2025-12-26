package com.mcquaids.actions.user;

import java.util.List;

import com.mcquaids.model.User;




public class index extends BaseUserAction  {

    private static final long serialVersionUID = 1L; 
    private List<User> users;


  @Override 
  public String execute() {
       users = this.userService.getAll();
       return INPUT;
  }

  public List<User> getUsers() {
       return this.users;
  }

  public void setUsers(List<User> users ) {
      this.users=users;
  }



}

