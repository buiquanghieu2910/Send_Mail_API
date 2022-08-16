package com.hieubq.UploadImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
