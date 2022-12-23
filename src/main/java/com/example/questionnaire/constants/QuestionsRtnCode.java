package com.example.questionnaire.constants;

public enum QuestionsRtnCode {
	STARTTIME_EMPTY("400", "開始時間為空"),
	STARTTIME_REQUIRED("400", "開始時間不能早於今日"),
	ENDTIME_EMPTY("400", "結束時間為空"),
	TIME_ERROR("400", "開始時間不能大於結束時間"),
	DTAILS_EMPTY("400", "問卷說明為空"),
	NO_QUESTIONNAIRE("400", "暫無問卷"),
	TITLE_EMPTY("400", "問卷標題為空");
	
	private String code;
	
	private String message;
	
	private QuestionsRtnCode(String code, String message) {
		this.code = code;
		
		this.message = message;
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
