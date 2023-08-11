package com.API.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import  com.API.Repository.TutorialRepository;
import com.API.helper.ExcelHelper;
import com.API.model.Tutorial;

@Service
public class ExcelserviceImp{

	@Autowired
	public TutorialRepository TutorialRepository;
	

	 public void save(MultipartFile file) {
		    try {
		      List<Tutorial> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
		      TutorialRepository.saveAll(tutorials);
		    } catch (IOException e) {
		      throw new RuntimeException("fail to store excel data: " + e.getMessage());
		    }
		  }

		  public List<Tutorial> getAllTutorials() {
		    return TutorialRepository.findAll();
		  }

		public List<Tutorial> getdata() {
			// TODO Auto-generated method stub
			return TutorialRepository.findAll();
		}

		

}
