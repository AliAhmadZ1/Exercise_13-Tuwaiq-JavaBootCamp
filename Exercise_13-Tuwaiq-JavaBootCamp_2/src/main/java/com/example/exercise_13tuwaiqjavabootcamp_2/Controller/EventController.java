package com.example.exercise_13tuwaiqjavabootcamp_2.Controller;

import com.example.exercise_13tuwaiqjavabootcamp_2.ApiResponse.ApiResponse;
import com.example.exercise_13tuwaiqjavabootcamp_2.Model.Event;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
public class EventController {

    ArrayList<Event> events = new ArrayList<>();

    //• Display all event
    @GetMapping("/display")
    public ResponseEntity display() {
        return ResponseEntity.status(200).body(events);
    }

    //• Create a new event (ID , description , capacity, startDate , endDate)
    @PostMapping("/create")
//    @JsonFormat(pattern = "yyyy-MM-dd")
    public ResponseEntity createEvent(@RequestBody @Valid Event event) {
        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("created!!"));
    }

    //• Update a event
    @PutMapping("/update/{id}")
    public ResponseEntity updateEvent(@PathVariable int id, @RequestBody @Valid Event event) {
        for (int index = 0; index < events.size(); index++) {
            if (events.get(index).getId() == id) {
                events.set(index, event);
                return ResponseEntity.status(200).body(new ApiResponse("updated!!"));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found!!"));
    }

    //• Delete a event
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEvent(@PathVariable int id){
        for (Event e:events){
            if (e.getId()==id){
                events.remove(e);
                return ResponseEntity.status(200).body(new ApiResponse("deleted!!!"));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not Found"));
    }

    //• Change capacity
    @PutMapping("/change-capacity/{id},{capacity}")
    public ResponseEntity changeCapacity(@PathVariable int id,@PathVariable int capacity){
        for (Event e: events){
            if (e.getId()==id){
                e.setCapacity(capacity);
                return ResponseEntity.status(200).body(new ApiResponse("Capacity changed!!"));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }

    //• Search for a event by given id
    @GetMapping("/search/{id}")
    public ResponseEntity searchEvent(@PathVariable int id){
        for (Event e: events){
            if (e.getId()==id){
                return ResponseEntity.status(200).body(e);
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    //Hint ( use @JsonFormat(pattern="yyyy-MM-dd") and LocalDateTime )
}
