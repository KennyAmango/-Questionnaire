package com.example.questionnaire.vo;

import java.time.LocalDate;
import java.util.List;

public class QuestionsReq {
	
	private Integer id;

	private String title;

	private String details;

	private LocalDate startTime;

	private LocalDate endTime;
	
	private String qus;
	
	private List<String> qusList;
	
	private String ans;
	
	private String qusOption;
	
	public QuestionsReq(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public LocalDate getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDate startTime) {
		this.startTime = startTime;
	}

	public LocalDate getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDate endTime) {
		this.endTime = endTime;
	}

	public String getQus() {
		return qus;
	}

	public void setQus(String qus) {
		this.qus = qus;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public String getQusOption() {
		return qusOption;
	}

	public void setQusOption(String qusOption) {
		this.qusOption = qusOption;
	}
	

}
