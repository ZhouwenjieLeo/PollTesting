package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Answers;
import com.briup.apps.poll.bean.extend.AnswersVM;
import com.briup.apps.poll.bean.extend.QuestionVM;
import com.briup.apps.poll.service.IAnswersService;
import com.briup.apps.poll.service.IGradeService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "答案管理相关接口")
@RestController
@RequestMapping("/answers")

public class AnswersController {
	@Autowired
	private IAnswersService answersService;
	
	@ApiOperation(value="保存或更新答案信息",notes="如果参数中包含了id，说明这是一个更新操作。如果参数中不包含id，说明这是一个保存操作")
	@PostMapping("saveOrUpdateAnswers")
    public MsgResponse saveOrUpdateAnswers(Answers answers){
    	try {
    		if(answers!=null&&answers.getId()!=null){
    		answersService.update(answers);
    		}
    		else{
    			answersService.save(answers);	
    		}
    		return MsgResponse.success("保存或更新成功", null);
			
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
    } 
	
	
	
	
	@ApiOperation(value = "查询所有答案信息")
	@GetMapping("findAllAnswers")
	public MsgResponse findAllAnswers() {
		try {
			List<Answers> list = answersService.findAll();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value="查询所有的答案信息",notes="每个答案信息中包含对应该题目下所有的课调信息")
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
	@ApiOperation(value = "通过关键字查询答案信息")
	@GetMapping("queryAnswers")
	public MsgResponse queryAnswers(String keywords) {
		try {
			List<Answers> list = answersService.query(keywords);
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

//	@ApiOperation(value = "添加答案信息")
//	@PostMapping("saveAnswers")
//	public MsgResponse saveAnswers(Answers answers) {
//		try {
//			answersService.save(answers);
//			return MsgResponse.success("success", null);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MsgResponse.error(e.getMessage());
//		}
//	}

//	@ApiOperation(value = "修改答案信息")
//	@PostMapping("updateAnswers")
//	public MsgResponse updateAnswers(Answers answers) {
//		try {
//			answersService.update(answers);
//			return MsgResponse.success("success", null);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MsgResponse.error(e.getMessage());
//		}
//	}

	@ApiOperation(value = "通过ID删除答案信息")
	@GetMapping("deleteByIdAnswers")
	public MsgResponse deleteByIdAnswers(long id) {
		try {
			answersService.deleteById(id);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value = "批量删除答案信息")
	@GetMapping("batchDeleteAnswers")
	public MsgResponse batchDeleteAnswers(long[] ids) {
		try {
			answersService.batchDelete(ids);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

}
