package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentService {
    Student addStudent(Student student);

    Student findStudent(long id);

    Student editStudent(Student student);

    void deleteStudent(long id);

     Collection<Student> getAllStudent();

    Collection<Student> filterByAge(int age);
    int getNumberOfAllStudents() ;

    int getAverageAge() ;

    Collection<Student> get5MaxId();

}
