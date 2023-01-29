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
		req.setTitle("test新");
		req.setDetails("這是test測試問卷");
		
		LocalDate start = LocalDate.now();
		
		LocalDate end = LocalDate.now();
		
		req.setStartTime(start.plusDays(-10));
		
//		req.setEndTime(end.plusDays(17));
		
		req.setEndTime(LocalDate.now());

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
	
//	@Test
//	public void catch2() {
//		
//		Map<String, List<String>> ansMap = new HashMap<>();
//		List<String> option1 = Arrays.asList("選項1", "選項2");
//		ansMap.put("問題1", option1);
//		
//		
//		List<String> option2 = Arrays.asList("選項33");
//		ansMap.put("問題2", option2);
//		
//		
//		List<String> option3 = Arrays.asList("選項333", "選項222");
//		ansMap.put("問題3", option3);
//		
//		QusDetailsReqList dReqList = new QusDetailsReqList();
//		dReqList.setAnsMap(ansMap);
//		
//		QusRequestReq qusRequest = new QusRequestReq();
//		qusRequest.setTitle("test宗憲好棒");
//		qusRequest.setName("老乾杯2");
//		qusRequest.setPhoneNum("0911111111");
//		qusRequest.setEmail("kenny@gmail.com");
//		qusRequest.setAge("25");
//		qusRequest.setSex("女");
//		
//		qusRequest.setAnsMap(ansMap);
//		
//		qusRequestService.catchAnswerInfo(qusRequest);
//	}

	
	@Test
	public void StringTest() {
		 String str1 = "vbasic,aashfh,vbasic,aaaa,aaavbasic, vbasic";
	        String str2 = "vbasic";
	        String str3 = "aaa";
	        String str4 = "aaa";
	        String str5 = "vbasic";
	        
	        List<String> strList =new ArrayList<>();
	        strList.add(str2);
	        strList.add(str3);
	        strList.add(str4);
	        strList.add(str5);
	        
	        int x = 0;
	        int y = 0;
	        
	        for(String item : strList) {
	        	if(str1.contains(item)) {
	        		 str1.replaceFirst(item," ");
	        		if(item == "vbasic") {
	        			x += 1;
	        		}
	        		if(item == "aaa") {
	        			y += 1;
	        		}
	        		System.out.println(str1);
	        	}
	        	
	        }
	        System.out.println("vbasic出現:" + x +" 次");
	        System.out.println("aaa出現:" + y +" 次");
	        
	        
	        
	}
}
