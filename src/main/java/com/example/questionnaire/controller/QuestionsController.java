package com.example.questionnaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.questionnaire.constants.QuestionsRtnCode;
import com.example.questionnaire.service.ifs.QuestionsService;
import com.example.questionnaire.vo.QuestionsReq;
import com.example.questionnaire.vo.QuestionsRes;
import com.example.questionnaire.vo.QuestionsResList;

@RestController
public class QuestionsController {
	
	@Autowired
	QuestionsService questionsService;
	
	@PostMapping(value = "/api/createQuestionsWihoutBackGround")
	public QuestionsRes createQuestions(@RequestBody QuestionsReq req) {
		
		return questionsService.createQuestionWihoutBackGround(req);
		
	}
	
	@PostMapping(value = "/api/getAllQuestions")
	public QuestionsResList getAllQuestions() {
		
		return questionsService.getAllQuestions();
		
	}
	
	@PostMapping(value = "/api/getQuestionsByTitleOrDate")
	public QuestionsResList getQuestionsByTitleOrDate(@RequestBody QuestionsReq req) {
		
		return questionsService.getQuestionsByTitleOrDate(req);
		
	}
	
	@PostMapping(value = "/api/createQuestionnaire")
	public QuestionsRes createQuestionnaire(@RequestBody QuestionsReq req) {
		
		if(!StringUtils.hasText(req.getTitle())) {
			return new QuestionsRes(QuestionsRtnCode.TITLE_EMPTY.getMessage());
		}else if(req.getStartTime() != null && req.getEndTime() == null) {
			return new QuestionsRes(QuestionsRtnCode.ENDTIME_EMPTY.getMessage());
		}else if(req.getStartTime() == null && req.getEndTime() != null) {
			return new QuestionsRes(QuestionsRtnCode.STARTTIME_EMPTY.getMessage());
		}else if(!StringUtils.hasText(req.getDetails())) {
			return new QuestionsRes(QuestionsRtnCode.DTAILS_EMPTY.getMessage());
		}
		
		return questionsService.createQuestionnaire(req);
		
	}

}
