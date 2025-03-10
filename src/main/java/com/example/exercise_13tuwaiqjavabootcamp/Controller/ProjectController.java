package com.example.exercise_13tuwaiqjavabootcamp.Controller;

import com.example.exercise_13tuwaiqjavabootcamp.ApiResponse.ApiResponse;
import com.example.exercise_13tuwaiqjavabootcamp.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
public class ProjectController {

    ArrayList<Project> projects = new ArrayList<>();

    //• Display all project.
    @GetMapping("/display")
    public ResponseEntity display() {
        return ResponseEntity.status(200).body(projects);
    }

    //• Create a new project (ID,title , description , status, companyName)
    @PostMapping("/create")
    public ResponseEntity createNewProject(@RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else {
            projects.add(project);
            return ResponseEntity.status(200).body(new ApiResponse("created!!!!"));
        }
    }

    //• Update a project
    @PutMapping("/update/{id}")
    public ResponseEntity updateProject(@PathVariable int id, @RequestBody @Valid Project project, Errors errors) {
        if (!errors.hasErrors()) {
            for (int index = 0; index < projects.size(); index++) {
                if (projects.get(index).getId() == id) {
                    projects.set(index, project);
                    return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("updated"));
                }
            }
        } else ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found...");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
    }

    //• Delete a project
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProject(@PathVariable int id) {
        for (int index = 0; index < projects.size(); index++) {
            if (projects.get(index).getId() == id) {
                projects.remove(index);
                return ResponseEntity.status(200).body(new ApiResponse("deleted!"));
            }
        }
        return ResponseEntity.status(200).body(new ApiResponse("not found..."));
    }

    //• Change the project status as done or not done
    @PutMapping("/change-status/{id},{status}")
    public ResponseEntity changeStatus(@PathVariable int id, @PathVariable String status) {
        for (int index = 0; index < projects.size(); index++) {
            if (projects.get(index).getId() == id) {
                if (status.equals("done")) {
                    projects.get(index).setStatus("done");
                    return ResponseEntity.status(200).body(new ApiResponse("Done!"));
                } else if (status.equals("not done")) {
                    projects.get(index).setStatus("not done");
                    return ResponseEntity.status(200).body(new ApiResponse("Done!"));
                } else
                    return ResponseEntity.status(400).body(new ApiResponse("there are no status like that" + "'" + status + "'"));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found!!"));
    }

    //• Search for a project by given title
    @GetMapping("/search/{title}")
    public ResponseEntity searchProject(@PathVariable String title) {
        for (Project p : projects) {
            if (p.getTitle().equals(title))
                return ResponseEntity.status(200).body(p);
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found..."));
    }

    //• Display All project for one company by companyName.
    @GetMapping("/display-company/{companyName}")
    public ResponseEntity displayAllByCompanyName(@PathVariable String companyName) {
        ArrayList<Project> company = new ArrayList<>();
        for (Project p : projects) {
            if (p.getCompanyName().equals(companyName))
                company.add(p);
        }
        return ResponseEntity.status(200).body(company);
    }
}
