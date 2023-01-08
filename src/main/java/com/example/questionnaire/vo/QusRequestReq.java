package com.example.questionnaire.vo;

import java.util.List;
import java.util.Map;

public class QusRequestReq {

	private String title;

	private String name;

	private String phoneNum;

	private String email;

	private String age;

	private String sex;

	private List<String> qus;

	private Map<String, String> options_ans;
	
	private Map<String, String> ansMap;
	
	public QusRequestReq() {
		
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

	public List<String> getQus() {
		return qus;
	}

	public void setQus(List<String> qus) {
		this.qus = qus;
	}

	public Map<String, String> getOptions_ans() {
		return options_ans;
	}

	public void setOptions_ans(Map<String, String> options_ans) {
		this.options_ans = options_ans;
	}

	public Map<String, String> getAnsMap() {
		return ansMap;
	}

	public void setAnsMap(Map<String, String> ansMap) {
		this.ansMap = ansMap;
	}

	


}
