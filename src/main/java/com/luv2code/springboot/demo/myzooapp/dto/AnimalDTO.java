package com.luv2code.springboot.demo.myzooapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnimalDTO {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Species must not be blank")
    private String species;

    @Min(value = 0, message = "Age must be greater than or equal to 0")
    private int age;
}
