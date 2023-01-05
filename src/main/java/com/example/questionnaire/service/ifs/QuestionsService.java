package com.example.questionnaire.service.ifs;

import com.example.questionnaire.vo.QuestionsReq;
import com.example.questionnaire.vo.QuestionsRes;
import com.example.questionnaire.vo.QuestionsResList;
import com.example.questionnaire.vo.QusDetailsReqList;
import com.example.questionnaire.vo.QusDetailsRes;

public interface QuestionsService {
	
	//�b�S��x�����p�s�W�ݨ��`��
	public QuestionsRes createQuestionWihoutBackGround(QuestionsReq req);
	
	//��x�s�W�ݨ�
	public QuestionsRes createQuestionnaire(QuestionsReq req, QusDetailsReqList dReqList);
	
	//���o�Ҧ��ݨ�
	public QuestionsResList getAllQuestions();
	
	//���Ӥ������o������ƪ����
	public QuestionsResList getQuestionsPageList();
	
	//��J�ݨ��W��(�ҽk�j�M)�Τ���϶��j�M�����ݨ�
	public QuestionsResList getQuestionsByTitleOrDate(QuestionsReq req);
	
	//��J�ݨ�id��ܸӰݨ����e
	public QusDetailsRes getQuestionsDetailsById(QuestionsReq req);
	
	
	
}
