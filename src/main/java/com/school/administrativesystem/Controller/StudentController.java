package com.school.administrativesystem.Controller;

import com.school.administrativesystem.Models.Student;
import com.school.administrativesystem.ServiceInterface.StudentServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StudentController {
    private StudentServiceInterface studentService;

    public StudentController(StudentServiceInterface studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/allstudents")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping(value = "/students")
    public ResponseEntity<?> saveStudent(@RequestBody Student student)
    {
        return studentService.saveStudent(student);
    }

    @GetMapping(value = "/commonstudents")
    public Map<String, List<String>> getCommonStudents(@RequestParam(value = "teacher")List<String> teacherEmailList)
    {
        return studentService.getCommonStudents(teacherEmailList);
    }

    @PostMapping(value = "/suspend")
    public ResponseEntity<?> suspendStudent(@RequestBody Map<String, String> studentInstance)
    {
//        studentService.suspendStudent(studentInstance);
//
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        return studentService.suspendStudent(studentInstance);

    }
}
