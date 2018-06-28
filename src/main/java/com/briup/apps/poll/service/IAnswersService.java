package com.briup.apps.poll.service;

import java.util.List;

import com.briup.apps.poll.bean.Answers;
import com.briup.apps.poll.bean.extend.AnswersVM;


public interface IAnswersService {
	List<Answers> findAll() throws Exception;
	
	List<Answers> query(String keywords) throws Exception;
	
	List<AnswersVM> findAllAnswersVM() throws Exception;
	
	void save(Answers answers) throws Exception;
	
	void update(Answers answers) throws Exception;
	
	void deleteById(long id) throws Exception;
	
	void batchDelete(long[] ids) throws Exception;

}
