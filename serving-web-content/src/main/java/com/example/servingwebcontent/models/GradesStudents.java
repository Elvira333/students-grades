package com.example.servingwebcontent.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Grades")
public class GradesStudents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer grade;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Students student;

    public GradesStudents() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }
}
