package com.example.questionnaire.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//問卷裡的問題
@Entity
@Table(name = "qus_details")
public class QusDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "title")
	private String title;

	@Column(name = "qus")
	private String qus;

	@Column(name = "options")
	private String options;

	@Column(name = "multiple_choice")
	private boolean multipleChoice;

	public QusDetails(String title, String qus, String options, boolean multipleChoice) {
		this.title = title;
		this.qus = qus;
		this.options = options;
		this.multipleChoice = multipleChoice;
	}

	public QusDetails() {

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

	public String getQus() {
		return qus;
	}

	public void setQus(String qus) {
		this.qus = qus;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public boolean isMultipleChoice() {
		return multipleChoice;
	}

	public void setMultipleChoice(boolean multipleChoice) {
		this.multipleChoice = multipleChoice;
	}

}
