package com.noprizal.test_excel.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.noprizal.test_excel.exceptions.DuplicateDataException;
import com.noprizal.test_excel.models.Student;
import com.noprizal.test_excel.repository.StudentRepository;

@Service
public class UploadService {
	@Autowired
	private StudentRepository studentRepo;

	public void saveExcelData(MultipartFile file) throws IOException {
		Workbook workbook = new XSSFWorkbook(file.getInputStream());
		Sheet firstSheet = workbook.getSheetAt(0);

		List<Student> students = new ArrayList<>();
		List<Integer> duplicateNims = new ArrayList<>();
		Iterator<Row> rows = firstSheet.iterator();

		Integer firstRow = 0;
		while (rows.hasNext()) {
			Row row = rows.next();
			// skip header
			if (firstRow == 0) {
				firstRow++;
				continue;
			}

			Iterator<Cell> cells = row.iterator();
			Integer cellIndex = 0;
			Student student = new Student();

			while (cells.hasNext()) {
				Cell cell = cells.next();
				switch (cellIndex) {
				case 0 -> student.setName(cell.getStringCellValue());
				case 1 -> student.setNim((int) cell.getNumericCellValue());
				case 2 -> student.setFaculty(cell.getStringCellValue());
				case 3 -> student.setMajor(cell.getStringCellValue());
				case 4 -> student.setGpa(cell.getNumericCellValue());
				}
				cellIndex++;
			}

			if (studentRepo.existsByNim(student.getNim())) {
				duplicateNims.add(student.getNim());
			} else {
				students.add(student);
			}
		}

		workbook.close();

		if (!duplicateNims.isEmpty()) {
			throw new DuplicateDataException(duplicateNims);
		}

		studentRepo.saveAll(students);
	}
}
