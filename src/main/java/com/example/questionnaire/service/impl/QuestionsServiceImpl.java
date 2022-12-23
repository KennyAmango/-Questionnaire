package com.example.questionnaire.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.questionnaire.constants.QuestionsRtnCode;
import com.example.questionnaire.entity.Questions;
import com.example.questionnaire.entity.QusDetails;
import com.example.questionnaire.repository.QuestionsDao;
import com.example.questionnaire.repository.QusDetailsDao;
import com.example.questionnaire.repository.QusRequestDao;
import com.example.questionnaire.service.ifs.QuestionsService;
import com.example.questionnaire.vo.QuestionsReq;
import com.example.questionnaire.vo.QuestionsRes;
import com.example.questionnaire.vo.QuestionsResList;


@Service
public class QuestionsServiceImpl implements QuestionsService{
	
	@Autowired
	private QuestionsDao questionsDao;
	
	@Autowired
	private QusDetailsDao qusDetailsDao;
	
	@Autowired
	private QusRequestDao qusRequestDao;
	
	//判斷問卷時間
	private QuestionsRes timeCheck(LocalDate start, LocalDate end) {
		QuestionsRes res = new QuestionsRes();
		if(start.isAfter(LocalDate.now())) {
			res.setMessage("未開始");
		}else if(end.isBefore(LocalDate.now())) {
			res.setMessage("已完結");
		}else {
			res.setMessage("投票中");
		}
		return res;
	}

	
	//在沒後台的情況新增問卷總覽
	@Override
	public QuestionsRes createQuestionWihoutBackGround(QuestionsReq req) {
		
		if(!StringUtils.hasText(req.getTitle())) {
			return new QuestionsRes(QuestionsRtnCode.TITLE_EMPTY.getMessage());
		}else if(!StringUtils.hasText(req.getDetails())) {
			return new QuestionsRes(QuestionsRtnCode.DTAILS_EMPTY.getMessage());
		}else if(req.getStartTime() == null) {
			return new QuestionsRes(QuestionsRtnCode.DTAILS_EMPTY.getMessage());
		}else if(req.getEndTime() == null) {
			return new QuestionsRes(QuestionsRtnCode.DTAILS_EMPTY.getMessage());
		}
//		else if(req.getStartTime().isBefore(LocalDate.now())) {
//			return new QuestionsRes(QuestionsRtnCode.STARTTIME_REQUIRED.getMessage());
//		}
		QuestionsRes res = new QuestionsRes();
		Questions questions = new Questions(req.getTitle(), req.getDetails(), req.getEndTime(), req.getEndTime());
		
		
		if(req.getStartTime().isAfter(LocalDate.now())) {
			res.setMessage("未開始");
		}else if(req.getEndTime().isBefore(LocalDate.now())) {
			res.setMessage("已完結");
		}else {
			res.setMessage("投票中");
		}
		questionsDao.save(questions);
		
		res.setQuestions(questions);
		
		return res;
	}
	
	//後台新增問卷
	@Transactional
	@Override
	public QuestionsRes createQuestionnaire(QuestionsReq req) {
		if(req.getStartTime()!= null && req.getEndTime() != null) {
			Questions questionnaire = new Questions(req.getTitle(), req.getDetails(), req.getStartTime(), req.getEndTime());
		}
		LocalDate now = LocalDate.now();
		Questions questionnaire = new Questions(req.getTitle(), req.getDetails(), LocalDate.now(), now.plusDays(7));
		QusDetails qusInfo = new QusDetails();
		
		
		return null;
	}


	//取得所有問卷,並判斷時間是否可以使用問卷
	@Override
	public QuestionsResList getAllQuestions() {
		
		List<Questions> QuestionsList = questionsDao.findAll();
		
		if(CollectionUtils.isEmpty(QuestionsList)) {
			return new QuestionsResList(QuestionsRtnCode.NO_QUESTIONNAIRE.getMessage());
		}
		
		List<QuestionsRes> timeList = new ArrayList<>();
		
		for(Questions item : QuestionsList) {
			if(item.getStartTime().isAfter(LocalDate.now())) {
				QuestionsRes res = new QuestionsRes();
				res.setMessage("未開始");
				res.setQuestions(item);
				timeList.add(res);
			}else if(item.getEndTime().isBefore(LocalDate.now())) {
				QuestionsRes res = new QuestionsRes();
				res.setMessage("已完結");
				res.setQuestions(item);
				timeList.add(res);
			}else {
				QuestionsRes res = new QuestionsRes();
				res.setMessage("投票中");
				res.setQuestions(item);
				timeList.add(res);
			}
		}
		
		QuestionsResList resList = new QuestionsResList();
		
		resList.setQuestionsResList(timeList);;
		
		return resList;
	}


