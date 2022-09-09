package com.school.administrativesystem.Models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Teacher {

    @Id
    private String email;

    @Column
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "teacher_student",
            joinColumns = @JoinColumn(name = "teacher_email"),
            inverseJoinColumns = @JoinColumn(name = "student_email"))
    private Set<Student> registeredStudents = new HashSet<>();

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

    public Set<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(Set<Student> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }
}
