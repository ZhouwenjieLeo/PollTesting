package com.briup.apps.poll.service;

import java.util.List;

import com.briup.apps.poll.bean.Answers;
import com.briup.apps.poll.bean.Clazz;
import com.briup.apps.poll.bean.extend.ClazzVM;

public interface IClazzService {

    List<Clazz> findAll() throws Exception;
    
    List<ClazzVM> findAllClazzVM() throws Exception;
	
	List<Clazz> query(String keywords) throws Exception;
	
	void saveOrUpdate(Clazz clazz)  throws Exception;
	
	void deleteById(long id) throws Exception;
	
	void batchDelete(long[] ids) throws Exception;
	
}
