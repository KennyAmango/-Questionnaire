package com.example.questionnaire.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.questionnaire.constants.QuestionsRtnCode;
import com.example.questionnaire.entity.QusDetails;
import com.example.questionnaire.entity.QusRequest;
import com.example.questionnaire.repository.QusDetailsDao;
import com.example.questionnaire.repository.QusRequestDao;
import com.example.questionnaire.service.ifs.QusDetailsService;
import com.example.questionnaire.vo.QusDetailsReq;
import com.example.questionnaire.vo.QusDetailsRes;

@Service
public class QusDetailsServiceImpl implements QusDetailsService {

	@Autowired
	private QusDetailsDao qusDetailsDao;

	@Autowired
	private QusRequestDao qusRequestDao;

//	@Override
//	public QusDetailsRes statistics(QusDetailsReq req) {
//
//		List<QusDetails> qusList = qusDetailsDao.findAllByTitle(req.getTitle());
//		List<QusRequest> ansList = qusRequestDao.findAllByTitle(req.getTitle());
//
//		if (CollectionUtils.isEmpty(qusList)) {
//			return new QusDetailsRes(QuestionsRtnCode.QUTIONNAIRE_NO_QUS.getMessage());
//		} else if (CollectionUtils.isEmpty(ansList)) {
//			return new QusDetailsRes(QuestionsRtnCode.QUTIONNAIRE_NO_ANS.getMessage());
//		}
//		Map<String, Integer> optionsNum = new HashMap<>();
//		
//		Map<String, Map<String, Integer>> qusStatistics = new HashMap<>();
//
//		// 先設定各選項的初始數字0
//		for (QusDetails qus : qusList) {
//			// 題目
//			qus.getQus();
//			String[] option = qus.getOptions().split(",");
//
//			// foreach該問題的選項(str.trim())
//			for (String str : option) {
//
//				// foreach答卷者輸入的問題與選項
//				for (QusRequest ans : ansList) {
//
//					// 答卷者各個問題所選的選項(strList) : {問題3=選項222, 選項333}, {問題1=選項2, 選項3}, {問題2=選項11}
//					String[] strList = ans.getOptions_ans().split("}");
//
//					// 把匹配的問卷問題和選項加入optionsNum裡 :{問題3=選項222, 選項333,, {問題1=選項2, 選項3,, {問題2=選項11
//					for (String item : strList) {
//						//{問題3=選項222, 選項333,      {問題1=選項2, 選項3,         {問題2=選項11
//						if (item.trim().contains(str.trim()) && item.trim().contains(qus.getQus())) {
//							
//							optionsNum.put(str.trim(), 0);
//
//						}
//					}
//				}
//			}
//		}
//		
//		// foreach該問卷的題目內容,取得問題與選項(qus)
//		for (QusDetails qus : qusList) {
//
//			// 題目
//			qus.getQus();
//			String[] option = qus.getOptions().split(",");
//
//			// foreach該問題的選項(str.trim())
//			for (String str : option) {
//
//				// foreach答卷者輸入的問題與選項
//				for (QusRequest ans : ansList) {
//
//					// 答卷者各個問題所選的選項(strList)
//					String[] strList = ans.getOptions_ans().split("}");
//
//					// 把匹配的問卷問題和選項加入optionsNum裡
//					for (String item : strList) {
//						if (item.trim().contains(str.trim()) && item.trim().contains(qus.getQus())) {
//							
//							optionsNum.put(str.trim(), optionsNum.get(str.trim()) + 1);
//							
//							if (qus.getOptions().contains(str.trim())) {
//								qusStatistics.put(qus.getQus(), optionsNum);
//							}
//						}
//					}
//				}
//
//			}
//		}
//
//		QusDetailsRes res = new QusDetailsRes();
//		res.setQusAndOptions(qusStatistics);
//		return res;
//	}

	// ===========================

	@Override
	public QusDetailsRes statistics(QusDetailsReq req) {

		List<QusDetails> qusList = qusDetailsDao.findAllByTitle(req.getTitle());
		List<QusRequest> ansList = qusRequestDao.findAllByTitle(req.getTitle());

		if (CollectionUtils.isEmpty(qusList)) {
			return new QusDetailsRes(QuestionsRtnCode.QUTIONNAIRE_NO_QUS.getMessage());
		} else if (CollectionUtils.isEmpty(ansList)) {
			return new QusDetailsRes(QuestionsRtnCode.QUTIONNAIRE_NO_ANS.getMessage());
		}
		Map<String, String> optionsNum = new HashMap<>();

		Map<String, Map<String, String>> qusStatistics = new HashMap<>();
		int count = 0;
		
		for(QusRequest item : ansList) {
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
				double x = 0;
				
				// foreach答卷者輸入的問題與選項
				for (QusRequest ans : ansList) {

					// 答卷者各個問題所選的選項(strList) : {問題3=選項222, 選項333}, {問題1=選項2, 選項3}, {問題2=選項11}
					String[] strList = ans.getOptions_ans().split("}");

					// 把匹配的問卷問題和選項加入optionsNum裡 :{問題3=選項222, 選項333,, {問題1=選項2, 選項3,, {問題2=選項11
					for (String item : strList) {
						// {問題3=選項222, 選項333, {問題1=選項2, 選項3, {問題2=選項11
						if (item.trim().contains(str.trim()) && item.trim().contains(qus.getQus())) {
							x++;
							double y = (x/count)*100;
							String z = Double.toString(y);
							int a = (int) x;
							
							optionsNum.put(str.trim(), a +"(" +  z + "%" +")");

							qusStatistics.put(qus.getQus(), optionsNum);
						}
					}
				}
			}
		}

//		//foreach該問卷的題目內容,取得問題與選項(qus)
//		for (QusDetails qus : qusList) {
//
//			// 題目
//			qus.getQus();
//			String[] option = qus.getOptions().split(",");
//
//			// foreach該問題的選項(str.trim())
//			for (String str : option) {
//
//				// foreach答卷者輸入的問題與選項
//				for (QusRequest ans : ansList) {
//
//					// 答卷者各個問題所選的選項(strList)
//					String[] strList = ans.getOptions_ans().split("}");
//
//					// 把匹配的問卷問題和選項加入optionsNum裡
//					for (String item : strList) {
//						if (item.trim().contains(str.trim()) && item.trim().contains(qus.getQus())) {
//							
//							optionsNum.put(str.trim(), optionsNum.get(str.trim()) + 1);
//							
//							if (qus.getOptions().contains(str.trim())) {
//								qusStatistics.put(qus.getQus(), optionsNum);
//							}
//						}
//					}
//				}
//
//			}
//		}

		QusDetailsRes res = new QusDetailsRes();
		res.setStatics(qusStatistics);
		return res;
	}

}
