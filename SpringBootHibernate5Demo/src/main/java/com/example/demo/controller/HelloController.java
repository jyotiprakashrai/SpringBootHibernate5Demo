package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

	
	
	/** Web method to demonstrate how to use JSP as view in Spring boot application 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/hello", method = RequestMethod.GET)
    public String hello(Model model) {
		model.addAttribute("message","Hello User use http://localhost:8080/list to fetch data");
        return "hello";
    }
}
