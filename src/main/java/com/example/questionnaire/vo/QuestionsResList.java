package com.example.questionnaire.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionsResList {

	private List<QuestionsRes> questionsResList;

	private String message;
	
	private int num;

	public QuestionsResList() {

	}

	public QuestionsResList(String message) {

		this.message = message;
	}

	public List<QuestionsRes> getQuestionsResList() {
		return questionsResList;
	}

	public void setQuestionsResList(List<QuestionsRes> questionsResList) {
		this.questionsResList = questionsResList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
