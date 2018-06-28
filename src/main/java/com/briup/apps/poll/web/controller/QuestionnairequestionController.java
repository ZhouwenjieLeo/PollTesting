package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.QuestionnaireQuestion;
import com.briup.apps.poll.bean.extend.QuestionnaireQuestionVM;
import com.briup.apps.poll.bean.extend.QuestionnaireVM;
import com.briup.apps.poll.service.IQuestionnairequestionService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "问题问卷桥表相关API接口")
@RestController
@RequestMapping("/questionnairequestion")
public class QuestionnairequestionController {
	@Autowired
	private IQuestionnairequestionService questionnairequestionService;

	@ApiOperation(value = "查询所有问题问卷桥表信息")
	@GetMapping("findAllQuestionnairequestion")
	public MsgResponse findAllQuestionnairequestion() {
		try {
			List<QuestionnaireQuestion> list = questionnairequestionService.findAll();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value="查询出所有的问卷桥表信息",notes="每个问卷桥表信息中包含所有的题目和问卷的的信息")
	@GetMapping("findAllQuestionnaireQuestionVM")
	public MsgResponse findAllQuestionnaireQuestionVM(){
		try {
			List<QuestionnaireQuestionVM> list = questionnairequestionService.findAllQuestionnaireQuestionVM();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value = "保存问题问卷桥表信息")
	@PostMapping("saveQuestionnairequestion")
	public MsgResponse saveQuestionnairequestion(QuestionnaireQuestion questionnairequestion) {
		try {
//			questionnairequestionService.save(questionnairequestionVM);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

//	@ApiOperation(value = "修改问题问卷桥表信息")
//	@PostMapping("updateQuestionnairequestion")
//	public MsgResponse updateQuestionnairequestion(QuestionnaireQuestion questionnairequestion) {
//		try {
//			questionnairequestionService.update(questionnairequestion);
//			return MsgResponse.success("success", null);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MsgResponse.error(e.getMessage());
//		}
//	}

	@ApiOperation(value = "通过id删除问题问卷桥表信息")
	@GetMapping("deleteByIdQuestionnairequestion")
	public MsgResponse deleteByIdQuestionnairequestion(@RequestParam long id) {
		try {
			questionnairequestionService.deleteById(id);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
}
