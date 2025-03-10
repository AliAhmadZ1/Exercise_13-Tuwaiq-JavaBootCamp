package com.example.exercise_13tuwaiqjavabootcamp_2.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

@Data
@AllArgsConstructor
public class Event {

    //event Class : ID , description , capacity, startDate , endDate

    @NotNull
    @Min(value = 10,message = "your id length must be more than 2")
    private int id;
    @NotEmpty
    @Size(min = 15,message = "description must be more than 15")
    private String description;
    @NotNull
    @NumberFormat
    @Min(value = 25,message = "your capacity should be more than 25")
    private int capacity;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

}
