package com.example.questionnaire.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.questionnaire.constants.QuestionsRtnCode;
import com.example.questionnaire.entity.PercentageStatistics;
import com.example.questionnaire.entity.Questions;
import com.example.questionnaire.entity.QusDetails;
import com.example.questionnaire.entity.QusRequest;
import com.example.questionnaire.repository.QuestionsDao;
import com.example.questionnaire.repository.QusDetailsDao;
import com.example.questionnaire.repository.QusRequestDao;
import com.example.questionnaire.service.ifs.QusDetailsService;
import com.example.questionnaire.vo.QusDetailsReq;
import com.example.questionnaire.vo.QusDetailsRes;

@Service
public class QusDetailsServiceImpl implements QusDetailsService {

	@Autowired
	private QuestionsDao questionsDao;

	@Autowired
	private QusDetailsDao qusDetailsDao;

	@Autowired
	private QusRequestDao qusRequestDao;

	@Override
	public QusDetailsRes statistics(QusDetailsReq req) {

//		Questions question = questionsDao.findTitleById(req.getId());
//		if(!StringUtils.hasText(question.getTitle())) {
//			return new QusDetailsRes(QuestionsRtnCode.TITLE_EMPTY.getMessage());
//		}
//		List<QusDetails> qusList = qusDetailsDao.findAllByTitle(question.getTitle());
//		List<QusRequest> ansList = qusRequestDao.findAllByTitle(question.getTitle());
//
//		if (CollectionUtils.isEmpty(qusList)) {
//			return new QusDetailsRes(QuestionsRtnCode.QUTIONNAIRE_NO_QUS.getMessage());
//		} else if (CollectionUtils.isEmpty(ansList)) {
//			return new QusDetailsRes(QuestionsRtnCode.QUTIONNAIRE_NO_ANS.getMessage());
//		}
//		Map<String, String> optionsNum = new HashMap<>();
//
//		Map<String, Map<String, String>> qusStatistics = new HashMap<>();
//		int count = 0;
//		
//		for(QusRequest item : ansList) {
//			count++;
//		}
//
//		// 先設定各選項的初始數字0
//		for (QusDetails qus : qusList) {
//			optionsNum = new HashMap<>();
//			// 題目
//			qus.getQus();
//			String[] option = qus.getOptions().split(",");
//
//			// foreach該問題的選項(str.trim())
//			for (String str : option) {
//				double x = 0;
//				
//				// foreach答卷者輸入的問題與選項
//				for (QusRequest ans : ansList) {
//
//					// 答卷者各個問題所選的選項(strList) : {問題3=選項222, 選項333}, {問題1=選項2, 選項3}, {問題2=選項11}
//					String[] strList = ans.getOptions_ans().split("}");
//
//					// 把匹配的問卷問題和選項加入optionsNum裡 :{問題3=選項222, 選項333,, {問題1=選項2, 選項3,, {問題2=選項11
//					for (String item : strList) {
//						// {問題3=選項222, 選項333, {問題1=選項2, 選項3, {問題2=選項11
//						if (item.trim().contains(str.trim()) && item.trim().contains(qus.getQus())) {
//							x++;
//							double y = (x/count)*100;
//							String z = Double.toString(y);
//							int a = (int) x;
//							
//							optionsNum.put(str.trim(), a +"(" +  z + "%" +")");
//
//							qusStatistics.put(qus.getQus(), optionsNum);
//						}
//					}
//				}
//			}
//		}
//		QusDetailsRes res = new QusDetailsRes();
//		res.setStatics(qusStatistics);
//		return res;

		Questions question = questionsDao.findTitleById(req.getId());
		if (!StringUtils.hasText(question.getTitle())) {
			return new QusDetailsRes(QuestionsRtnCode.TITLE_EMPTY.getMessage());
		}
		List<QusDetails> qusList = qusDetailsDao.findAllByTitle(question.getTitle());
		List<QusRequest> ansList = qusRequestDao.findAllByTitle(question.getTitle());

		if (CollectionUtils.isEmpty(qusList)) {
			return new QusDetailsRes(QuestionsRtnCode.QUTIONNAIRE_NO_QUS.getMessage());
		} else if (CollectionUtils.isEmpty(ansList)) {
			return new QusDetailsRes(QuestionsRtnCode.QUTIONNAIRE_NO_ANS.getMessage());
		}
		Map<String, Integer> optionsNum = new HashMap<>();

		Map<String, Map<String, Integer>> qusStatistics = new HashMap<>();
		int count = 0;

		for (QusRequest item : ansList) {
			count++;
		}

		// 先設定各選項的初始數字0
		for (QusDetails qus : qusList) {
			optionsNum = new HashMap<>();
			// 題目
			qus.getQus();
			String[] option = qus.getOptions().split(",");

			// foreach該問題的選項(str.trim())
			for (String str : option) {
				int x = 0;

				// foreach答卷者輸入的問題與選項
				for (QusRequest ans : ansList) {

					// 答卷者各個問題所選的選項(strList) : {問題3=選項222, 選項333}, {問題1=選項2, 選項3}, {問題2=選項11}
					String[] strList = ans.getOptions_ans().split("}");

					// 把匹配的問卷問題和選項加入optionsNum裡 :{問題3=選項222, 選項333,, {問題1=選項2, 選項3,, {問題2=選項11
					for (String item : strList) {
						// {問題3=選項222, 選項333, {問題1=選項2, 選項3, {問題2=選項11
						if (item.trim().contains(str.trim()) && item.trim().contains(qus.getQus())) {
							x++;

							optionsNum.put(str.trim(), x);

							qusStatistics.put(qus.getQus(), optionsNum);
						}
					}
				}
			}
		}
//		Map<String, Map<String, Integer>> percentageMap = new HashMap<>();
		Map<String, Integer> totalMap = new HashMap<>();
		List<PercentageStatistics> percentageList = new ArrayList<>();
		for (Entry<String, Map<String, Integer>> entry : qusStatistics.entrySet()) {
			totalMap.put(entry.getKey(), 0);
			
			for (Entry<String, Integer> optionNum : entry.getValue().entrySet()) {
				
					totalMap.put(entry.getKey(), optionNum.getValue() + totalMap.get(entry.getKey()));
				
				
			}
		}
		for (Entry<String, Map<String, Integer>> entry : qusStatistics.entrySet()) {

			for (Entry<String, Integer> optionNum : entry.getValue().entrySet()) {

				for (Entry<String, Integer> optionTotalNum : totalMap.entrySet()) {

					if (entry.getKey().equals(optionTotalNum.getKey())) {
						double x = optionNum.getValue();
						double y = optionTotalNum.getValue();
						double percentage = Math.round(x / y * 1000.0) / 10.0 ;
						PercentageStatistics res = new PercentageStatistics(entry.getKey(),optionNum.getKey(),percentage);
						percentageList.add(res);
//						Map<String, Integer> optionPercentageMap = new HashMap<>();
//						optionPercentageMap.put(optionNum.getKey(), percentage);
//						percentageMap.put(entry.getKey(), optionPercentageMap);
					}
				}
			}
		}

		QusDetailsRes res = new QusDetailsRes();
		res.setQusAndOptions(qusStatistics);
//		res.setPercentageStatics(percentageMap);
		res.setStatisticsList(percentageList);
		return res;
	}

}