	//輸入問卷名稱(模糊搜尋)或日期區間搜尋對應問卷
	@Override
	public QuestionsResList getQuestionsByTitleOrDate(QuestionsReq req) {
		//判斷輸入時間其中一個為空則提醒,全部為空則讓前端取得所有問卷
		if(!StringUtils.hasText(req.getTitle()) && req.getStartTime() == null && req.getEndTime() != null) {
			return new QuestionsResList(QuestionsRtnCode.STARTTIME_EMPTY.getMessage());
		}else if(!StringUtils.hasText(req.getTitle()) && req.getStartTime() != null && req.getEndTime() == null) {
			return new QuestionsResList(QuestionsRtnCode.ENDTIME_EMPTY.getMessage());
		}
		List<Questions> questionsList = questionsDao.findAll();
		
		List<QuestionsRes> resList = new ArrayList<>();
		
		//輸入問卷標題和時間搜尋時
		if(StringUtils.hasText(req.getTitle()) && req.getStartTime() != null && req.getEndTime() != null) {
			
			if(req.getStartTime().isAfter(req.getEndTime())) {
				return new QuestionsResList(QuestionsRtnCode.TIME_ERROR.getMessage());
			}
			int x = 0;
			for(Questions item : questionsList) {
				x++;
				//把符合時間內的該標題問卷顯示出來
				if((item.getStartTime().isAfter(req.getStartTime()) || item.getStartTime().isEqual(req.getStartTime()))
						&& (item.getEndTime().isBefore(req.getEndTime()) || item.getEndTime().isEqual(req.getEndTime()))) {
					
					QuestionsRes res = timeCheck(item.getStartTime(), item.getEndTime());
					res.setQuestions(item);
					resList.add(res);
				}
				//當resList裡沒有問卷時,回傳查無問卷
				if(questionsList.size() == x && CollectionUtils.isEmpty(resList)) {
					return new QuestionsResList(QuestionsRtnCode.NO_QUESTIONNAIRE.getMessage());
				}
			}
		}
		//只輸入問卷名稱搜尋時
		else if(StringUtils.hasText(req.getTitle()) && req.getStartTime() == null && req.getEndTime() == null) {
			int x = 0;
			for(Questions item : questionsList) {
				x++;
				if(item.getTitle().contains(req.getTitle())) {
					QuestionsRes res = timeCheck(item.getStartTime(), item.getEndTime());
					res.setQuestions(item);
					resList.add(res);
				}
				if(questionsList.size() == x && CollectionUtils.isEmpty(resList)) {
					return new QuestionsResList(QuestionsRtnCode.NO_QUESTIONNAIRE.getMessage());
				}
			}
		}
		//只輸入時間搜尋時
		else if(!StringUtils.hasText(req.getTitle()) && req.getStartTime() != null && req.getEndTime() != null) {
			if(req.getStartTime().isAfter(req.getEndTime())) {
				return new QuestionsResList(QuestionsRtnCode.TIME_ERROR.getMessage());
			}
			int x = 0;
			for(Questions item : questionsList) {
				x++;
				if((item.getStartTime().isAfter(req.getStartTime()) || item.getStartTime().isEqual(req.getStartTime()))
						&& (item.getEndTime().isBefore(req.getEndTime()) || item.getEndTime().isEqual(req.getEndTime()))) {
					QuestionsRes res = timeCheck(item.getStartTime(), item.getEndTime());
					res.setQuestions(item);
					resList.add(res);
				}
				if(questionsList.size() == x && CollectionUtils.isEmpty(resList)) {
					return new QuestionsResList(QuestionsRtnCode.NO_QUESTIONNAIRE.getMessage());
				}
			}
		}
		QuestionsResList finalRes = new QuestionsResList();
		finalRes.setQuestionsResList(resList);
		return finalRes;
	}
}
