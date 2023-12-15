package com.springboot.springjpa.service;

import java.util.*;

import org.springframework.data.domain.Page;

import com.springboot.springjpa.model.Student;

public interface StudentService {
    List<Student> getAllStudents();

    void saveStudent(Student student);

    Student getStudentById(int id);

    void deleteStudentById(int id);

    Page<Student> findPaginated(int PageNo, int PageSize);
}
