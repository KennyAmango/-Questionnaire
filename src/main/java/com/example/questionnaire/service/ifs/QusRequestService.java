package com.example.questionnaire.service.ifs;

import com.example.questionnaire.vo.QusDetailsReqList;
import com.example.questionnaire.vo.QusRequestReq;
import com.example.questionnaire.vo.QusRequestRes;

public interface QusRequestService {

	// 接收前端答卷者的資料與答案
	public QusRequestRes catchAnswerInfo(QusRequestReq req, QusDetailsReqList dReqList);

}
