package com.school.administrativesystem.Controller;

import com.school.administrativesystem.Exception.ErrorResponse;
import com.school.administrativesystem.Models.Student;
import com.school.administrativesystem.Models.Teacher;
import com.school.administrativesystem.Models.Teacher_Student;
import com.school.administrativesystem.Repo.StudentRepo;
import com.school.administrativesystem.Repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TeacherStudentController {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private TeacherRepo teacherRepo;

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerStudents(@RequestBody Teacher_Student teacher_student)
    {
        ErrorResponse errorResponse = new ErrorResponse();
        // Return Error Message if Teacher or Student is not provided
        if (teacher_student.getTeacher() == null)
        {
            errorResponse.setMessage("Teacher's email must be provided!");
            return new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
        }
        if (teacher_student.getStudents() == null)
        {
            errorResponse.setMessage("Student's email must be provided!");
            return new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
        }

        // Search for teacher in database
        Optional<Teacher> optionalTeacher = teacherRepo.findById(teacher_student.getTeacher());
        if (!optionalTeacher.isPresent())
        {
            errorResponse.setMessage("Teacher is not found!");
            return new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
        }
        Teacher teacher = optionalTeacher.get();


        // Search for students in database
        List<Student> student_list = studentRepo.findAllById(teacher_student.getStudents());
        if (student_list.size() <=0)
        {
            errorResponse.setMessage("Student are not found!");
            return new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
        }


        // Assign the students to the following teacher
        teacher.setRegisteredStudents(new HashSet<>(student_list));

        teacherRepo.save(teacher);

        return new ResponseEntity<>(teacher, HttpStatus.NO_CONTENT);
    }
}
