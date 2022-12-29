package com.example.questionnaire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.questionnaire.entity.QusRequest;
import com.example.questionnaire.service.ifs.QuestionsService;
import com.example.questionnaire.service.ifs.QusRequestService;
import com.example.questionnaire.vo.QuestionsReq;
import com.example.questionnaire.vo.QusDetailsReq;
import com.example.questionnaire.vo.QusDetailsReqList;
import com.example.questionnaire.vo.QusRequestReq;

@SpringBootTest
public class QuestionsTest {

	@Autowired
	private QuestionsService questionsService;

	@Autowired
	private QusRequestService qusRequestService;

	@Test
	public void createByBackGround() {
		QuestionsReq req = new QuestionsReq();
		req.setTitle("test哈哈哈");
		req.setDetails("這是test3測試問卷");
		
		LocalDate start = LocalDate.now();
		
		LocalDate end = LocalDate.now();
		
		req.setStartTime(start.plusDays(10));
		req.setEndTime(end.plusDays(25));

		Map<String, List<String>> map1 = new HashMap<>();
		Map<String, List<String>> map2 = new HashMap<>();
		Map<String, List<String>> map3 = new HashMap<>();

		List<String> options = Arrays.asList("選項a", "選項b", "選項c");

		List<String> options2 = new ArrayList<>();
		options2.add("選項aa");
		options2.add("選項bb");
		options2.add("選項cc");

		List<String> options3 = new ArrayList<>();
		options3.add("選項aaa");
		options3.add("選項bbb");
		options3.add("選項ccc");

		List<Map<String, List<String>>> mapList = new ArrayList<>();

		map1.put("問題a", options);
		map2.put("問題b", options2);
		map3.put("問題c", options3);

		QusDetailsReqList dReqList = new QusDetailsReqList();

		QusDetailsReq req1 = new QusDetailsReq();
		req1.setOptions(map1);
		req1.setMultipleChoice(true);

		QusDetailsReq req2 = new QusDetailsReq();
		req2.setOptions(map2);
		req2.setMultipleChoice(false);

		QusDetailsReq req3 = new QusDetailsReq();
		req3.setOptions(map3);
		req3.setMultipleChoice(true);

		List<QusDetailsReq> finalReq = new ArrayList<>();
		finalReq.add(req1);
		finalReq.add(req2);
		finalReq.add(req3);

		dReqList.setReqList(finalReq);
		questionsService.createQuestionnaire(req, dReqList);
	}

	@Test
	public void catch1() {
		
		Map<String, List<String>> ans1 = new HashMap<>();
		List<String> option1 = Arrays.asList("選項1");
		ans1.put("問題1", option1);
		
		Map<String, List<String>> ans2 = new HashMap<>();
		List<String> option2 = Arrays.asList("選項22");
		ans2.put("問題2", option2);
		
		Map<String, List<String>> ans3 = new HashMap<>();
		List<String> option3 = Arrays.asList("選項111", "選項222", "選項333");
		ans3.put("問題3", option3);
		
		List<Map<String, List<String>>> ansList = new ArrayList<>();
		ansList.add(ans1);
		ansList.add(ans2);
		ansList.add(ans3);
		QusDetailsReqList dReqList = new QusDetailsReqList();
//		dReqList.setAnsList(ansList);
		
		QusRequestReq qusRequest = new QusRequestReq();
		qusRequest.setTitle("test宗憲好棒");
		qusRequest.setName("小笨蛋");
		qusRequest.setPhoneNum("0911111111");
		qusRequest.setEmail("kenny@gmail.com");
		qusRequest.setAge("18");
		qusRequest.setSex("男");
		
		qusRequestService.catchAnswerInfo(qusRequest, dReqList);
	}
	
	@Test
	public void catch2() {
		
		Map<String, List<String>> ansMap = new HashMap<>();
		List<String> option1 = Arrays.asList("選項1");
		ansMap.put("問題1", option1);
		
		
		List<String> option2 = Arrays.asList("選項22");
		ansMap.put("問題2", option2);
		
		
		List<String> option3 = Arrays.asList("選項111", "選項222", "選項333");
		ansMap.put("問題3", option3);
		
		QusDetailsReqList dReqList = new QusDetailsReqList();
		dReqList.setAnsMap(ansMap);
		
		QusRequestReq qusRequest = new QusRequestReq();
		qusRequest.setTitle("test宗憲好棒");
		qusRequest.setName("小笨蛋");
		qusRequest.setPhoneNum("0911111111");
		qusRequest.setEmail("kenny@gmail.com");
		qusRequest.setAge("18");
		qusRequest.setSex("男");
		
		qusRequestService.catchAnswerInfo(qusRequest, dReqList);
	}

}
