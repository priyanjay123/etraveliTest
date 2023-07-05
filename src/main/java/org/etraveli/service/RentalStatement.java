package org.etraveli.service;

import org.etraveli.model.Customer;

public interface RentalStatement {
	public String statement(Customer customer);
}