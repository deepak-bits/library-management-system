package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.DTO.RequestDto.StudentRequestDto;
import com.example.librarymanagementsystem.DTO.RequestDto.UpdateStudentMobRequestDto;
import com.example.librarymanagementsystem.DTO.ResponseDto.CardResponseDto;
import com.example.librarymanagementsystem.DTO.ResponseDto.StudentResponseDto;
import com.example.librarymanagementsystem.DTO.ResponseDto.UpdateStudentMobNoResponseDto;
import com.example.librarymanagementsystem.entity.Card;
import com.example.librarymanagementsystem.entity.Student;
import com.example.librarymanagementsystem.enums.CardStatus;
import com.example.librarymanagementsystem.exceptions.StudentNotFoundException;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public String addStudent(StudentRequestDto studentRequestDto) {

        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setMobNo(studentRequestDto.getMobNo());

        // Auto-generate a new card for the student
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setValidTill(LocalDate.parse("2024-01-06"));
        card.setStudent(student);

        // allocate the generated card to the student
        student.setCard(card);
        studentRepository.save(student);
        return "Student added";
    }

    @Override
    public UpdateStudentMobNoResponseDto updateMobNo(UpdateStudentMobRequestDto updateStudentMobRequestDto) throws StudentNotFoundException {

        try {
            Student student = studentRepository.findById(updateStudentMobRequestDto.getId()).get();
            student.setMobNo(updateStudentMobRequestDto.getMobNo());
            Student updatedStudent = studentRepository.save(student);

            // prepare response Dto
            UpdateStudentMobNoResponseDto updateStudentMobNoResponseDto = new UpdateStudentMobNoResponseDto();
            updateStudentMobNoResponseDto.setName(updatedStudent.getName());
            updateStudentMobNoResponseDto.setMobNo(updatedStudent.getMobNo());

            return updateStudentMobNoResponseDto;
        } catch(Exception e) {
            throw new StudentNotFoundException("Invalid student id");
        }
    }

    @Override
    public StudentResponseDto getStudentById(int id) throws StudentNotFoundException {

        try {
            Student student = studentRepository.findById(id).get();

            // prepare response Dto
            StudentResponseDto studentResponseDto = new StudentResponseDto();
            studentResponseDto.setId(student.getId());
            studentResponseDto.setName(student.getName());
            studentResponseDto.setDepartment(student.getDepartment());
            studentResponseDto.setAge(student.getAge());
            studentResponseDto.setMobNo(student.getMobNo());

            CardResponseDto cardResponseDto = new CardResponseDto();
            cardResponseDto.setIssueDate(student.getCard().getIssueDate());
            cardResponseDto.setCardStatus(student.getCard().getCardStatus());
            cardResponseDto.setUpdatedOn(student.getCard().getUpdatedOn());
            cardResponseDto.setValidTill(student.getCard().getValidTill());
            cardResponseDto.setId(student.getCard().getId());

            studentResponseDto.setCardResponseDto(cardResponseDto);

            return studentResponseDto;

        } catch(Exception e) {
            throw new StudentNotFoundException("Invalid student id");
        }
    }

    @Override
    public List<StudentResponseDto> getAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponseDto> studentResponseDtos = new ArrayList<>();

        for(Student student : students) {
            StudentResponseDto studentResponseDto = new StudentResponseDto();
            studentResponseDto.setId(student.getId());
            studentResponseDto.setName(student.getName());
            studentResponseDto.setDepartment(student.getDepartment());
            studentResponseDto.setAge(student.getAge());
            studentResponseDto.setMobNo(student.getMobNo());

            CardResponseDto cardResponseDto = new CardResponseDto();
            cardResponseDto.setIssueDate(student.getCard().getIssueDate());
            cardResponseDto.setCardStatus(student.getCard().getCardStatus());
            cardResponseDto.setUpdatedOn(student.getCard().getUpdatedOn());
            cardResponseDto.setValidTill(student.getCard().getValidTill());
            cardResponseDto.setId(student.getCard().getId());

            studentResponseDto.setCardResponseDto(cardResponseDto);

            studentResponseDtos.add(studentResponseDto);
        }

        return studentResponseDtos;
    }

    @Override
    public String deleteStudent(int id) throws StudentNotFoundException {
        try {
            Student student = studentRepository.findById(id).get();
        } catch(Exception e) {
            throw new StudentNotFoundException("Invalid student id");
        }
        studentRepository.deleteById(id);
        return "Student deleted successfully";
    }
}
