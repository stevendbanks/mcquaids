package com.mcquaids.service;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mcquaids.dao.ReservationDAO;
import com.mcquaids.dao.ReservationLineItemDAO;
import com.mcquaids.model.Equipment;
import com.mcquaids.model.Reservation;
import com.mcquaids.model.ReservationLineItem;
import com.mcquaids.model.ReservationLineItemDTO;
import com.mcquaids.model.ReservationQueryDTO;

public class ReservationService {

    private ReservationDAO reservationDAO;
    
    private ReservationLineItemDAO reservationLineItemDAO;

    private String errorMessage;

	private EquipmentService equipmentService;
	private CustomerService customerService;

	private Equipment equipment;

    public ReservationService() {
        JdbcTemplate jdbcTemplate = DaoDataSource.jdbcTemplate;
        this.reservationDAO = new ReservationDAO(jdbcTemplate);
        this.reservationLineItemDAO = new ReservationLineItemDAO(jdbcTemplate);
    	this.equipmentService = new EquipmentService();

    	this.customerService = new CustomerService();
    }

    // ------------------------------------------------------------
    // Reservation CRUD
    // ------------------------------------------------------------
    public void createReservation(Reservation reservation) {
        reservationDAO.createReservation(reservation);
    }

    public Reservation getReservation(String reservationID) {
    	Reservation reservation = reservationDAO.getReservation(reservationID);
    	reservation.setCustomer(customerService.edit(reservation.getCustomerID()));
    	return reservation;
    	
    }

    public void updateReservation(Reservation reservation) {
        reservationDAO.updateReservation(reservation);
    }
    


    public void deleteReservation(String reservationID, String customerID) {
        reservationDAO.deleteReservation(reservationID, customerID);
    }

    public List<Reservation> getAllReservationsForCustomer(String customerID) {
        return reservationDAO.getAllReservationsForCustomerID(customerID);
    }

    // ------------------------------------------------------------
    // Reservation View (reservation_view)
    // ------------------------------------------------------------
    public List<ReservationQueryDTO> getReservationDetails(String reservationID,
                                                           String customerID,
                                                           String reservationStatusCode) {
        return reservationDAO.getReservationDetails(reservationID, customerID, reservationStatusCode);
    }

    // ------------------------------------------------------------
    // Reservation Equipment (Base Table)
    // ------------------------------------------------------------
    public List<ReservationLineItem> getReservedEquipmentByReservationID(String reservationID) {
        return reservationLineItemDAO.getReservedEquipmentByReservationID(reservationID);
    }

    public ReservationLineItem editReservationLineItem(int reservationLineItemID) {
        return reservationLineItemDAO.getReservationLineItem(reservationLineItemID);
    }

    public ReservationLineItemDTO addEquipmentToReservation(
            String reservationID,
            String equipmentNumber,
            int quantity,
            String notes) {

		
		//  get the Equipment Record as we will be storing the properties, type, and subtype in the reservation table.  We are not reserving this exact piece of equipment, so we just need the properties.
		equipment = equipmentService.edit(equipmentNumber);
    	
        try {
        	int reservationLineItemID = reservationLineItemDAO.createReservationLineitem(
                    reservationID,
                    equipment.getEquipmentType(),
                    equipment.getEquipmentSubType(),
                    quantity,
                    notes,
                    equipment.getProperties()
            );

            return reservationLineItemDAO.viewReservationLineItem(reservationLineItemID);

        } catch (DuplicateKeyException e) {
            this.errorMessage = "Error - This reservation line item already exists.  Please modify the existing one rather than adding another one.";
            return null;
        }
    }

    public boolean updateReservedEquipment(ReservationLineItem reservedEquipment) {
        return reservationLineItemDAO.updateReservationLineItem(reservedEquipment);
    }

    public void removeEquipmentFromReservation(int reservationLineItemID) {
    	reservationLineItemDAO.deleteReservationLineItem(reservationLineItemID);
    }

    // ------------------------------------------------------------
    // Reservation Line Item View (ReservationLineItemView)
    // ------------------------------------------------------------
    public List<ReservationLineItemDTO> getReservationLineItems(String reservationID) {
        return reservationLineItemDAO.getReservationLineItems(reservationID);
    }
    
    
    // ------------------------------------------------------------
    // Reservation Line Item View (ReservationLineItemView)
    // ------------------------------------------------------------
    public ReservationLineItemDTO viewReservationLineItem(int reservationLineitemID) { 
        return reservationLineItemDAO.viewReservationLineItem(reservationLineitemID);
    }    

    // ------------------------------------------------------------
    // Error Handling
    // ------------------------------------------------------------
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String msg) {
        this.errorMessage = msg;
    }
}