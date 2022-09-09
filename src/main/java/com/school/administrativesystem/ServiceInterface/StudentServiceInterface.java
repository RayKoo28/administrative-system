package com.school.administrativesystem.ServiceInterface;

import com.school.administrativesystem.Models.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface StudentServiceInterface {
    List<Student> getStudents();
    ResponseEntity<?> saveStudent(Student student);
    Map<String, List<String>> getCommonStudents(List<String> teacherEmailList);
    ResponseEntity<?> suspendStudent(Map<String, String> studentInstance);
}
