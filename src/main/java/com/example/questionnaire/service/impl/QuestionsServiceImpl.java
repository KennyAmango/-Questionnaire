package com.example.questionnaire.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.example.questionnaire.vo.QusDetailsReq;
import com.example.questionnaire.vo.QusDetailsReqList;
import com.example.questionnaire.vo.QusDetailsRes;

@Service
public class QuestionsServiceImpl implements QuestionsService {

	@Autowired
	private QuestionsDao questionsDao;

	@Autowired
	private QusDetailsDao qusDetailsDao;

	@Autowired
	private QusRequestDao qusRequestDao;

	// �P�_�ݨ��ɶ�
	private QuestionsRes timeCheck(LocalDate start, LocalDate end) {
		QuestionsRes res = new QuestionsRes();
		if (start.isAfter(LocalDate.now())) {
			res.setMessage("���}�l");
		} else if (end.isBefore(LocalDate.now())) {
			res.setMessage("�w����");
		} else {
			res.setMessage("�벼��");
		}
		return res;
	}

	// �b�S��x�����p�s�W�ݨ��`��
	@Override
	public QuestionsRes createQuestionWihoutBackGround(QuestionsReq req) {

		if (!StringUtils.hasText(req.getTitle())) {
			return new QuestionsRes(QuestionsRtnCode.TITLE_EMPTY.getMessage());
		} else if (!StringUtils.hasText(req.getDetails())) {
			return new QuestionsRes(QuestionsRtnCode.DTAILS_EMPTY.getMessage());
		} else if (req.getStartTime() == null) {
			return new QuestionsRes(QuestionsRtnCode.DTAILS_EMPTY.getMessage());
		} else if (req.getEndTime() == null) {
			return new QuestionsRes(QuestionsRtnCode.DTAILS_EMPTY.getMessage());
		}
//		else if(req.getStartTime().isBefore(LocalDate.now())) {
//			return new QuestionsRes(QuestionsRtnCode.STARTTIME_REQUIRED.getMessage());
//		}
		QuestionsRes res = new QuestionsRes();
		Questions questions = new Questions(req.getTitle(), req.getDetails(), req.getEndTime(), req.getEndTime());

		if (req.getStartTime().isAfter(LocalDate.now())) {
			res.setMessage("���}�l");
		} else if (req.getEndTime().isBefore(LocalDate.now())) {
			res.setMessage("�w����");
		} else {
			res.setMessage("�벼��");
		}
		questionsDao.save(questions);

		res.setQuestions(questions);

		return res;
	}

	// ��x�s�W�ݨ�
	@Override
	public QuestionsRes createQuestionnaire(QuestionsReq req, QusDetailsReqList dReqList) {

		List<QusDetails> qusList = new ArrayList<>();
		// ��J���A:List<Map{Key:���D���D(String), value:���D�ﶵ(List<String>)}>
		for (QusDetailsReq item : dReqList.getReqList()) {
			for (Map.Entry<String, List<String>> entry : item.getOptions().entrySet()) {
				String qusOptions = entry.getValue().toString().substring(1, entry.getValue().toString().length() - 1);

				QusDetails qusInfo = new QusDetails(req.getTitle(), entry.getKey(), qusOptions,
						item.isMultipleChoice());
				qusList.add(qusInfo);
			}
		}
		if (req.getStartTime() != null && req.getEndTime() != null) {
			Questions questionnaire = new Questions(req.getTitle(), req.getDetails(), req.getStartTime(),
					req.getEndTime());
			questionsDao.save(questionnaire);
		} else {
			LocalDate now = LocalDate.now();
			Questions questionnaire = new Questions(req.getTitle(), req.getDetails(), LocalDate.now(), now.plusDays(7));
			questionsDao.save(questionnaire);
		}
		qusDetailsDao.saveAll(qusList);
		return new QuestionsRes(QuestionsRtnCode.CREATE_QUESTIONNAIRE_SUCCESS.getMessage());
	}

