package com.evalia.backend.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
	
  @GetMapping(path = "/administration/greeting")
  public String greetingAdmin(Principal principal) {
    return "Hello admin" + principal.getName();
  }
  
  @GetMapping(path = "/services/greeting")
  public String greetingUser(Principal principal) {
    return "Hello user" + principal.getName();
  }
}
