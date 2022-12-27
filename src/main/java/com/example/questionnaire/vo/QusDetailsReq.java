package com.example.questionnaire.vo;

import java.util.List;
import java.util.Map;

public class QusDetailsReq {

	private String title;

	private Map<String, List<String>> options;

	private boolean multipleChoice;

	public QusDetailsReq() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Map<String, List<String>> getOptions() {
		return options;
	}

	public void setOptions(Map<String, List<String>> options) {
		this.options = options;
	}

	public boolean isMultipleChoice() {
		return multipleChoice;
	}

	public void setMultipleChoice(boolean multipleChoice) {
		this.multipleChoice = multipleChoice;
	}

}
