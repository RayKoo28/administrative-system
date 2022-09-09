package com.school.administrativesystem.Controller;

import com.school.administrativesystem.Models.Teacher;
import com.school.administrativesystem.Repo.TeacherRepo;
import com.school.administrativesystem.ServiceInterface.TeacherServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TeacherController {

    private TeacherServiceInterface teacherService;

    public TeacherController(TeacherServiceInterface teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(value = "/allteachers")
    public List<Teacher> getTeachers()
    {
        return teacherService.getTeachers();
    }

    @PostMapping(value = "/teachers")
    public ResponseEntity<?> saveTeacher(@RequestBody Teacher teacher)
    {
        return teacherService.saveTeacher(teacher);
    }
}
