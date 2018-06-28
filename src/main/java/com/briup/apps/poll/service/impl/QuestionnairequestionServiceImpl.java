package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.QuestionnaireQuestion;
import com.briup.apps.poll.bean.QuestionnaireQuestionExample;
import com.briup.apps.poll.bean.UserExample;
import com.briup.apps.poll.bean.extend.QuestionnaireQuestionVM;
import com.briup.apps.poll.dao.QuestionMapper;
import com.briup.apps.poll.dao.QuestionnaireQuestionMapper;
import com.briup.apps.poll.dao.extend.QuestionnaireQuestionVMMapper;
import com.briup.apps.poll.service.IQuestionnairequestionService;

@Service
public class QuestionnairequestionServiceImpl implements IQuestionnairequestionService {
	@Autowired
	private QuestionnaireQuestionMapper questionnairequestionMapper;
	@Autowired 
	private QuestionnaireQuestionVMMapper questionnairequestionVMMapper;
	@Autowired
	private QuestionMapper questionMapper;

	@Override
	public List<QuestionnaireQuestion> findAll() throws Exception {
		QuestionnaireQuestionExample example = new QuestionnaireQuestionExample();
		return questionnairequestionMapper.selectByExample(example);
	}

	@Override
	public void deleteById(long id) throws Exception {
		questionnairequestionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<QuestionnaireQuestionVM> findAllQuestionnaireQuestionVM() throws Exception {
		return questionnairequestionVMMapper.selectAll();
	}

	@Override
	public void save(QuestionnaireQuestionVM questionnairequestionVM) throws Exception {
		
		
	}

}
