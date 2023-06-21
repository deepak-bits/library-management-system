package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.DTO.RequestDto.StudentRequestDto;
import com.example.librarymanagementsystem.DTO.RequestDto.UpdateStudentMobRequestDto;
import com.example.librarymanagementsystem.DTO.ResponseDto.StudentResponseDto;
import com.example.librarymanagementsystem.DTO.ResponseDto.UpdateStudentMobNoResponseDto;
import com.example.librarymanagementsystem.exceptions.StudentNotFoundException;
import com.example.librarymanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody StudentRequestDto studentRequestDto) {
        return studentService.addStudent(studentRequestDto);
    }

    @PutMapping("/update_mobile")
    public UpdateStudentMobNoResponseDto updateMobNo(@RequestBody UpdateStudentMobRequestDto updateStudentMobRequestDto) throws StudentNotFoundException {
        return studentService.updateMobNo(updateStudentMobRequestDto);
    }

    // find a student by id
    @GetMapping("/get_student")
    public StudentResponseDto getStudent(@RequestParam("id") int id) throws StudentNotFoundException {
        return studentService.getStudentById(id);
    }

    // find all the students
    @GetMapping("/get_all")
    public List<StudentResponseDto> getAll() {
        return studentService.getAll();
    }

    // Delete a student by id
    @DeleteMapping("/delete")
    public String deleteStudent(@RequestParam("id") int id) throws StudentNotFoundException {
        return studentService.deleteStudent(id);
    }

}
