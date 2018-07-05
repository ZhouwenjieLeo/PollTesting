package com.briup.apps.poll.service;

import java.util.List;

import com.briup.apps.poll.bean.Answers;
import com.briup.apps.poll.bean.Course;
import com.briup.apps.poll.bean.extend.AnswersVM;
import com.briup.apps.poll.bean.extend.QuestionVM;


public interface IAnswersService {
	
	//提交或者更新答卷信息
	   void saveOrUpdate(Answers answers)  throws Exception;
	   
	   List<Answers> findAll() throws Exception;
	   
	   void deleteById(long id) throws Exception;
	   //通过课调id查找答案
	   List<Answers> findAnswersBySurveyId(long surveyId) throws Exception;

       
	   List<AnswersVM> findAllAnswersVM() throws Exception;
}
