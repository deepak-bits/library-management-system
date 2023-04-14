package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.entity.Card;
import com.example.librarymanagementsystem.entity.Student;
import com.example.librarymanagementsystem.enums.CardStatus;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public String addStudent(Student student) {
        // Generate a new card for the student
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setValidTill(LocalDate.parse("2024-01-06"));
        card.setStudent(student);

        // set card for the student
        student.setCard(card);
        studentRepository.save(student);
        return "Student added successfully.";
    }
}
