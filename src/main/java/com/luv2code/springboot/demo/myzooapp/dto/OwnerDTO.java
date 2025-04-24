package com.luv2code.springboot.demo.myzooapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerDTO {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Contact info must not be blank")
    private String contactInfo;
}
