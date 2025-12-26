package com.mcquaids.actions;

import java.util.HashMap;
import java.util.Map;

import com.mcquaids.service.interfaces.IUserService;
import com.opensymphony.xwork2.ActionSupport;


public class BaseAppActionSupport extends ActionSupport { 

    private static final long serialVersionUID = 1L; 
    
    protected IUserService userService ; 
    
    protected Map<String, String> errors = new HashMap<>();
    
    public Map<String, String> getErrors() {
        return errors; 
    }

} 

