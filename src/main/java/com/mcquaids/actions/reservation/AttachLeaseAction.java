package com.mcquaids.actions.reservation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mcquaids.model.Reservation;
import com.mcquaids.model.lookup.ReservationStatus;
import com.opensymphony.xwork2.Action;

public class AttachLeaseAction extends BaseReservationAction {

	private static final long serialVersionUID = 1L;

	private String filename;
	private String path;

	private Map<String, Object> jsonResponse = new HashMap<>();

	@Override
	public String execute() {

		try {
			if (reservationID == null || filename == null || path == null) {
				
				System.out.println("reservationID=" + reservationID);
				System.out.println("filename=" + filename);
				System.out.println("path=" + path);
				
				jsonResponse.put("status", "error");
				jsonResponse.put("message", "Missing required fields");
				return Action.SUCCESS;
			}

			// Load reservation
			Reservation reservation = reservationService.getReservation(reservationID);
			if (reservation == null) {
				jsonResponse.put("status", "error");
				jsonResponse.put("message", "Reservation not found");
				return Action.SUCCESS;
			}

			// Update reservation fields
			reservation.setLeaseDocumentPath(filename);  // this LeaseDocument path needs to be converted to SetFilename in the future
			reservation.setLeaseSignedDate(new Date());
			reservation.setLeaseSignedBy("system"); // or current user
			reservation.setReservationStatusCode(ReservationStatus.ACTIVE);

			reservationService.updateReservation(reservation);

			jsonResponse.put("status", "success");
			jsonResponse.put("message", "Lease attached");
			return Action.SUCCESS;

		} catch (Exception ex) {
			ex.printStackTrace();
			jsonResponse.put("status", "error");
			jsonResponse.put("message", "Server error attaching lease");
			return Action.SUCCESS;
		}
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the jsonResponse
	 */
	public Map<String, Object> getJsonResponse() {
		return jsonResponse;
	}

	/**
	 * @param jsonResponse the jsonResponse to set
	 */
	public void setJsonResponse(Map<String, Object> jsonResponse) {
		this.jsonResponse = jsonResponse;
	}


}
