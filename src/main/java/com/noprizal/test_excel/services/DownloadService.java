package com.noprizal.test_excel.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noprizal.test_excel.models.Student;
import com.noprizal.test_excel.repository.StudentRepository;

@Service
public class DownloadService {
	@Autowired
	private StudentRepository studentRepo;

	public ByteArrayInputStream loadExcel() throws IOException {
		List<Student> students = studentRepo.findAll();

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("student");

		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("id");
		headerRow.createCell(1).setCellValue("name");
		headerRow.createCell(2).setCellValue("nim");
		headerRow.createCell(3).setCellValue("faculty");
		headerRow.createCell(4).setCellValue("major");
		headerRow.createCell(5).setCellValue("gpa");

		Integer rowIndex = 1;
		for (Student student : students) {
			Row row = sheet.createRow(rowIndex++);
			row.createCell(0).setCellValue(student.getId());
			row.createCell(1).setCellValue(student.getName());
			row.createCell(2).setCellValue(student.getNim());
			row.createCell(3).setCellValue(student.getFaculty());
			row.createCell(4).setCellValue(student.getMajor());
			row.createCell(5).setCellValue(student.getGpa());
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		workbook.write(out);
		workbook.close();

		return new ByteArrayInputStream(out.toByteArray());
	}
}
