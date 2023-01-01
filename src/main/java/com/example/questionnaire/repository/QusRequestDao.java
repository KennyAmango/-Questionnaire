package com.example.questionnaire.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questionnaire.entity.QusRequest;

@Repository
public interface QusRequestDao extends JpaRepository<QusRequest, UUID>{
	
	public List<QusRequest> findAllByTitle(String title);

}
