package com.example.questionnaire.entity;

public class PercentageStatistics {
	
	private String quesion;
	
	private String option;
	
	private double percentage;
	
	public PercentageStatistics() {
		
	}
	
	

	public PercentageStatistics(String quesion, String option, double percentage) {
		this.quesion = quesion;
		this.option = option;
		this.percentage = percentage;
	}



	public String getQuesion() {
		return quesion;
	}

	public void setQuesion(String quesion) {
		this.quesion = quesion;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	

}
