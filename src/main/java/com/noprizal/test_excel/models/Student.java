package com.noprizal.test_excel.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter and setter by Lombok jar
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Students")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer nim;
	private String name;
	private String major;
	private String faculty;
	private Double gpa;

	public Student(Integer nim, String name, String major, String faculty, Double gpa) {
		super();
		this.nim = nim;
		this.name = name;
		this.major = major;
		this.faculty = faculty;
		this.gpa = gpa;
	}
}
