package com.example.questionnaire.service.ifs;

import com.example.questionnaire.vo.QusDetailsReq;
import com.example.questionnaire.vo.QusDetailsRes;

public interface QusDetailsService {
	
	
	 //輸入問卷標題,統計該問卷所有問題的選項百分比
	 public QusDetailsRes statistics(QusDetailsReq req);
	 
}
