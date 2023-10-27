package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Entities.Student;
import com.example.LibraryManagement.Repository.StudentRepository;
import com.example.LibraryManagement.ResponseObjects.BasicDetailsStudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JavaMailSender mailSender;

    public String addStudent(Student student) {
        studentRepository.save(student);

        SimpleMailMessage mailMessage=new SimpleMailMessage();

        String body="Hi "+student.getName()+" !"+
                "You have Successfully registered with Library. "+
                "Please Start Issuing book from Library";

        mailMessage.setFrom("springacciojob@gmail.com");
        mailMessage.setTo(student.getEmailId());
        mailMessage.setSubject("Welcome to Library fox!");
        mailMessage.setText(body);

        mailSender.send(mailMessage);

        return "Student Added";
    }

    public Student getStudent(Integer studentId) {
        Student student=studentRepository.findById(studentId).get();
        return student;
    }

    public BasicDetailsStudentResponse getBasicDetails(Integer studentId) {
        Student student=studentRepository.findById(studentId).get();
        BasicDetailsStudentResponse result=new BasicDetailsStudentResponse();
        result.setName(student.getName());
        result.setAge(student.getAge());
        result.setMobNo(student.getContactNo());


        return result;
    }
}
