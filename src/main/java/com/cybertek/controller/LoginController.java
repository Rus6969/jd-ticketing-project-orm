package com.cybertek.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	// we add  value login on brackets it here bc when we do logout we want to come back to login page
	@RequestMapping(value = {"/login","/"})
	public String login(){

		return "/login";
	}
	
	@RequestMapping("/welcome")
	public String welcome(){
		return "welcome";
	}

}
