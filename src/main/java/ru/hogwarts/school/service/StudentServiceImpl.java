package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {

        return studentRepository.save(student);
    }

    public Student findStudent(long id) {

        return studentRepository.getById(id);
    }
    public Student editStudent(Student student) {

        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }

}
