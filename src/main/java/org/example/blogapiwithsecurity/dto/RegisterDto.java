package org.example.blogapiwithsecurity.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter

public class RegisterDto {
    private String name;
    private String email;
    private String username;
    private String password;

}
