package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dataAccess.DataAccessFacade;

public class CheckoutRecordEntry implements Serializable {
	private LendableCopy copy;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	public LendableCopy getCopy(){
		return this.copy;
	}
	
	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}
	
	public void setCheckoutDate(LocalDate dt) {
		this.checkoutDate = dt;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(LocalDate dt) {
		this.dueDate = dt;
	}
	
	public CheckoutRecordEntry(LendableCopy copy, LocalDate checkoutDate, LocalDate dueDate){ 
		this.copy = copy;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
	}
	public String toString() {
		return "[" + "checkoutdate:" + 
	        checkoutDate.format(DateTimeFormatter.ofPattern(DataAccessFacade.DATE_PATTERN)) +
	        ", dueDate: " + dueDate.format(DateTimeFormatter.ofPattern(DataAccessFacade.DATE_PATTERN)) +
	        ", publication: " + copy + "]";
	}
}
