package com.mcquaids.actions.dispatch;

import com.mcquaids.service.DispatchActionService;
import com.opensymphony.xwork2.ActionSupport;

public class DispatchCalendarAction extends ActionSupport {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long dispatchActionId;
    private String calendarEventLink;

    public void setDispatchActionId(Long id) {
        this.dispatchActionId = id;
    }

    public String getCalendarEventLink() {
        return calendarEventLink;
    }

    // ============================================
    // PUSH TO GOOGLE CALENDAR
    // ============================================
    public String push() {
    	System.out.println("SDBANKs-push toCalendar");
        try {
            DispatchActionService svc = new DispatchActionService();

            // Service handles both Reservation + Movement Order dispatch actions
            this.calendarEventLink = svc.pushToCalendar(dispatchActionId);

            addActionMessage("Event created successfully.");
            return SUCCESS;

        } catch (Exception e) {
        	e.printStackTrace();
            addActionError("Failed: " + e.getMessage());
            return ERROR;
        }
    }

    // ============================================
    // REMOVE FROM GOOGLE CALENDAR
    // ============================================
    public String remove() {
        try {
            DispatchActionService svc = new DispatchActionService();

            svc.removeFromCalendar(dispatchActionId);

            addActionMessage("Event removed successfully.");
            return SUCCESS;

        } catch (Exception e) {
            addActionError("Failed: " + e.getMessage());
            return ERROR;
        }
    }
}
