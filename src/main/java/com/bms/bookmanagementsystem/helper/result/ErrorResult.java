package com.bms.bookmanagementsystem.helper.result;

public class ErrorResult extends Result{

	public ErrorResult(String message) {
		super(false, message);
	}
}