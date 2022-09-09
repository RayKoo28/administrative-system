package com.school.administrativesystem.Service;

import com.school.administrativesystem.Exception.ErrorResponse;
import com.school.administrativesystem.Exception.ResourceNotFoundException;
import com.school.administrativesystem.Models.Student;
import com.school.administrativesystem.Repo.StudentRepo;
import com.school.administrativesystem.ServiceInterface.StudentServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService implements StudentServiceInterface {

    private StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        super();
        this.studentRepo = studentRepo;
    }

    @Override
    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    @Override
    public ResponseEntity<?> saveStudent(Student student) {
        if (student.getEmail() == null)
        {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Student email must be provided!");
            return new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
        }

        Optional<Student> foundStudent = studentRepo.findById(student.getEmail());

        if (foundStudent.isPresent())
        {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Student email already existed!");
            return new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(studentRepo.save(student), HttpStatus.CREATED);
    }

    @Override
    public Map<String, List<String>> getCommonStudents(List<String> teacherEmailList) {
        List<String> student_email_list
                = studentRepo.filterWithTeacherEmailList(teacherEmailList, teacherEmailList.size());

        Map<String, List<String>> result = new HashMap<>();
        result.put("students", student_email_list);

        return result;
    }

    @Override
    public ResponseEntity<?> suspendStudent(Map<String, String> studentInstance) {

        ErrorResponse errorResponse = new ErrorResponse();
        // Return Error message if student key is not provided
        if (!studentInstance.containsKey("student"))
        {
            errorResponse.setMessage("Student email must be provided!");
            return new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
        }


        // Search for student in database
        Optional<Student> optionalStudent = studentRepo.findById(studentInstance.get("student"));
        if (!optionalStudent.isPresent())
        {
            errorResponse.setMessage("Student is not found!");
            return new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
        }
        Student student = optionalStudent.get();


        studentRepo.delete(student);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }
}
