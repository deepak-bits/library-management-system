![LMS_diag](https://github.com/deepak-bits/library-management-system/assets/40654292/32df74b7-30cc-44a4-b14b-79f6f432a6a8)

![LMS_Swagger](https://github.com/deepak-bits/library-management-system/assets/40654292/ed4dc59d-c065-4e43-9da8-a8e86e4c2355)

# Library Management System Backend API Documentation

This document provides documentation for the backend APIs of the Library Management System. The Library Management System is designed to handle various operations related to students, authors, books, and transactions in a library.

## Base URL

The base URL for accessing the APIs is: `http://localhost:8080/`

## APIs

### Add Student

- **URL:** `/add`
- **Method:** `POST`
- **Description:** Add a new student to the library.
- **Request Body:** StudentRequestDto object
- **Response:** Success message on success or HTTP 400 Bad Request if the request is invalid.

### Update Student Mobile Number

- **URL:** `/update_mobile`
- **Method:** `PUT`
- **Description:** Update the mobile number of a student.
- **Request Body:** UpdateStudentMobRequestDto object
- **Response:** Updated mobile number on success or HTTP 404 Not Found if the student is not found.

### Get Student by ID

- **URL:** `/get_student`
- **Method:** `GET`
- **Description:** Get a student by their ID.
- **Query Parameters:** `id` (int) - ID of the student to retrieve.
- **Response:** StudentResponseDto object on success or HTTP 404 Not Found if the student is not found.

### Get All Students

- **URL:** `/get_all`
- **Method:** `GET`
- **Description:** Get all the students in the library.
- **Response:** List of StudentResponseDto objects.

### Delete Student

- **URL:** `/delete`
- **Method:** `DELETE`
- **Description:** Delete a student from the library by their ID.
- **Query Parameters:** `id` (int) - ID of the student to delete.
- **Response:** Success message on success or HTTP 404 Not Found if the student is not found.

### Add Author

- **URL:** `/add`
- **Method:** `POST`
- **Description:** Add a new author to the library.
- **Request Body:** Author object
- **Response:** Success message on success.

### Get Author by Email

- **URL:** `/get_by_email`
- **Method:** `GET`
- **Description:** Get an author by their email address.
- **Query Parameters:** `email` (string) - Email address of the author to retrieve.
- **Response:** AuthorResponseDto object on success.

### Add Book

- **URL:** `/add`
- **Method:** `POST`
- **Description:** Add a new book to the library.
- **Request Body:** Book object
- **Response:** Success message on success or exception if an error occurs.

### Issue Book

- **URL:** `/add`
- **Method:** `POST`
- **Description:** Issue a book to a student.
- **Request Body:** IssueBookRequestDto object
- **Response:** IssueBookResponseDto object on success or exception if an error occurs.


