package com.noprizal.test_excel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.noprizal.test_excel.services.UploadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Upload file excel", description = "API untuk mengupload file excel")
public class FileController {

	@Autowired
	private UploadService uploadService;

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Operation(parameters = {
			@Parameter(name = "file", description = "upload file excel dengan urutan kolom (id, name, nim, faculty, major, gpa)") })
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			uploadService.saveExcelData(file);
			return ResponseEntity.status(HttpStatus.OK)
					.body("File uploaded and data saved successfully! " + file.getName());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while processing the file. " + e.getMessage());
		}
	}
}
