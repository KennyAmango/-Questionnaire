package com.example.questionnaire.constants;

public enum QuestionsRtnCode {
	STARTTIME_EMPTY("400", "�}�l�ɶ�����"),
	STARTTIME_REQUIRED("400", "�}�l�ɶ����ভ�󤵤�"),
	ENDTIME_EMPTY("400", "�����ɶ�����"),
	TIME_ERROR("400", "�}�l�ɶ�����j�󵲧��ɶ�"),
	DTAILS_EMPTY("400", "�ݨ���������"),
	NO_QUESTIONNAIRE("400", "�ȵL�ݨ�"),
	TITLE_EMPTY("400", "�ݨ����D����");
	
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
