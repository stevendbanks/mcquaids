package com.mcquaids.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.mcquaids.dao.interfaces.IUserDAO;
import com.mcquaids.model.Customer;

public class UserDAO extends DaoDataSource implements IUserDAO {

	@Override
	public Customer saveNewCustomer(Customer pCustomer) {
		addCustomer(pCustomer);
		return pCustomer;
	}

	public void addCustomer(Customer customer) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate.getDataSource());
		String sql = "CALL AddUserAndCustomer(:userID, :firstName, :lastName, :phone, :email, :street, :city, :province, :country, :postalCode, :notes)";

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("userID", customer.getUserID())
				.addValue("firstName", customer.getFirstName())
				.addValue("lastName", customer.getLastName())
				.addValue("phone", customer.getPhone())
				.addValue("email", customer.getEmail())
				.addValue("street", customer.getStreet())
				.addValue("city", customer.getCity())
				.addValue("province", customer.getProvince())
				.addValue("country", customer.getCountry())
				.addValue("postalCode", customer.getPostalCode())
				.addValue("notes", customer.getNotes());

		try {
			namedParameterJdbcTemplate.update(sql, namedParameters);
			System.out.println("addCustomer DAO End");
		} catch (DataAccessException e) {
			System.err.println("Error executing stored procedure: " + e.getMessage());
			// Handle specific error scenarios here
		} catch (Exception e) {
			System.err.println("Unexpected error: " + e.getMessage());
			// Handle unexpected errors here
		}
	}

	@Override
	public Customer saveCustomer(Customer pCustomer) {
		System.out.println("SAVEING Customer=" + pCustomer.toString());
		String sql = "UPDATE user SET FirstName = ?, LastName = ?, Phone = ?, Email = ?, street = ?, City = ?, Province = ?, Country = ?, PostalCode = ? WHERE UserID = ?";
		jdbcTemplate.update(sql, pCustomer.getFirstName(), pCustomer.getLastName(), pCustomer.getPhone(),
				pCustomer.getEmail(), pCustomer.getStreet(), pCustomer.getCity(), pCustomer.getProvince(),
				pCustomer.getCountry(), pCustomer.getPostalCode(), pCustomer.getUserID());
		return pCustomer;
	}

	@Override
	public List<Customer> queryCustomers(String userID, String customerName, String phone, String email,
			String street, String city, String province, String country, String postalCode) {

		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate.getDataSource());

		StringBuilder sql = new StringBuilder(
				"SELECT u.UserID, u.FirstName, u.LastName, u.Phone, u.Email, u.street, u.City, u.Province, u.Country, u.PostalCode, "
						+ "c.Notes, c.CreatedDateTime, c.CreatedUserID " + "FROM user u "
						+ "LEFT JOIN customer c ON u.UserID = c.UserID WHERE 1=1");

		MapSqlParameterSource parameters = new MapSqlParameterSource();

		if (!StringUtils.isEmpty(userID)) {
			sql.append(" AND u.UserID = :userID");
			parameters.addValue("userID", userID);
		}
		if (!StringUtils.isEmpty(customerName)) {
			String customerNameSearchString = "%" + customerName.toLowerCase() + "%";

			sql.append(" and (LOWER(u.FirstName) LIKE :customerNameSearchString OR LOWER(u.LastName) LIKE :customerNameSearchString)");	
			parameters.addValue("customerNameSearchString", customerNameSearchString);
			parameters.addValue("customerNameSearchString", customerNameSearchString);
		}

		if (!StringUtils.isEmpty(phone)) {
			sql.append(" AND u.Phone = :phone");
			parameters.addValue("phone", phone);
		}
		if (!StringUtils.isEmpty(email)) {
			sql.append(" AND u.Email = :email");
			parameters.addValue("email", email);
		}
		if (!StringUtils.isEmpty(street)) {
			sql.append(" AND u.street = :street");
			parameters.addValue("street", street);
		}
		if (!StringUtils.isEmpty(city)) {
			sql.append(" AND u.City = :city");
			parameters.addValue("city", city);
		}
		if (!StringUtils.isEmpty(province)) {
			sql.append(" AND u.Province = :province");
			parameters.addValue("province", province);
		}
		if (!StringUtils.isEmpty(country)) {
			sql.append(" AND u.Country = :country");
			parameters.addValue("country", country);
		}
		if (!StringUtils.isEmpty(postalCode)) {
			sql.append(" AND u.PostalCode = :postalCode");
			parameters.addValue("postalCode", postalCode);
		}

		System.out.println(sql.toString());
		
		return namedParameterJdbcTemplate.query(sql.toString(), parameters, new CustomerRowMapper());
	}

	@Override
	public List<Customer> findByPhoneNumber(String pPhoneNumber) {
		String sql = "SELECT * FROM user WHERE Phone = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class), pPhoneNumber);
	}

	@Override
	public Customer findByCustomerID(String pUserID) {
		String sql = "SELECT * FROM user WHERE UserID = ?";
		List<Customer> customers = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class), pUserID);
		return customers.isEmpty() ? null : customers.get(0);
	}

	@Override
	public List<Customer> findByCustomerName(String pCustomerName) {
		String searchTerm = "%" + pCustomerName.toLowerCase() + "%";

		String sql = "SELECT *  FROM user u LEFT JOIN customer c ON u.UserID = c.UserID  WHERE LOWER(u.FirstName) LIKE ? OR LOWER(u.LastName) LIKE ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class), searchTerm, searchTerm);
	}

	@Override
	public List<Customer> findByemail(String pEmail) {
		String sql = "SELECT * FROM user WHERE Email = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class), pEmail);
	}

}
