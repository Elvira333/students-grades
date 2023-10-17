package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.StudentInfoDTO;
import com.example.servingwebcontent.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Tag(name = "TablesController")
public class TablesController {
    private final StudentService studentService;

    @Autowired
    public TablesController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/add-students-form")
    public String getAddStudentForm() {
        return "add_student_form";
    }

    @PostMapping("/add-students-form")
    public String addStudentWithGrades(@RequestParam("name") String studentName, @RequestParam("age") String studentAge, @RequestParam("grades") String grades) {
        List<Integer> gradeList = Arrays.stream(grades.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        studentService.addStudentWithGrades(studentName, studentAge, gradeList);
        return "redirect:/";
    }

    @GetMapping("/delete-student")
    public String deleteStudentForm() {
        return "deleted_student";
    }

    @PostMapping("/delete-student")
    public String deleteStudent(@RequestParam("name") String studentName) {
        studentService.deleteStudent(studentName);
        return "redirect:/";
    }

    @GetMapping("/student")
    @ResponseBody
    public List<StudentInfoDTO> getStudentsInfoWithAverageGrade(@RequestParam(name = "minGrade", defaultValue = "4.0") double minGrade) {
        return studentService.getStudentsInfoWithAverageGradeGreaterThanOrEqual(minGrade);
    }

}
