package com.API.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.API.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long>{

}
