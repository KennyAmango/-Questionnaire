package com.example.questionnaire.service.ifs;

import com.example.questionnaire.vo.QuestionsReq;
import com.example.questionnaire.vo.QuestionsRes;
import com.example.questionnaire.vo.QuestionsResList;

public interface QuestionsService {
	
	//�b�S��x�����p�s�W�ݨ��`��
	public QuestionsRes createQuestionWihoutBackGround(QuestionsReq req);
	
	//��x�s�W�ݨ�
	public QuestionsRes createQuestionnaire(QuestionsReq req);
	
	//���o�Ҧ��ݨ�
	public QuestionsResList getAllQuestions();
	
	//��J�ݨ��W��(�ҽk�j�M)�Τ���϶��j�M�����ݨ�
	public QuestionsResList getQuestionsByTitleOrDate(QuestionsReq req);
	
}
