package com.clinic.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

public class CustomValidationUtils<T>{
	
	public static<T> Map<String, String> checkErrors (T model, Errors errors){

		if (model != null) {

			if (errors.hasErrors()) {
				Map<String, String> errorsMap = new HashMap<>();

				for (FieldError fe : errors.getFieldErrors()) {
					errorsMap.put(fe.getField(), fe.getDefaultMessage());
				}

				
				return errorsMap;
			}
			
		} else {
			Map<String, String> errorsMap = new HashMap<>();
			errorsMap.put("message", "no request body found");
			return errorsMap;
		}
		


		return null;
		
	}
	
}
