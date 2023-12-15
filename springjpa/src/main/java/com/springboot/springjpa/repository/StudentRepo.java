package com.springboot.springjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.springjpa.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
