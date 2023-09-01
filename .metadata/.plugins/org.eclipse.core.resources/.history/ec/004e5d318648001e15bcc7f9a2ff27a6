package com.wibmo.rest;

import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	@RequestMapping(produces=MediaType.APPLICATION_JSON,
			method = RequestMethod.GET,
			value="/testStudent")
	public String testStudent() {
		return "I am Student";
	}
}
