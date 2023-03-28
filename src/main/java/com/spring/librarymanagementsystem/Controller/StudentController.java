package com.spring.librarymanagementsystem.Controller;

import com.spring.librarymanagementsystem.DTOs.StuUpdateEmailReqDto;
import com.spring.librarymanagementsystem.DTOs.StudentResponseDto;
import com.spring.librarymanagementsystem.Entity.Book;
import com.spring.librarymanagementsystem.Entity.Student;
import com.spring.librarymanagementsystem.Exception.StudentNotfoundExep;
import com.spring.librarymanagementsystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public String addStudent(@RequestBody Student student){
        return studentService.addBook(student);
    }

    @GetMapping("/find_all")
    public List<Student> find_all(){
        return studentService.findAll();
    }

    @GetMapping("findByName")
    public List<Student>findByname(@RequestParam("name") String name){
        return studentService.findByName(name);
    }

    @PutMapping("/Update_Email")
    public ResponseEntity updateEmail(@RequestBody StuUpdateEmailReqDto stuUpdateEmailReqDto){

        try {
            StudentResponseDto studentResponseDto=studentService.updateEmail(stuUpdateEmailReqDto);
            return new ResponseEntity(studentResponseDto, HttpStatus.CREATED);
        } catch (StudentNotfoundExep e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
