package com.school.administrativesystem.Repo;

import com.school.administrativesystem.Models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepo extends JpaRepository<Teacher, String> {
    List<Teacher> findAllByName(String name);

    List<Teacher> findAllByEmailNotIn(List<String> email);

}
