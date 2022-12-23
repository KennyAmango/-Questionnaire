package com.example.questionnaire.service.ifs;

import com.example.questionnaire.vo.QuestionsRes;

public interface QusRequestService {

	// 接收前端答卷者的資料與答案
	public QuestionsRes catchAnswerInfo();

}
