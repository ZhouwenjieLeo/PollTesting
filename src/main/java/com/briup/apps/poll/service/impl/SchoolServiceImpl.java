package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.CourseExample;
import com.briup.apps.poll.bean.School;
import com.briup.apps.poll.bean.SchoolExample;
import com.briup.apps.poll.dao.SchoolMapper;
import com.briup.apps.poll.service.ICourseService;
import com.briup.apps.poll.service.ISchoolService;

@Service
public class SchoolServiceImpl implements ISchoolService{

	@Autowired
	private SchoolMapper schoolMapper;
	
	@Override
	public List<School> findAll() throws Exception {
		//创建空模板
		SchoolExample example = new SchoolExample();
		//调用QBE查询，并且将查询结果返回
		return schoolMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public void save(School school) throws Exception {
		// TODO Auto-generated method stub
		schoolMapper.insert(school);
	}

	@Override
	public void update(School school) throws Exception {
		// TODO Auto-generated method stub
		schoolMapper.updateByPrimaryKey(school);
	}

	@Override
	public void deleteById(long id) throws Exception {
		// TODO Auto-generated method stub
		schoolMapper.deleteByPrimaryKey(id);
	}

}
