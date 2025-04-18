package com.example.trablho_prog_web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping(path = "home")
public class MainViewerController {
    
    @GetMapping("/") // GET http://localhost:8080/home/
    public String getHome(){
        return "index.html";
    }

    @GetMapping("/login") // GET http://localhost:8080/home/
    public String getLogin(){
        return "login.html";
    }

    @GetMapping("/cadastro") // GET http://localhost:8080/home/
    public String getCadastro(){
        return "cadastro.html";
    }

}
