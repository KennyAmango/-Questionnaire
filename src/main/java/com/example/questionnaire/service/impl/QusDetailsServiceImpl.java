package com.example.questionnaire.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.questionnaire.constants.QuestionsRtnCode;
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
//		// ���]�w�U�ﶵ����l�Ʀr0
//		for (QusDetails qus : qusList) {
//			optionsNum = new HashMap<>();
//			// �D��
//			qus.getQus();
//			String[] option = qus.getOptions().split(",");
//
//			// foreach�Ӱ��D���ﶵ(str.trim())
//			for (String str : option) {
//				double x = 0;
//				
//				// foreach�����̿�J�����D�P�ﶵ
//				for (QusRequest ans : ansList) {
//
//					// �����̦U�Ӱ��D�ҿ諸�ﶵ(strList) : {���D3=�ﶵ222, �ﶵ333}, {���D1=�ﶵ2, �ﶵ3}, {���D2=�ﶵ11}
//					String[] strList = ans.getOptions_ans().split("}");
//
//					// ��ǰt���ݨ����D�M�ﶵ�[�JoptionsNum�� :{���D3=�ﶵ222, �ﶵ333,, {���D1=�ﶵ2, �ﶵ3,, {���D2=�ﶵ11
//					for (String item : strList) {
//						// {���D3=�ﶵ222, �ﶵ333, {���D1=�ﶵ2, �ﶵ3, {���D2=�ﶵ11
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
		if(!StringUtils.hasText(question.getTitle())) {
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
		
		for(QusRequest item : ansList) {
			count++;
		}

		// ���]�w�U�ﶵ����l�Ʀr0
		for (QusDetails qus : qusList) {
			optionsNum = new HashMap<>();
			// �D��
			qus.getQus();
			String[] option = qus.getOptions().split(",");

			// foreach�Ӱ��D���ﶵ(str.trim())
			for (String str : option) {
				int x = 0;
				
				// foreach�����̿�J�����D�P�ﶵ
				for (QusRequest ans : ansList) {

					// �����̦U�Ӱ��D�ҿ諸�ﶵ(strList) : {���D3=�ﶵ222, �ﶵ333}, {���D1=�ﶵ2, �ﶵ3}, {���D2=�ﶵ11}
					String[] strList = ans.getOptions_ans().split("}");

					// ��ǰt���ݨ����D�M�ﶵ�[�JoptionsNum�� :{���D3=�ﶵ222, �ﶵ333,, {���D1=�ﶵ2, �ﶵ3,, {���D2=�ﶵ11
					for (String item : strList) {
						// {���D3=�ﶵ222, �ﶵ333, {���D1=�ﶵ2, �ﶵ3, {���D2=�ﶵ11
						if (item.trim().contains(str.trim()) && item.trim().contains(qus.getQus())) {
							x++;
							
							optionsNum.put(str.trim(), x );

							qusStatistics.put(qus.getQus(), optionsNum);
						}
					}
				}
			}
		}
		QusDetailsRes res = new QusDetailsRes();
		res.setQusAndOptions(qusStatistics);
		return res;
	}

}
