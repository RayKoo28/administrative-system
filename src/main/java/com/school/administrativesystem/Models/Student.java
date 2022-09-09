package com.school.administrativesystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {

    @Id
    private String email;

    @Column
    private String name;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "teacher_student",
            joinColumns = @JoinColumn(name = "student_email"),
            inverseJoinColumns = @JoinColumn(name = "teacher_email"))
    private Set<Teacher> teachers = new HashSet<>();

    public String getEmail(){
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getName(){
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }
}
