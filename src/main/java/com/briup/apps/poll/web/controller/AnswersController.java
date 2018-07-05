package com.briup.apps.poll.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Answers;
import com.briup.apps.poll.bean.Course;
import com.briup.apps.poll.bean.extend.AnswersVM;
import com.briup.apps.poll.service.IAnswersService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "学生答卷相关接口")
@RestController
@RequestMapping("/answers")

public class AnswersController {
	
	@Autowired
	private IAnswersService answersService;
	
	@ApiOperation(value="提交答卷，每个学生提交一份")
	@PostMapping("sumitAnswers")
	public MsgResponse sumitAnswers(Answers answers) {
		try {
			
			//1.判断用户是否有一个答卷的权限（是否提交过）
			//2.保存答卷信息
                 answersService.saveOrUpdate(answers);
			return MsgResponse.success("提交成功！您的意见是我们改进的方向！", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="查询出所有的答卷信息")
	@GetMapping("findAllAnswers")
	public MsgResponse findAllAnswers(){
		try {
			List<Answers> list = answersService.findAll();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="查询所有的问卷信息",notes="每个问卷信息中包含所有的课调的信息")
	@GetMapping("findAllAnswersVM")
	public MsgResponse findAllAnswersVM(){
		try {
			List<AnswersVM> list = answersService.findAllAnswersVM();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="通过id删除答卷信息")
	@GetMapping("deleteAnswersById")
	public MsgResponse deleteAnswersById(@RequestParam Long id){
		try {
			answersService.deleteById(id);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
}
