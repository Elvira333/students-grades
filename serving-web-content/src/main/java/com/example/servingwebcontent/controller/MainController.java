package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.servingwebcontent.repository.StudentsRepository;


import java.util.Map;


@Controller
@io.swagger.v3.oas.annotations.tags.Tag(name = "MainController")
public class MainController {
    @Autowired
    private StudentsRepository repo;

    @GetMapping("/")
    public String greeting( Map<String, Object> model) {
        model.put("title", "Главная страница");
        return "home";
    }


}
