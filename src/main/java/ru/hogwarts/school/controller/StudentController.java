package ru.hogwarts.school.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentServiceImpl;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentServiceImpl studentService;
    private final AvatarService avatarService;
    private final AvatarRepository avatarRepository;

    public StudentController(StudentServiceImpl studentService, AvatarService avatarService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarService = avatarService;
        this.avatarRepository = avatarRepository;
    }

    @GetMapping("{id}") // GET http://localhost:5432/student/1
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping // GET http://localhost:8080/students
    public ResponseEntity<Collection<Student>> getAllStudent() {
        return (ResponseEntity<Collection<Student>>) studentService.getAllStudent();
    }

    @PostMapping // POST http://localhost:8080/students
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping // PUT http://localhost:8080/students
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}") // DELETE http://localhost:8080/student/1
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{age}")
    public ResponseEntity<Collection<Student>> filterByAge(@PathVariable int age) {
        if (age > 0) {
            return ResponseEntity.ok((Collection<Student>) studentService.filterByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("age")
    public ResponseEntity<Collection<Student>> getStudentsByAgeBetween(@RequestParam int min,
                                                                       @RequestParam int max) {
        return ResponseEntity.ok(studentService.findStudentByAgeBetween(min, max));
    }

    @GetMapping("faculty")
    public ResponseEntity<Long> getFacultyOfStudent(@RequestParam Long id) {
        Long foundFaculty = studentService.getFacultyIdOfStudent(id);
        if (foundFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundFaculty);
    }
}