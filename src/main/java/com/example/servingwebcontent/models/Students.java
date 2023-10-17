package com.example.servingwebcontent.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "students")
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String age;
    @OneToMany(mappedBy = "student", orphanRemoval = true)
    private List<GradesStudents> grades;

    public Students() {
    }

    public Students(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<GradesStudents> getGrades() {
        return grades;
    }

    public void setGrades(List<GradesStudents> grades) {
        this.grades = grades;
    }
}
