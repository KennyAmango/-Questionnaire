package com.example.questionnaire.vo;

public class QusRequestRes {

	private String title;

	private String name;

	private String phoneNum;

	private String email;

	private String age;

	private String sex;

	private String qus;

	private String options_ans;
	
	private String message;

	public QusRequestRes(String message) {

		this.message = message;
	}
	
	public QusRequestRes() {

	}

	public QusRequestRes(String title, String name, String phoneNum, String email, String age, String sex, String qus,
			String options_ans) {
		this.title = title;
		this.name = name;
		this.phoneNum = phoneNum;
		this.email = email;
		this.age = age;
		this.sex = sex;
		this.qus = qus;
		this.options_ans = options_ans;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getQus() {
		return qus;
	}

	public void setQus(String qus) {
		this.qus = qus;
	}

	public String getOptions_ans() {
		return options_ans;
	}

	public void setOptions_ans(String options_ans) {
		this.options_ans = options_ans;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
