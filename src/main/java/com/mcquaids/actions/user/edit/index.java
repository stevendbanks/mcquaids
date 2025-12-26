package com.mcquaids.actions.user.edit;

import com.mcquaids.actions.user.BaseUserAction;
import com.mcquaids.model.User;

public class index extends BaseUserAction  {

    private static final long serialVersionUID = 1L; 
    private User user;
    private int id;

  @Override 
  public String execute() {
       user = this.userService.edit(id);
       return INPUT;
  }

  public User getUser() {
       return this.user;
  }

  public void setUser(User user) {
      this.user=user;
  }

  public int getId() {
       return this.id;
  }

  public void setId(int id) {
      this.id=id;
  }

}

