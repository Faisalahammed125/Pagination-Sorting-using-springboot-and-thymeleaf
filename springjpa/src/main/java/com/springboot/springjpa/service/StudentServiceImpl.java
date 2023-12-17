package com.springboot.springjpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.springjpa.model.Student;
import com.springboot.springjpa.repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public void saveStudent(Student student) {
        this.studentRepo.save(student);
    }

    @Override
    public Student getStudentById(int id) {
        Optional<Student> optional = studentRepo.findById(id);
        Student student = null;
        if (optional.isPresent()) {
            student = optional.get();
        } else {
            throw new RuntimeException("Student Not Found!!");
        }
        return student;
    }

    @Override
    public void deleteStudentById(int id) {
        this.studentRepo.deleteById(id);
    }

    @Override
    public Page<Student> findPaginated(int PageNo, int PageSize, String sortField, String sortDir) {
        Sort sort;
        if (sortDir.matches("ASC")) {
            sort = Sort.by(sortField).ascending();
        } else {
            sort = Sort.by(sortField).descending();
        }
        Pageable pageable = PageRequest.of(PageNo - 1, PageSize, sort);
        return this.studentRepo.findAll(pageable);
    }
}
