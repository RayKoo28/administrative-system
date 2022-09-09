package com.school.administrativesystem.Repo;

import com.school.administrativesystem.Models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher, String> {

}
