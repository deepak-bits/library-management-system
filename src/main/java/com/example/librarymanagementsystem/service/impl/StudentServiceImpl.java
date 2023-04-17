package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.DTO.RequestDto.StudentRequestDto;
import com.example.librarymanagementsystem.DTO.RequestDto.UpdateStudentMobRequestDto;
import com.example.librarymanagementsystem.DTO.ResponseDto.UpdateStudentMobResponseDto;
import com.example.librarymanagementsystem.entity.Card;
import com.example.librarymanagementsystem.entity.Student;
import com.example.librarymanagementsystem.enums.CardStatus;
import com.example.librarymanagementsystem.exceptions.StudentNotFoundException;
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
    public String addStudent(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setAge(studentRequestDto.getAge());
        student.setName(studentRequestDto.getName());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setMobNo(studentRequestDto.getMobNo());

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

    @Override
    public UpdateStudentMobResponseDto updateMobile(UpdateStudentMobRequestDto updateStudentMobRequestDto) throws StudentNotFoundException {
        Student student;
        try{
            student = studentRepository.findById(updateStudentMobRequestDto.getId()).get();
            student.setMobNo(updateStudentMobRequestDto.getMobNo());
            Student updatedStudent = studentRepository.save(student);

            // prepare response DTO
            UpdateStudentMobResponseDto updateStudentMobResponseDto = new UpdateStudentMobResponseDto();
            updateStudentMobResponseDto.setName(updatedStudent.getName());
            updateStudentMobResponseDto.setMobNo(updatedStudent.getMobNo());

            return updateStudentMobResponseDto;
        } catch (Exception e) {
            throw new StudentNotFoundException("Invalid student id");
        }

    }

    @Override
    public Student getStudentById(int id) {
        return studentRepository.findById(id).get();
    }
}
