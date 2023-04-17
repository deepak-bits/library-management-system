package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.DTO.ResponseDto.AuthorResponseDto;
import com.example.librarymanagementsystem.entity.Author;

public interface AuthorService {

    public String addAuthor(Author author);

    public AuthorResponseDto getByEmail(String email);
}
