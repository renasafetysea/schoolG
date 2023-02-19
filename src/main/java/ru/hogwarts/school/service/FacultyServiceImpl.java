package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.debug("Method addFaculty was called");
        return facultyRepository.save(faculty);
    }


    public Faculty findFaculty(long id) {
        logger.debug("Method findFaculty was called");
        return facultyRepository.findById(id).get();
    }


    public Faculty editFaculty(Faculty faculty) {
        logger.debug("Method editFaculty was called");
        if (findFaculty(faculty.getId()) == null){
            return null;
        }
        return facultyRepository.save(faculty);
    }


    public void deleteFaculty(long id) {
        logger.debug("Method deleteFaculty was called");
        facultyRepository.deleteById(id);
    }


    public Collection<Faculty> getAllFaculty() {
        logger.debug("Method getAllFaculty was called");
        return facultyRepository.findAll();
    }


    public List<Faculty> filterByColor(String color) {
        logger.debug("Method filterByColor was called ");
        return facultyRepository.findByColor(color);
    }


    public Faculty findFacultyByNameOrColor(String name, String color) {
        logger.debug("Method findFacultyByNameOrColor was called");
        if (!(name == null || name.isBlank())) {
            color = null;
        }
        return facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }


    public Collection<Student> getStudentsFromFaculty(Long id) {
        logger.debug("Method getStudentsFromFaculty was called");
        return findFaculty(id).getStudents();
    }
    public List<String> longestTitleOfFaculty(){
        int longTitle = facultyRepository.findAll().stream().
                mapToInt(s -> s.getName().length()).max().orElseThrow();
        return facultyRepository.findAll().stream().map(Faculty::getName).filter(s ->s.length() == longTitle).toList();
    }
}


