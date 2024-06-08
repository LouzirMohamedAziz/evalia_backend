package com.evalia.backend.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class VerificationToken {
    
    @Id
    private String token;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    @OneToOne(targetEntity = Account.class, fetch = FetchType.EAGER)
    private Account account;
    
    private TokenType tokenType;

}
