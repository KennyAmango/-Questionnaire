package com.example.questionnaire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questionnaire.entity.QusDetails;

@Repository
public interface QusDetailsDao extends JpaRepository<QusDetails, Integer>{
	
	public List<QusDetails> findAllByTitle(String title);

}
