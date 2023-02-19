package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.FacultyServiceImpl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyServiceImpl facultyService;

    public FacultyController(FacultyServiceImpl facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}") // GET http://localhost:8080/faculty/1
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping // GET http://localhost:8080/facultys
    public ResponseEntity<Collection<Faculty>> getAllFaculty() {
        return (ResponseEntity<Collection<Faculty>>) facultyService.getAllFaculty();
    }

    @PostMapping // POST http://localhost:8080/facultys
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping // PUT http://localhost:8080/facultys
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}") // DELETE http://localhost:8080/faculty/1
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{color}")
    public ResponseEntity<Collection<Faculty>> filterByColor(@PathVariable String color) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok((Collection<Faculty>) facultyService.filterByColor(color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("{faculty}")
    public ResponseEntity<Faculty> findFacultyByNameOrColor(@RequestParam(required = false) String name,
                                                            @RequestParam(required = false) String color) {
        Faculty foundFaculty = facultyService.findFacultyByNameOrColor(name, color);
        if (foundFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @GetMapping("students")
    public ResponseEntity<Collection<Student>> getStudentsFromFaculty(@RequestParam Long id) {
        if (facultyService.findFaculty(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyService.getStudentsFromFaculty(id));
    }
    @GetMapping("names/faculty")
    public ResponseEntity<List<String>> longetsTitleOfFaculty(){
        return ResponseEntity.ok(facultyService.longestTitleOfFaculty());

    }

}
