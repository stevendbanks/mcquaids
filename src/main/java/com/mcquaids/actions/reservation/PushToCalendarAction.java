package com.mcquaids.actions.reservation;

import com.mcquaids.service.DispatchActionService;
import com.opensymphony.xwork2.ActionSupport;

public class PushToCalendarAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private String calendarEventLink;
    private Long dispatchActionId;

    @Override
    public String execute() {
    	System.out.println("SDBANKS->PushToCalendarAction.execute() Entered");
        try {
            DispatchActionService svc = new DispatchActionService();

            // The service loads all domain objects, assembles the DTO,
            // and calls GoogleCalendarService internally.
            this.calendarEventLink = svc.pushToCalendar(dispatchActionId);

            addActionMessage("Event created successfully.");
            return SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Failed: " + e.getMessage());
            return ERROR;
        }
    }

    public String getCalendarEventLink() { 
        return calendarEventLink; 
    }

    public Long getDispatchActionId() { 
        return dispatchActionId; 
    }

    public void setDispatchActionId(Long dispatchActionId) { 
        this.dispatchActionId = dispatchActionId; 
    }
}