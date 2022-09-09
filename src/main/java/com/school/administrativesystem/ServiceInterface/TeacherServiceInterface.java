package com.school.administrativesystem.ServiceInterface;

import com.school.administrativesystem.Models.Teacher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TeacherServiceInterface {
    List<Teacher> getTeachers();
    ResponseEntity<?> saveTeacher(@RequestBody Teacher teacher);
}
