package com.hieubq.UploadImage;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hieubq.Beans.UploadFolder;
import com.hieubq.Service.UploadService;

@CrossOrigin("*")
@RestController
public class UploadImageRestController {
	
	private static final Logger logger = Logger.getLogger(UploadImageRestController.class.getName());
	@Autowired
	UploadService uploadService;

	@PostMapping("/rest/upload/images/{folder}")
	public JsonNode upload(@PathVariable("file") MultipartFile file, @PathVariable("folder") String folder) {
		File savedFile = uploadService.save(file, folder);
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("name", savedFile.getName());
		node.put("size", savedFile.length());
		return node;
	}
	
	
	@PostMapping("/rest/upload/images/lst")
	public JsonNode uploadLst(@PathVariable("file") MultipartFile[] files) {
//		File savedFile = uploadService.save(file, folder);
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
//		node.put("name", savedFile.getName());
//		node.put("size", savedFile.length());

//			 if (files[i].isFile()) {
//				    System.out.println("File " + file[i].getName());
//				  } else if (files[i].isDirectory()) {
//				    System.out.println("Directory " + files[i].getName());
//				  }
			if (files == null || files.length == 0) {
				throw new RuntimeException("You must select at least one file for uploading");
			}

			StringBuilder sb = new StringBuilder(files.length);
			for (int i = 0; i < files.length; i++) {
				System.out.println(files[i].getName());
			}
		
		return node;
	}
	
	@PostMapping("/upload/multiple/files")
	public ResponseEntity<JsonNode> uploadData(@RequestParam("multipleFiles") MultipartFile[] files) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		if (files == null || files.length == 0) {
			throw new RuntimeException("You must select at least one file for uploading");
		}

		StringBuilder sb = new StringBuilder(files.length);

		for (int i = 0; i < files.length; i++) {
			InputStream inputStream = files[i].getInputStream();
			String originalName = files[i].getOriginalFilename();
			String name = files[i].getName();
			String contentType = files[i].getContentType();
			long size = files[i].getSize();

			sb.append("File Name: " + originalName + "\n");

			logger.info("InputStream: " + inputStream);
			logger.info("OriginalName: " + originalName);
			logger.info("Name: " + name);
			logger.info("ContentType: " + contentType);
			logger.info("Size: " + size);
		}

		// Do processing with uploaded file data in Service layer
//		return new ResponseEntity<String>(sb.toString(), HttpStatus.OK);
		return ResponseEntity.ok(node);
	}

	@GetMapping("/get-all-folder")
	public List<String> getAllFolder() {
		List<String> lstFile = new ArrayList<String>();
		try {
			File[] listOfFiles = uploadService.getAllFolder();

			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println("File " + listOfFiles[i].getName());

				} else if (listOfFiles[i].isDirectory()) {
					System.out.println("Directory " + listOfFiles[i].getName());
					lstFile.add(listOfFiles[i].getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstFile;
	}

	@GetMapping("/get-all-file/{folder}")
	public List<String> getAllFile(@PathVariable("folder") String folder) {
		List<String> lstFile = new ArrayList<String>();
		try {
			File[] listOfFiles = uploadService.getAllFile(folder);

			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println("File " + listOfFiles[i].getName());
					lstFile.add(listOfFiles[i].getName());
				} else if (listOfFiles[i].isDirectory()) {
					System.out.println("Directory " + listOfFiles[i].getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstFile;
	}

	@DeleteMapping("/delete-file/{folder}/{fileName}")
	public Boolean deleteFile(@PathVariable("folder") String folder, @PathVariable("fileName") String fileName) {
		return uploadService.removeFile(folder, fileName);
	}
	
	@PostMapping("/create-folder")
	public File createFolder(@RequestBody UploadFolder folder) {
		return uploadService.createFolder(folder.getFolderName().trim());
	}
}
