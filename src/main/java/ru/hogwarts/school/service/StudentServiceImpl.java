package ru.hogwarts.school.service;

import liquibase.pro.packaged.A;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.debug("Method addStudent was called");
        return studentRepository.save(student);
    }


    public Student findStudent(long id) {
        logger.debug("Method findStudent was called");
        return studentRepository.getById(id);
    }


    public Student editStudent(Student student) {
        logger.debug("Method editStudent was called");
        return studentRepository.save(student);
    }


    public void deleteStudent(long id) {
        logger.debug("Method deleteStudent was called");
        studentRepository.deleteById(id);
    }


    @Override
    public Collection<Student> getAllStudent() {
        logger.debug("Method getAllStudent was called");
        return studentRepository.findAll();
    }


    public Collection<Student> filterByAge(int age) {
        logger.debug("Method filterByAge was called");
        return studentRepository.findByAge(age);
    }


    public Collection<Student> findStudentByAgeBetween(int min, int max) {
        logger.debug("Method findStudentByAgeBetween was called");
        return studentRepository.findStudentByAgeBetween(min, max);
    }


    public Long getFacultyIdOfStudent(Long id) {
        logger.debug("Method getFacultyIdOfStudent was called");
        if (findStudent(id) == null) {
            return null;
        }
        return findStudent(id).getFacultyId();

    }


    @Override
    public int getNumberOfAllStudents() {
        logger.debug("Method getNumbersOfAllStudents was called");
        return studentRepository.getNumberOfAllStudents();
    }


    @Override
    public int getAverageAge() {
        logger.debug("Method getAverageAge was called");
        return studentRepository.getAverageAge();
    }


    @Override
    public Collection<Student> get5MaxId() {
        logger.debug("Method get5MaxId was called");
        return studentRepository.get5MaxId();
    }
    public List<String> studentsNamesBeginA(){
        return studentRepository.findAll().stream().
                map(s -> s.getName().toUpperCase()).filter(s -> s.charAt(0) == 'A').sorted().toList();
    }

    public Double averageAge() {
        return studentRepository.findAll().stream().mapToDouble(Student::getAge).average().orElseThrow();
    }

    public void flowOfStudents() {
        System.out.println(getStudentName(1));
        System.out.println(getStudentName(2));

        new Thread(() ->{
            System.out.println(getStudentName(3));
            System.out.println(getStudentName(4));

        }).start();
        new Thread(() ->{
            System.out.println(getStudentName(5));
            System.out.println(getStudentName(6));

        }).start();
    }
    public String getStudentName(int num) {
        return studentRepository.findAll().stream().map(Student::getName).toList().get(num);
    }
    public void synchronizedStudentNameTwo() {
        synchronizedNamesPrint(1, 2);

        new Thread(() -> {
            synchronizedNamesPrint(3, 4);
        }).start();

        new Thread(() -> {
            synchronizedNamesPrint(5, 6);
        }).start();
    }

    public void synchronizedNamesPrint(int num1, int num2) {
        synchronized (studentRepository) {
            System.out.println(getStudentName(num1));
            System.out.println(getStudentName(num2));
        }
    }
}


