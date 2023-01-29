package com.example.questionnaire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questionnaire.entity.Questions;

@Repository
public interface QuestionsDao extends JpaRepository<Questions, Integer>{
	
	public Questions findTitleById(Integer id);
	
	public List<Questions> findAllByOrderByEndTimeDesc();
	
	public List<Questions> findAllByOrderByIdDesc();
	
	
}
