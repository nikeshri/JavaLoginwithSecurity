package org.example.blogapiwithsecurity.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JWTauthResponse {

    private String accessToken;
    private String tokenType = "Bearer";
}
