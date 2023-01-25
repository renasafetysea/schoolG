package ru.hogwarts.school.service;

import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;

import java.awt.*;
import java.util.Collection;

public interface FacultyService  {
    Faculty addFaculty(Faculty faculty);

    Faculty findFaculty(long id);

    Faculty editFaculty(Faculty faculty);

    void deleteFaculty(long id);

    public Collection<Faculty> getAllFaculty();
}

