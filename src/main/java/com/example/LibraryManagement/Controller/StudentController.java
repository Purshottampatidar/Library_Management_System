package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.Entities.Student;
import com.example.LibraryManagement.ResponseObjects.BasicDetailsStudentResponse;
import com.example.LibraryManagement.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping("/add")
    public String addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }


    @GetMapping("/get")
    public Student getStudent(@RequestParam("studentId") Integer studentId) {
        return studentService.getStudent(studentId);
    }

    @GetMapping("/basicDetails")
    public BasicDetailsStudentResponse getBasicDetails(@RequestParam("studentId") Integer studentId) {
        return studentService.getBasicDetails(studentId);
    }
}
