package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.GradesStudents;
import com.example.servingwebcontent.models.Students;
import com.example.servingwebcontent.repository.GradeRepository;
import com.example.servingwebcontent.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@io.swagger.v3.oas.annotations.tags.Tag(name = "TablesController")
public class TablesController {

    private final StudentsRepository studentsRepository;
    private final GradeRepository gradeRepository;


    @Autowired
    public TablesController(StudentsRepository studentsRepository, GradeRepository gradeRepository) {
        this.studentsRepository = studentsRepository;
        this.gradeRepository = gradeRepository;
    }

    @Transactional
    public void addStudentWithGrades(String studentName,String studentAge, List<Integer> grades){

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
    }



    @GetMapping("/add-students-form")
    public String getAddStudentForm() {
        return "add_student_form";
    }

    @PostMapping("/add-students-form")
    public String addStudentWithGrades(@RequestParam("name") String studentName, @RequestParam("age") String studentAge, @RequestParam("grades") String grades) {
        // Разделение оценок по запятой и преобразование в список чисел
        List<Integer> gradeList = Arrays.stream(grades.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        addStudentWithGrades(studentName,studentAge, gradeList);

        return "redirect:/";

    }

    @GetMapping("/delete-student")
    public String deleteStudentForm() {
        return "deleted_student";
    }

    @PostMapping("/delete-student")
    public String deleteStudent(@RequestParam("name") String studentName) {

        // Нахожу всех студентов с заданным именем
        List<Students> studentsToDelete = studentsRepository.findAllByName(studentName);
        if (!studentsToDelete.isEmpty()) {
            for (Students student : studentsToDelete) {
                // Удаление студента из таблицы студентов
                studentsRepository.delete(student);

                // Удаление связанные с ним оценки из таблицы оценок (GradesStudents)
                List<GradesStudents> studentGrades = gradeRepository.findByStudent(student);
                gradeRepository.deleteAll(studentGrades);
            }
        }
        return "redirect:/";
    }

    @GetMapping("/students-with-average-grade-greater-or-equal-4")
    @ResponseBody
    public List<Map<String, Object>> getStudentsInfoWithAverageGradeGreaterThanOrEqual4() {
        List<Object[]> studentInfoList = studentsRepository.findUniqueStudentInfoWithAverageGradeGreaterThanOrEqual4();

        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] studentInfo : studentInfoList) {
            Map<String, Object> studentInfoMap = new HashMap<>();
            studentInfoMap.put("id", studentInfo[0]);
            studentInfoMap.put("name", studentInfo[1]);
            studentInfoMap.put("age", studentInfo[2]);
            result.add(studentInfoMap);
        }

        return result;
    }




}
