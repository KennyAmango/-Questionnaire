package com.example.questionnaire.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.questionnaire.constants.QuestionsRtnCode;
import com.example.questionnaire.entity.QusDetails;
import com.example.questionnaire.entity.QusRequest;
import com.example.questionnaire.repository.QusDetailsDao;
import com.example.questionnaire.repository.QusRequestDao;
import com.example.questionnaire.service.ifs.QusRequestService;
import com.example.questionnaire.vo.QusDetailsReq;
import com.example.questionnaire.vo.QusDetailsReqList;
import com.example.questionnaire.vo.QusRequestReq;
import com.example.questionnaire.vo.QusRequestRes;

@Service
public class QusRequestServiceImpl implements QusRequestService {

	@Autowired
	private QusRequestDao qusRequestDao;
	
	@Autowired
	private QusDetailsDao qusDetailsDao;

	@Override
	public QusRequestRes catchAnswerInfo(QusRequestReq req, QusDetailsReqList dReqList) {
		
		// 輸入型態:List<Map{Key:問題選項(String), value:問題答案(List<String>)}>
		String ansStr = dReqList.getAnsList().toString().substring(1, dReqList.getAnsList().toString().length() - 1);
		
		
		QusRequest qusRequest = new QusRequest(UUID.randomUUID(), req.getTitle(), req.getName(), req.getPhoneNum(), 
				req.getEmail(), req.getAge(), req.getSex(), ansStr);
		
		
		
		QusRequestRes res = new QusRequestRes(QuestionsRtnCode.CATCH_REQUEST_SUCCESS.getMessage());
		
		qusRequestDao.save(qusRequest);

		return res;
	}

}
