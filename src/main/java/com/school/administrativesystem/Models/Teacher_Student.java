package com.school.administrativesystem.Models;

import java.util.List;

public class Teacher_Student {

    private String teacher;

    private List<String> students;

    public String getTeacher() {
        return teacher;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }
}
