/**
 * 
 */
package com.wibmo.rest;

import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 */
@RestController
public class ProfessorController {
	@RequestMapping(produces=MediaType.APPLICATION_JSON,
			method = RequestMethod.GET,
			value="testProfessor")
	public String testProfessor() {
		return "I am Professor";
	}
}
