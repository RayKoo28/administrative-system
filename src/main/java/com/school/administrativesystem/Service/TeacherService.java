package com.school.administrativesystem.Service;

import com.school.administrativesystem.Exception.ErrorResponse;
import com.school.administrativesystem.Models.Teacher;
import com.school.administrativesystem.Repo.TeacherRepo;
import com.school.administrativesystem.ServiceInterface.TeacherServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements TeacherServiceInterface {
    private TeacherRepo teacherRepo;

    public TeacherService(TeacherRepo teacherRepo) {
        super();
        this.teacherRepo = teacherRepo;
    }

    @Override
    public List<Teacher> getTeachers() {
        return teacherRepo.findAll();
    }

    @Override
    public ResponseEntity<?> saveTeacher(Teacher teacher) {
        if (teacher.getEmail() == null)
        {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Teacher email must be provided!");
            return new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
        }

        Optional<Teacher> foundTeacher = teacherRepo.findById(teacher.getEmail());

        if (foundTeacher.isPresent())
        {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Teacher email already existed!");
            return new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(teacherRepo.save(teacher), HttpStatus.CREATED);
    }
}
