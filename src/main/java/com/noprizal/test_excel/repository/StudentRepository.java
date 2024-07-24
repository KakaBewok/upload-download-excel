package com.noprizal.test_excel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noprizal.test_excel.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
