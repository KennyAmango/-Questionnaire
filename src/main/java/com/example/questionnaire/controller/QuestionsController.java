package com.example.questionnaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.questionnaire.constants.QuestionsRtnCode;
import com.example.questionnaire.service.ifs.QuestionsService;
import com.example.questionnaire.service.ifs.QusRequestService;
import com.example.questionnaire.vo.QuestionsReq;
import com.example.questionnaire.vo.QuestionsRes;
import com.example.questionnaire.vo.QuestionsResList;
import com.example.questionnaire.vo.QusDetailsReqList;
import com.example.questionnaire.vo.QusRequestReq;
import com.example.questionnaire.vo.QusRequestRes;

@CrossOrigin
@RestController
public class QuestionsController {

	@Autowired
	QuestionsService questionsService;

	@Autowired
	QusRequestService qusRequestService;

	// 在沒後台的情況新增問卷總覽
	@PostMapping(value = "/api/createQuestionsWihoutBackGround")
	public QuestionsRes createQuestions(@RequestBody QuestionsReq req) {

		return questionsService.createQuestionWihoutBackGround(req);

	}

	// 後台新增問卷
	@PostMapping(value = "/api/createQuestionnaire")
	public QuestionsRes createQuestionnaire(@RequestBody QuestionsReq req, QusDetailsReqList dReqList) {

		if (!StringUtils.hasText(req.getTitle())) {
			return new QuestionsRes(QuestionsRtnCode.TITLE_EMPTY.getMessage());
		} else if (req.getStartTime() != null && req.getEndTime() == null) {
			return new QuestionsRes(QuestionsRtnCode.ENDTIME_EMPTY.getMessage());
		} else if (req.getStartTime() == null && req.getEndTime() != null) {
			return new QuestionsRes(QuestionsRtnCode.STARTTIME_EMPTY.getMessage());
		} else if (!StringUtils.hasText(req.getDetails())) {
			return new QuestionsRes(QuestionsRtnCode.DTAILS_EMPTY.getMessage());
		} else if (!CollectionUtils.isEmpty(req.getQusList())) {
			return new QuestionsRes(QuestionsRtnCode.ANSOPTIONS_EMPTY.getMessage());
		}

		return questionsService.createQuestionnaire(req, dReqList);

	}

	// 取得所有問卷
	@PostMapping(value = "/api/getAllQuestions")
	public QuestionsResList getAllQuestions() {

		return questionsService.getAllQuestions();

	}

	// 輸入問卷名稱(模糊搜尋)或日期區間搜尋對應問卷
	@PostMapping(value = "/api/getQuestionsByTitleOrDate")
	public QuestionsResList getQuestionsByTitleOrDate(@RequestBody QuestionsReq req) {

		return questionsService.getQuestionsByTitleOrDate(req);

	}

	// 取得答卷者資訊
	@PostMapping(value = "/api/catchAnswerInfo")
	public QusRequestRes catchAnswerInfo(@RequestBody QusRequestReq req, QusDetailsReqList dReqList) {

		if (!StringUtils.hasText(req.getTitle())) {
			return new QusRequestRes(QuestionsRtnCode.TITLE_EMPTY.getMessage());
		} else if (!StringUtils.hasText(req.getName())) {
			return new QusRequestRes(QuestionsRtnCode.NAME_EMPTY.getMessage());
		} else if (!StringUtils.hasText(req.getPhoneNum())) {
			return new QusRequestRes(QuestionsRtnCode.PHONENUM_EMPTY.getMessage());
		} else if (!StringUtils.hasText(req.getEmail())) {
			return new QusRequestRes(QuestionsRtnCode.EMAIL_EMPTY.getMessage());
		} else if (!StringUtils.hasText(req.getSex())) {
			return new QusRequestRes(QuestionsRtnCode.SEX_EMPTY.getMessage());
		} else if (req.getAge() == null) {
			return new QusRequestRes(QuestionsRtnCode.AGE_EMPTY.getMessage());
		} else if (!CollectionUtils.isEmpty(dReqList.getAnsList())) {
			return new QusRequestRes(QuestionsRtnCode.ANS_EMPTY.getMessage());
		} 

		return qusRequestService.catchAnswerInfo(req, dReqList);

	}

}
