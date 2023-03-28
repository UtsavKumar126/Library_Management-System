package com.spring.librarymanagementsystem.Service;

import com.spring.librarymanagementsystem.DTOs.StuUpdateEmailReqDto;
import com.spring.librarymanagementsystem.DTOs.StudentResponseDto;
import com.spring.librarymanagementsystem.Entity.Card;
import com.spring.librarymanagementsystem.Entity.Student;
import com.spring.librarymanagementsystem.Enums.CardStatus;
import com.spring.librarymanagementsystem.Exception.StudentNotfoundExep;
import com.spring.librarymanagementsystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public String addBook(Student student) {

        Card card=new Card();
        card.setValidityDate("03/2025");
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudent(student);

        student.setCard(card);

        studentRepository.save(student);

        return "Added Successfully";
    }

    public List<Student> findAll() {

        return studentRepository.findAll();
    }

    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    public StudentResponseDto updateEmail(StuUpdateEmailReqDto stuUpdateEmailReqDto) throws StudentNotfoundExep {
        Student student;
        try {
            student = studentRepository.findById(stuUpdateEmailReqDto.getId()).get();
        }
        catch (Exception e){
            throw new StudentNotfoundExep("Student does not Exist");
        }
        student.setEmail(stuUpdateEmailReqDto.getNewEmail());

        studentRepository.save(student);

        Student updated=studentRepository.findById(stuUpdateEmailReqDto.getId()).get();
        StudentResponseDto studentResponseDto=new StudentResponseDto();
        studentResponseDto.setId(updated.getId());
        studentResponseDto.setEmail(updated.getEmail());
        studentResponseDto.setName(updated.getName());

        return studentResponseDto;
    }
}
