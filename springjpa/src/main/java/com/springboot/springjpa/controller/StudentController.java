package com.springboot.springjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.springjpa.model.Student;
import com.springboot.springjpa.service.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("title", "HomePage-Student List");
        model.addAttribute("sortField", "roll");
        model.addAttribute("sortDir", "ASC");
        return "home.html";
    }

    @GetMapping("/studentform")
    public String ShowAddNewStudentForm(Model model) {
        model.addAttribute("title", "Add New Student Form-Student List");
        model.addAttribute("student", new Student());
        return "addstudentform.html";
    }

    @GetMapping("/showstudents/{pageNo}")
    public String viewStudentsList(@PathVariable(value = "pageNo") int pageNo,
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir, Model model) {
        int pageSize = 10;
        Page<Student> page = studentService.findPaginated(pageNo, pageSize, sortField, sortDir);

        model.addAttribute("title", "Student List");
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalStudents", page.getTotalElements());
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("sortField", sortField);
        model.addAttribute("students", page.getContent());
        return "showallstudents.html";
    }

    @PostMapping("/add_student")
    public String addNewStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/";
    }
}
