package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.repository.StudentsRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
@Tag(name = "WelcomeController")
public class WelcomeController {
    private final StudentsRepository repo;
    @Autowired
    public WelcomeController(StudentsRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        model.put("title", "Главная страница");
        return "home";
    }
}
