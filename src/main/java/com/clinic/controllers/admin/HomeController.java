package com.clinic.controllers.admin;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController{

	@RequestMapping("/admin/home/")
	public String index(){

		return "admin/home";
	}
	
}
