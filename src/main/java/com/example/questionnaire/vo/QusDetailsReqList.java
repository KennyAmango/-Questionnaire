package com.example.questionnaire.vo;

import java.util.List;
import java.util.Map;

public class QusDetailsReqList {

	private List<QusDetailsReq> reqList;

//	private List<Map<String, List<String>>> ansList;
	
	private Map<String, List<String>> ansMap;
	
	private List<String> ansList;

	public QusDetailsReqList() {

	}

	public List<QusDetailsReq> getReqList() {
		return reqList;
	}

	public void setReqList(List<QusDetailsReq> reqList) {
		this.reqList = reqList;
	}

	public Map<String, List<String>> getAnsMap() {
		return ansMap;
	}

	public void setAnsMap(Map<String, List<String>> ansMap) {
		this.ansMap = ansMap;
	}

	public List<String> getAnsList() {
		return ansList;
	}

	public void setAnsList(List<String> ansList) {
		this.ansList = ansList;
	}


}
