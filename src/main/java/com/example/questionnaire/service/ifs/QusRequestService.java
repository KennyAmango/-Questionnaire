package com.example.questionnaire.service.ifs;

import com.example.questionnaire.vo.QusDetailsReqList;
import com.example.questionnaire.vo.QusRequestReq;
import com.example.questionnaire.vo.QusRequestRes;

public interface QusRequestService {

	// �����e�ݵ����̪���ƻP����
	public QusRequestRes catchAnswerInfo(QusRequestReq req, QusDetailsReqList dReqList);

}