	// ���o�Ҧ��ݨ�,�çP�_�ɶ��O�_�i�H�ϥΰݨ�
	@Override
	public QuestionsResList getAllQuestions() {

		List<Questions> questionsList = questionsDao.findAll();

		if (CollectionUtils.isEmpty(questionsList)) {
			return new QuestionsResList(QuestionsRtnCode.NO_QUESTIONNAIRE.getMessage());
		}

		List<QuestionsRes> timeList = new ArrayList<>();

		for (Questions item : questionsList) {
			if (item.getStartTime().isAfter(LocalDate.now())) {
				QuestionsRes res = new QuestionsRes();
				res.setMessage("���}�l");
				res.setQuestions(item);
				timeList.add(res);
			} else if (item.getEndTime().isBefore(LocalDate.now())) {
				QuestionsRes res = new QuestionsRes();
				res.setMessage("�w����");
				res.setQuestions(item);
				timeList.add(res);
			} else {
				QuestionsRes res = new QuestionsRes();
				res.setMessage("�벼��");
				res.setQuestions(item);
				timeList.add(res);
			}
		}

		QuestionsResList resList = new QuestionsResList();

		resList.setQuestionsResList(timeList);

		return resList;
	}

	// ��J�ݨ��W��(�ҽk�j�M)�Τ���϶��j�M�����ݨ�
	@Override
	public QuestionsResList getQuestionsByTitleOrDate(QuestionsReq req) {

		List<Questions> questionsList = questionsDao.findAll();

		List<QuestionsRes> resList = new ArrayList<>();

		// ��J�ݨ����D�M�ɶ��j�M��
		if (StringUtils.hasText(req.getTitle()) && req.getStartTime() != null && req.getEndTime() != null) {

			if (req.getStartTime().isAfter(req.getEndTime())) {
				return new QuestionsResList(QuestionsRtnCode.TIME_ERROR.getMessage());
			}
			int x = 0;
			for (Questions item : questionsList) {
				x++;
				// ��ŦX�ɶ������Ӽ��D�ݨ���ܥX��
				if ((item.getStartTime().isAfter(req.getStartTime()) || item.getStartTime().isEqual(req.getStartTime()))
						&& (item.getEndTime().isBefore(req.getEndTime()) || item.getEndTime().isEqual(req.getEndTime()))
						&& item.getTitle().contains(req.getTitle())) {

					QuestionsRes res = timeCheck(item.getStartTime(), item.getEndTime());
					res.setQuestions(item);
					resList.add(res);
				}
				// ��resList�̨S���ݨ���,�^�Ǭd�L�ݨ�
				if (questionsList.size() == x && CollectionUtils.isEmpty(resList)) {
					return new QuestionsResList(QuestionsRtnCode.NO_QUESTIONNAIRE.getMessage());
				}
			}
		}
		// �u��J�ݨ��W�ٷj�M��
		else if (StringUtils.hasText(req.getTitle()) && req.getStartTime() == null && req.getEndTime() == null) {
			int x = 0;
			for (Questions item : questionsList) {
				x++;
				if (item.getTitle().contains(req.getTitle())) {
					QuestionsRes res = timeCheck(item.getStartTime(), item.getEndTime());
					res.setQuestions(item);
					resList.add(res);
				}
				if (questionsList.size() == x && CollectionUtils.isEmpty(resList)) {
					return new QuestionsResList(QuestionsRtnCode.NO_QUESTIONNAIRE.getMessage());
				}
			}
		}
		// �u��J�ɶ��j�M��
		else if (!StringUtils.hasText(req.getTitle()) && req.getStartTime() != null && req.getEndTime() != null) {
			if (req.getStartTime().isAfter(req.getEndTime())) {
				return new QuestionsResList(QuestionsRtnCode.TIME_ERROR.getMessage());
			}
			int x = 0;
			for (Questions item : questionsList) {
				x++;
				if ((item.getStartTime().isAfter(req.getStartTime()) || item.getStartTime().isEqual(req.getStartTime()))
						&& (item.getEndTime().isBefore(req.getEndTime())
								|| item.getEndTime().isEqual(req.getEndTime()))) {
					QuestionsRes res = timeCheck(item.getStartTime(), item.getEndTime());
					res.setQuestions(item);
					resList.add(res);
				}
				if (questionsList.size() == x && CollectionUtils.isEmpty(resList)) {
					return new QuestionsResList(QuestionsRtnCode.NO_QUESTIONNAIRE.getMessage());
				}
			}
		}
		// �u��J�}�l�ɶ���
		else if (!StringUtils.hasText(req.getTitle()) && req.getStartTime() != null && req.getEndTime() == null) {
			int x = 0;
			for (Questions item : questionsList) {
				x++;
				if ((item.getStartTime().isAfter(req.getStartTime())
						|| item.getStartTime().isEqual(req.getStartTime()))) {
					QuestionsRes res = timeCheck(item.getStartTime(), item.getEndTime());
					res.setQuestions(item);
					resList.add(res);
				}
				if (questionsList.size() == x && CollectionUtils.isEmpty(resList)) {
					return new QuestionsResList(QuestionsRtnCode.NO_QUESTIONNAIRE.getMessage());
				}
			}
		}
		// �u��J�����ɶ���
		else if (!StringUtils.hasText(req.getTitle()) && req.getStartTime() == null && req.getEndTime() != null) {
			int x = 0;
			for (Questions item : questionsList) {
				x++;
				if ((item.getEndTime().isBefore(req.getEndTime()) || item.getEndTime().isEqual(req.getEndTime()))) {
					QuestionsRes res = timeCheck(item.getStartTime(), item.getEndTime());
					res.setQuestions(item);
					resList.add(res);
				}
				if (questionsList.size() == x && CollectionUtils.isEmpty(resList)) {
					return new QuestionsResList(QuestionsRtnCode.NO_QUESTIONNAIRE.getMessage());
				}
			}
		}
		// ��J�}�l�ɶ��M���D
		else if (StringUtils.hasText(req.getTitle()) && req.getStartTime() != null && req.getEndTime() == null) {
			int x = 0;
			for (Questions item : questionsList) {
				x++;
				if ((item.getStartTime().isAfter(req.getStartTime()) || item.getStartTime().isEqual(req.getStartTime()))
						&& item.getTitle().contains(req.getTitle())) {
					QuestionsRes res = timeCheck(item.getStartTime(), item.getEndTime());
					res.setQuestions(item);
					resList.add(res);
				}
				if (questionsList.size() == x && CollectionUtils.isEmpty(resList)) {
					return new QuestionsResList(QuestionsRtnCode.NO_QUESTIONNAIRE.getMessage());
				}
			}
		}
		// ��J�����ɶ��M���D
		else if (StringUtils.hasText(req.getTitle()) && req.getStartTime() == null && req.getEndTime() != null) {
			int x = 0;
			for (Questions item : questionsList) {
				x++;
				if ((item.getEndTime().isBefore(req.getEndTime()) || item.getEndTime().isEqual(req.getEndTime()))
						&& item.getTitle().contains(req.getTitle())) {
					QuestionsRes res = timeCheck(item.getStartTime(), item.getEndTime());
					res.setQuestions(item);
					resList.add(res);
				}
				if (questionsList.size() == x && CollectionUtils.isEmpty(resList)) {
					return new QuestionsResList(QuestionsRtnCode.NO_QUESTIONNAIRE.getMessage());
				}
			}
		}
		// �ƻ򳣨S��J
		else if (!StringUtils.hasText(req.getTitle()) && req.getStartTime() == null && req.getEndTime() == null) {
			int x = 0;
			for (Questions item : questionsList) {
				x++;
					QuestionsRes res = timeCheck(item.getStartTime(), item.getEndTime());
					res.setQuestions(item);
					resList.add(res);
				
				if (questionsList.size() == x && CollectionUtils.isEmpty(resList)) {
					return new QuestionsResList(QuestionsRtnCode.NO_QUESTIONNAIRE.getMessage());
				}
			}
		}
		QuestionsResList finalRes = new QuestionsResList();
		finalRes.setQuestionsResList(resList);
		return finalRes;
	}

