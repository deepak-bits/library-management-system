package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.entity.Author;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    AuthorRepository authorRepository;
    BookRepository bookRepository;

    @Override
    public String addBook(Book book) throws Exception {
        Author author;
        try {
            author = authorRepository.findById(book.getAuthor().getId()).get();
        } catch (Exception e) {
            throw new Exception("Author not present");
        }

        author.getBooks().add(book);
        book.setAuthor(author);
        authorRepository.save(author);
        return "Book added";
    }
}
