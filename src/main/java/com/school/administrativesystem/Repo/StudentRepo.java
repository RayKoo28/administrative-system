package com.school.administrativesystem.Repo;

import com.school.administrativesystem.Models.Student;
import com.school.administrativesystem.Models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, String> {
    List<Student> findAllByTeachersNotIn(List<Teacher> teachers);


    @Query(value = "SELECT s.email FROM student s " +
            "LEFT JOIN teacher_student ts ON s.email = ts.student_email " +
            "WHERE ts.teacher_email IN :targetGroup " +
            "GROUP BY s.email " +
            "HAVING COUNT(s.name) = :groupCount",
    nativeQuery = true)
    List<String> filterWithTeacherEmailList(@Param("targetGroup") List<String> targetGroup,
                                            @Param("groupCount") long groupCount);

}