	// ��J�ݨ�id��ܸӰݨ����e
	@Override
	public QusDetailsRes getQuestionsDetailsById(QuestionsReq req) {

		Optional<Questions> questionOp = questionsDao.findById(req.getId());

		if (!questionOp.isPresent()) {
			return new QusDetailsRes(QuestionsRtnCode.QUTIONNAIRE_NO_FOUND.getMessage());
		}

		Questions questionInfo = questionOp.get();

		List<QusDetails> qusList = qusDetailsDao.findAllByTitle(questionInfo.getTitle());

		Map<String, Map<String, Integer>> qusMap = new HashMap<>();

		for (QusDetails item : qusList) {
			Map<String, Integer> optionsMap = new HashMap<>();
			String[] str = item.getOptions().split(",");

			for (String strItem : str) {
				if (item.isMultipleChoice() == true) {
					optionsMap.put(strItem, 1);
				} else if (item.isMultipleChoice() == false) {
					optionsMap.put(strItem, 0);
				}
			}
			qusMap.put(item.getQus(), optionsMap);
		}

		QuestionsRes timeCheck = timeCheck(questionInfo.getStartTime(), questionInfo.getEndTime());

		QusDetailsRes res = new QusDetailsRes(questionInfo.getTitle(), questionInfo.getDetails(),
				questionInfo.getStartTime(), questionInfo.getEndTime(), timeCheck.getMessage(), qusMap);

		return res;
	}
}
