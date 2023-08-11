package com.API.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.API.model.Tutorial;

public interface ExcelService {

	List<Tutorial> getdata();

	 public void save(MultipartFile file);

	
}
