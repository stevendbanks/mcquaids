package com.mcquaids.service;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mcquaids.dao.ReservationDAO;
import com.mcquaids.dao.ReservationLineItemDAO;
import com.mcquaids.model.Reservation;
import com.mcquaids.model.ReservationLineItem;
import com.mcquaids.model.ReservationLineItemDTO;
import com.mcquaids.model.ReservationQueryDTO;
import com.mcquaids.model.ReservationViewDTO;

public class ReservationService {

    private final ReservationDAO reservationDAO;
    private final ReservationLineItemDAO reservationLineItemDAO;

    private final CustomerService customerService;

    private String errorMessage;

    public ReservationService() {
        JdbcTemplate jdbcTemplate = DaoDataSource.jdbcTemplate;
        this.reservationDAO = new ReservationDAO(jdbcTemplate);
        this.reservationLineItemDAO = new ReservationLineItemDAO(jdbcTemplate);

        new EquipmentService();
        this.customerService = new CustomerService();
    }

    // ------------------------------------------------------------
    // Reservation CRUD
    // ------------------------------------------------------------
    public Reservation createReservation(Reservation reservation) {
        Integer newId = reservationDAO.createReservation(reservation);
        return reservationDAO.getReservation(newId);
    }

    public Reservation getReservation(Integer reservationID) {
        Reservation reservation = reservationDAO.getReservation(reservationID);
        reservation.setCustomer(customerService.edit(reservation.getCustomerID()));
        return reservation;
    }

    public void updateReservation(Reservation reservation) {
        reservationDAO.updateReservation(reservation);
    }

    public void deleteReservation(Integer reservationID, String customerID) {
        reservationDAO.deleteReservation(reservationID, customerID);
    }

    public List<Reservation> getAllReservationsForCustomer(String customerID) {
        return reservationDAO.getAllReservationsForCustomerID(customerID);
    }

    // ------------------------------------------------------------
    // Reservation View (reservation_view)
    // ------------------------------------------------------------
    public List<ReservationQueryDTO> getReservationDetails(
            Integer reservationID,
            String customerID,
            String reservationStatusCode) {

        return reservationDAO.getReservationDetails(reservationID, customerID, reservationStatusCode);
    }

    // ------------------------------------------------------------
    // Reservation Equipment (Base Table)
    // ------------------------------------------------------------
    public List<ReservationLineItem> getReservedEquipmentByReservationID(Integer reservationID) {
        return reservationLineItemDAO.getReservedEquipmentByReservationID(reservationID);
    }

    public ReservationLineItem editReservationLineItem(int reservationLineItemID) {
        return reservationLineItemDAO.getReservationLineItem(reservationLineItemID);
    }

    // ------------------------------------------------------------
    // Add Equipment to Reservation (NEW simplified version)
    // ------------------------------------------------------------
    public ReservationLineItemDTO addEquipmentToReservation(
            Integer reservationID,
            Integer equipmentNumber,
            String notes) {

        try {
            Integer reservationLineItemID =
                    reservationLineItemDAO.createReservationLineItem(
                            reservationID,
                            equipmentNumber,
                            notes
                    ); 

            return reservationLineItemDAO.viewReservationLineItem(reservationLineItemID);

        } catch (DuplicateKeyException e) {
            this.errorMessage = "Error - This reservation line item already exists.";
            return null;
        }
    }

    public boolean updateReservedEquipment(ReservationLineItem reservedEquipment) {
        return reservationLineItemDAO.updateReservationLineItem(reservedEquipment);
    }

    public void removeEquipmentFromReservation(int reservationLineItemID) {
    	
    	ReservationLineItem x = reservationLineItemDAO.getReservationLineItem(reservationLineItemID);
    	Reservation y = reservationDAO.getReservation(x.getReservationID());
    	
    	if (y.getReservationStatusCode().equals("1001-01")) {
    		reservationLineItemDAO.deleteReservationLineItem(reservationLineItemID);
    	} else {
    		System.out.println("Invalid ReservationStatusCode:" + y.getReservationStatusCode() );
 		    this.errorMessage = "Error - Reservation Status must be DRAFT in order to remove the equipment. Please use the other actions";
    	}
    }
    
    
    
    public Integer substituteEquipmentInReservation(Integer oldReservationLineItemID, Integer newEquipmentNumber) {
    	Integer reservationID = null;
        
    	
        if (oldReservationLineItemID == null || newEquipmentNumber == null) {
        	this.errorMessage = "Invalid substitution request.";
        }

        // Load the existing line item
        
        ReservationLineItem oldItem =
                this.viewReservationLineItem(oldReservationLineItemID);

        if (oldItem == null) {
        	this.errorMessage ="The original reservation line item could not be found.";
        } else {
        	reservationID = oldItem.getReservationID();	
        }

      /// *************** SDBANKS - should we be validating that the reservationID and the   oldReservationLineItemID belong to the same reservation????
      //  reservationID = oldItem.getReservationID();

        System.out.println("SDBANKS - 9000");        
    	reservationLineItemDAO.deleteReservationLineItem(oldReservationLineItemID);
System.out.println("SDBANKS - 10000");

            // Add the new equipment
            ReservationLineItem newItem =
                    this.addEquipmentToReservation(
                            reservationID,
                            newEquipmentNumber,
                            ""          // notes empty for now
                   );

            if (newItem == null) {
            	this.errorMessage ="Unable to add the replacement equipment.";
            }    	
    	return reservationID;
    	
    }
    

    // ------------------------------------------------------------
    // Reservation Line Item View (DTO)
    // ------------------------------------------------------------
    public List<ReservationLineItemDTO> getReservationLineItems(Integer reservationID) {
        return reservationLineItemDAO.getReservationLineItems(reservationID);
    }

    public ReservationLineItemDTO viewReservationLineItem(Integer reservationLineItemID) { 
        return reservationLineItemDAO.viewReservationLineItem(reservationLineItemID);
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

    public List<ReservationViewDTO> findReservationsByCriteria(Integer reservationID, String customerID) {
        return reservationDAO.findReservationsByCriteria(reservationID, customerID);
    }
}