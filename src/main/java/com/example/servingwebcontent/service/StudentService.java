package com.example.servingwebcontent.service;

import com.example.servingwebcontent.models.GradesStudents;
import com.example.servingwebcontent.models.StudentInfoDTO;
import com.example.servingwebcontent.models.Students;
import com.example.servingwebcontent.repository.GradeRepository;
import com.example.servingwebcontent.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class StudentService {
    private final StudentsRepository studentsRepository;
    private final GradeRepository gradeRepository;

    @Autowired
    public StudentService(StudentsRepository studentsRepository, GradeRepository gradeRepository) {
        this.studentsRepository = studentsRepository;
        this.gradeRepository = gradeRepository;
    }

    @Transactional
    public void addStudentWithGrades(String studentName, String studentAge, List<Integer> grades) {
        Students student = new Students();
        student.setName(studentName);
        student.setAge(studentAge);

        List<GradesStudents> gradesList = new ArrayList<>();
        for (Integer grade : grades) {
            GradesStudents gradesStudents = new GradesStudents();
            gradesStudents.setGrade(grade);
            gradesStudents.setStudent(student);
            gradesList.add(gradesStudents);
        }

        student.setGrades(gradesList);

        studentsRepository.save(student);

        for (GradesStudents grade : gradesList) {
            gradeRepository.save(grade);
        }
    }

    @Transactional
    public void deleteStudent(String studentName) {
        List<Students> studentToDelete = studentsRepository.findAllByName(studentName);
        studentsRepository.deleteAll(studentToDelete);
    }

    public List<StudentInfoDTO> getStudentsInfoWithAverageGradeGreaterThanOrEqual(double minGrade) {
        return studentsRepository.findStudentsInfoWithAverageGradeGreaterThanOrEqual(minGrade);
    }
}
