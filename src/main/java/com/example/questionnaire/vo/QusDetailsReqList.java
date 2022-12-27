package com.example.questionnaire.vo;

import java.util.List;
import java.util.Map;

public class QusDetailsReqList {

	private List<QusDetailsReq> reqList;

	private List<Map<String, List<String>>> ansList;

	public QusDetailsReqList() {

	}

	public List<QusDetailsReq> getReqList() {
		return reqList;
	}

	public void setReqList(List<QusDetailsReq> reqList) {
		this.reqList = reqList;
	}

	public List<Map<String, List<String>>> getAnsList() {
		return ansList;
	}

	public void setAnsList(List<Map<String, List<String>>> ansList) {
		this.ansList = ansList;
	}

}
