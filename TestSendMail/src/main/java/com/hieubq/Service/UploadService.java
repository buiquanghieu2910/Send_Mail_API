package com.hieubq.Service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	File save(MultipartFile file, String folder);
	File createFolder(String folder);
	File[] getAllFolder();
	File[] getAllFile(String folder);
	
	Boolean removeFile(String folder, String fileName);
}
