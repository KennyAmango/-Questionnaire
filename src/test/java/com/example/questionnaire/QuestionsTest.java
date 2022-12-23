package com.example.questionnaire;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.questionnaire.service.ifs.QuestionsService;
import com.example.questionnaire.vo.QuestionsReq;

@SpringBootTest
public class QuestionsTest {
	
	
	
	@Autowired 
	QuestionsService questionsService;
	
	@Test
	private void create() {
		QuestionsReq req = new QuestionsReq();
		req.setTitle("§Ú·R¹Åªå");
		req.setDetails("c8 c8 c8 c8 c8 c8 c8 c8 c8 c88 88 8c8 c8 87");
		
		
	}

}
