package com.noprizal.test_excel.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
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

	public ByteArrayInputStream downloadExcelData() {
		List<Student> students = studentRepo.findAll();

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("student");

		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Name");
		headerRow.createCell(1).setCellValue("Age");

		Integer rowIndex = 1;
		for (Student student : students) {
			Row row = sheet.createRow(rowIndex++);
			
//			row.createCell(0)
//			row.createCell(0)
//			row.createCell(0)
//			row.createCell(0)
//			row.createCell(0)
//			row.createCell(0)
		}
		//(id, name, nim, faculty, major, gpa)

		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		workbook.write(out);
//		workbook.close();

		return new ByteArrayInputStream(out.toByteArray());
	}
}
