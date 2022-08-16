package com.hieubq.ServiceIml;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hieubq.Service.UploadService;

@Service
public class UploadServiceIml implements UploadService {
	@Autowired
	ServletContext app;

	@Override
	public File save(MultipartFile file, String folder) {
		File dir = new File(app.getRealPath("/assets/images/" + folder));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String s = System.currentTimeMillis() + file.getOriginalFilename();
		String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));

		try {
			File savedFile = new File(dir, name);
			file.transferTo(savedFile);
			System.out.println(savedFile.getAbsolutePath());
			return savedFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public File[] getAllFile(String folder) {
		File dir = new File(app.getRealPath("/assets/images/" + folder));
		File[] listOfFiles = dir.listFiles();
		return listOfFiles;

//		for (int i = 0; i < listOfFiles.length; i++) {
//		  if (listOfFiles[i].isFile()) {
//		    System.out.println("File " + listOfFiles[i].getName());
//		  } else if (listOfFiles[i].isDirectory()) {
//		    System.out.println("Directory " + listOfFiles[i].getName());
//		  }
//		}
	}

	@Override
	public File[] getAllFolder() {
		File folder = new File(app.getRealPath("/assets/images"));
		File[] listOfFiles = folder.listFiles();
		return listOfFiles;
	}

	@Override
	public Boolean removeFile(String folder, String fileName) {
		File file = new File(app.getRealPath("/assets/images/" + folder + "/" + fileName));
		if (file.delete()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public File createFolder(String folder) {
		File dir = new File(app.getRealPath("/assets/images/" + folder));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}
}
