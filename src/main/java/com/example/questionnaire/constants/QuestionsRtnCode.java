package com.example.questionnaire.constants;

public enum QuestionsRtnCode {
	CREATE_QUESTIONNAIRE_SUCCESS("200", "創建問卷成功"),
	CATCH_REQUEST_SUCCESS("200", "答卷者資訊儲存成功"),
	STARTTIME_EMPTY("400", "開始時間為空"),
	STARTTIME_REQUIRED("400", "開始時間不能早於今日"),
	ENDTIME_EMPTY("400", "結束時間為空"),
	TIME_ERROR("400", "開始時間不能大於結束時間"),
	PAGE_ERROR("400", "分頁數字錯誤"),
	DTAILS_EMPTY("400", "問卷說明為空"),
	TITLE_AND_TIME_EMPTY("400", "標題及時間為空"),
	NO_QUESTIONNAIRE("400", "暫無問卷"),
	NAME_EMPTY("400", "姓名為空"),
	PHONENUM_EMPTY("400", "手機號碼為空"),
	EMAIL_EMPTY("400", "email為空"),
	AGE_EMPTY("400", "年齡為空"),
	QUS_EMPTY("400", "問題輸入為空"),
	ANSOPTIONS_EMPTY("400", "問題選項為空"),
	ANS_EMPTY("400", "題目答案為空"),
	SEX_EMPTY("400", "性別為空"),
	QUTIONNAIRE_NO_FOUND("400", "查無此問卷"),
	QUTIONNAIRE_NO_QUS("400", "該問卷無題目"),
	QUTIONNAIRE_NO_ANS("400", "該問卷目前無人作答"),
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
