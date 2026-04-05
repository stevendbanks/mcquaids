package com.mcquaids.actions.reservation;

import com.mcquaids.service.DispatchActionService;
import com.opensymphony.xwork2.ActionSupport;

public class RemoveFromCalendarAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private Long dispatchActionId;

    @Override
    public String execute() {
        System.out.println("SDBANKS->RemoveFromCalendarAction.execute() Entered");

        try {
            DispatchActionService svc = new DispatchActionService();

            // Service handles loading the DispatchAction, deleting the Google event,
            // and clearing google_event_id + google_calendar_id in the DB.
            svc.removeFromCalendar(dispatchActionId);

            addActionMessage("Event removed from Google Calendar.");
            return SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Failed: " + e.getMessage());
            return ERROR;
        }
    }

    public Long getDispatchActionId() {
        return dispatchActionId;
    }

    public void setDispatchActionId(Long dispatchActionId) {
        this.dispatchActionId = dispatchActionId;
    }
}