package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.awt.*;

public interface FacultyService  {
    Faculty addFaculty(Faculty faculty);

    Faculty findFaculty(long id);

    Faculty editFaculty(long id, Faculty faculty);

    void deleteFaculty(long id);

    Object findByColor(String color);
}
