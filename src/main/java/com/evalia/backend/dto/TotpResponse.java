package com.evalia.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TotpResponse {
    private String secret;
    private String qr;
    private String type;
}
