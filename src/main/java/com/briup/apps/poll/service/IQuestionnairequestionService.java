package com.briup.apps.poll.service;

import java.util.List;

import com.briup.apps.poll.bean.QuestionnaireQuestion;
import com.briup.apps.poll.bean.extend.QuestionnaireQuestionVM;

public interface IQuestionnairequestionService {
	List<QuestionnaireQuestion> findAll() throws Exception;
	
	List<QuestionnaireQuestionVM> findAllQuestionnaireQuestionVM() throws Exception;

	void save(QuestionnaireQuestionVM questionnairequestionVM) throws Exception;

//	void update(QuestionnaireQuestion questionnairequestion) throws Exception;

	void deleteById(long id) throws Exception;
}
