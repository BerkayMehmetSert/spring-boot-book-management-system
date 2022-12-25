package com.bms.bookmanagementsystem.helper.result;

public class SuccessDataResult<T> extends DataResult<T>{
	public SuccessDataResult(String message) {
		super(true,null,message);
	}
	
	public SuccessDataResult(T data, String message) {
		super(true, data, message);
	}
}
