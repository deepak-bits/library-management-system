package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.DTO.RequestDto.StudentRequestDto;
import com.example.librarymanagementsystem.DTO.RequestDto.UpdateStudentMobRequestDto;
import com.example.librarymanagementsystem.DTO.ResponseDto.UpdateStudentMobResponseDto;
import com.example.librarymanagementsystem.entity.Student;
import com.example.librarymanagementsystem.exceptions.StudentNotFoundException;

public interface StudentService {

    public String addStudent(StudentRequestDto studentRequestDto);

    public UpdateStudentMobResponseDto updateMobile(UpdateStudentMobRequestDto updateStudentMobRequestDto) throws StudentNotFoundException;

    public Student getStudentById(int id);
}
