package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;

public interface FacultyService {

    public Faculty addFaculty(Faculty faculty);

    public Faculty findFaculty(long id);


    public Faculty editFaculty(Faculty faculty) ;

    public void deleteFaculty(long id);

    public Collection<Faculty> getAllFaculty();

    public List<Faculty> filterByColor(String color);
}
