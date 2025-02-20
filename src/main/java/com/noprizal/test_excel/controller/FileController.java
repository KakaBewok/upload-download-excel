package com.noprizal.test_excel.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.noprizal.test_excel.services.DownloadService;
import com.noprizal.test_excel.services.UploadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Upload file excel", description = "API untuk mengupload file excel ke database table Student. Dengan urutan kolom (id, name, nim, faculty, major, gpa)")
public class FileController {

	@Autowired
	private UploadService uploadService;
	@Autowired
	private DownloadService downloadService;

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			uploadService.saveExcelData(file);
			return ResponseEntity.status(HttpStatus.OK).body("File uploaded and data saved successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("An error occurred while processing the file. " + e.getMessage());
		}
	}

	@GetMapping("/download")
	@Operation(parameters = {
			@Parameter(name = "fileName", description = "Contoh: data-siswa", required = false) })
	public ResponseEntity<InputStreamResource> downloadFile(@RequestParam(required = false) String fileName) throws IOException {
		ByteArrayInputStream inputStream = downloadService.loadExcel();

		fileName = fileName == null || fileName == "" ? "student" : fileName;
		String fullFileName = fileName + ".xlsx" ;

		return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=" + fullFileName)
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(new InputStreamResource(inputStream));
	}
}
