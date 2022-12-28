package com.example.questionnaire.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
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
		List<String> ansList = new ArrayList<>();
		Map<String, String> saveMap = new HashMap<>();
		
		// 輸入型態:List<Map{Key:問題選項(String), value:問題答案(List<String>)}>
//	star:
		for(Map<String, List<String>> ansMap: dReqList.getAnsList()) {
//			int x = 1;
//			int y = 0;
//			for(Map.Entry<String, List<String>> entry: ansMap.entrySet()) {
//				y++;
//				if(x != y) {
//					continue;
//				}
//				String entryStr = entry.toString().substring(1, entry.toString().length() - 1);
//				saveMap.put(ansMap.get, entryStr);
//				x++;
//				continue star;
//			}
			String str = ansMap.toString().substring(1, ansMap.toString().length() - 1);
			ansList.add(str);
		}
		String ansStr = ansList.toString().substring(1, ansList.toString().length() - 1);
		
		
		QusRequest qusRequest = new QusRequest(UUID.randomUUID(), req.getTitle(), req.getName(), req.getPhoneNum(), 
				req.getEmail(), req.getAge(), req.getSex(), ansStr);
		
		
		
		QusRequestRes res = new QusRequestRes(QuestionsRtnCode.CATCH_REQUEST_SUCCESS.getMessage());
		
		qusRequestDao.save(qusRequest);

		return res;
	}

}
