package com.mcquaids.actions.user.edit;

import java.util.List;

import com.mcquaids.actions.user.BaseUserAction;
import com.mcquaids.model.User;



public class Save extends BaseUserAction  {

    private static final long serialVersionUID = 1L; 
    private User user;
    private List<User> users;
  @Override 
  public String execute() {
      this.userService.save(user);

      users = this.userService.getAll();

        addActionMessage("WORKING");

       return INPUT;
  }

  public List<User> getUsers() {
       return this.users;
  }

  public void setUsers(List<User> users ) {
      this.users=users;
  }

  public User getUser() {
       return this.user;
  }

  public void setUser(User user) {
      this.user=user;
  }

}

