package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Answers;
import com.briup.apps.poll.bean.School;
import com.briup.apps.poll.service.ISchoolService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="学校管理相关接口")
@RestController
@RequestMapping("/school")
public class SchoolController {
	@Autowired
	private ISchoolService schoolService;
	
	@ApiOperation(value="查询出所有的学校信息")
	@GetMapping("findAllSchool")
	public MsgResponse findAllSchool(){
		try {
			List<School> list = schoolService.findAll();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="保存或更新学校信息",notes="如果参数中包含了id，说明这是一个更新操作。如果参数中不包含id，说明这是一个保存操作")
	@PostMapping("saveOrUpdateSchool")
    public MsgResponse saveOrUpdateSchool(School school){
    	try {
    		if(school!=null&&school.getId()!=null){
    			schoolService.update(school);
    		}
    		else{
    			schoolService.save(school);	
    		}
    		return MsgResponse.success("保存或更新成功", null);
			
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
    } 
	
	@ApiOperation(value="通过id删除学校信息")
	@GetMapping("deleteSchoolById")
	public MsgResponse deleteSchoolById(@RequestParam Long id){
		try {
			schoolService.deleteById(id);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
}
