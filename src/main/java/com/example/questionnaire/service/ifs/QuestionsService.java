package com.example.questionnaire.service.ifs;

import com.example.questionnaire.vo.QuestionsReq;
import com.example.questionnaire.vo.QuestionsRes;
import com.example.questionnaire.vo.QuestionsResList;

public interface QuestionsService {
	
	//在沒後台的情況新增問卷總覽
	public QuestionsRes createQuestionWihoutBackGround(QuestionsReq req);
	
	//後台新增問卷
	public QuestionsRes createQuestionnaire(QuestionsReq req);
	
	//取得所有問卷
	public QuestionsResList getAllQuestions();
	
	//輸入問卷名稱(模糊搜尋)或日期區間搜尋對應問卷
	public QuestionsResList getQuestionsByTitleOrDate(QuestionsReq req);
	
}
