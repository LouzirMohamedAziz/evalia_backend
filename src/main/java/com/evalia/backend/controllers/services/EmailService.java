package com.evalia.backend.controllers.services;

public interface EmailService {

    public void sendEmail(String to, String subject, String body);
    
}