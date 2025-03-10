package com.example.exercise_13tuwaiqjavabootcamp.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Project {

    //project Class : ID , title , description , status, companyName
    @NotNull
    @Min(value = 2,message = "your id length must be more than 2...")
    private int id;
    @NotEmpty
    @Size(min = 8,message = "your title length must be more than 8...")
    private String title;
    @NotEmpty
    @Size(min = 15,message = "your description length must be more than 15...")
    private String description;
    @NotEmpty
//    @Pattern(regexp = "['Not started'],['in Progress'],['Completed']")
    private String status;
    @NotEmpty
    @Size(min = 6,message = "your company name length must be more than 6...")
    private String companyName;

}
