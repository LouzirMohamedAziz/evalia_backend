package com.evalia.backend.security.auth;
import com.evalia.backend.metadata.Role;
import com.evalia.backend.models.Actor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String email;
  private String username;
  private String password;
  private boolean mfaEnabled;
  private Actor actor;
}
