package com.example.questionnaire.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "qus_request")
//答卷者輸入的資訊
public class QusRequest {

	@Id
	@Column(name = "uuid")
	@Type(type = "uuid-char")
	private UUID uuid;

	@Column(name = "title")
	private String title;

	@Column(name = "name")
	private String name;

	@Column(name = "phone_num")
	private String phoneNum;

	@Column(name = "email")
	private String email;

	@Column(name = "age")
	private String age;

	@Column(name = "sex")
	private String sex;
	
	@Column(name = "qus")
	private String qus;

	@Column(name = "options_ans")
	private String options_ans;

	public QusRequest() {

	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
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


}
