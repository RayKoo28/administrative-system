package com.school.administrativesystem.Controller;

import com.school.administrativesystem.Repo.StudentRepo;
import com.school.administrativesystem.Repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class APIController {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private TeacherRepo teacherRepo;

    @GetMapping(value = "/")
    public String GetTest()
    {
        return "Welcome";
    }
}
