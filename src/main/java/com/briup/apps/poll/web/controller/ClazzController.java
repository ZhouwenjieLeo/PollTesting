package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Clazz;
import com.briup.apps.poll.bean.extend.ClazzVM;
import com.briup.apps.poll.service.IClazzService;
import com.briup.apps.poll.service.ICourseService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="班级相关接口")
@RestController
@RequestMapping("/clazz")
public class ClazzController {

	/**
	 * @return
	 */
	@Autowired
	private IClazzService clazzService;
	
	@ApiOperation(value="查询出所有的班级信息")
	@GetMapping("findAllClazz")
	public MsgResponse findAllClazz(){
		try {
			List<Clazz> list = clazzService.findAll();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="查询出所有的班级信息",notes="每个班级信息中包含所属年级和年级所属的班主任的信息")
	@GetMapping("findAllClazzVM")
	public MsgResponse findAllClazzVM(){
		try {
			List<ClazzVM> list = clazzService.findAllClazzVM();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="通过关键字查询出班级信息")
	@GetMapping("queryClazz")
	public MsgResponse queryClazz(String keywords){
		try {
			List<Clazz> list = clazzService.query(keywords);
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="通过id删除班级信息")
	@GetMapping("deleteClazzById")
	public MsgResponse deleteClazzById(@RequestParam Long id){
		try {
			clazzService.deleteById(id);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="添加或修改班级信息",notes="如果班级id不为空，表示更新操作;如果班级id为空，表示保存操作")
	@PostMapping("saveOrUpdateClazz")
	public MsgResponse saveOrUpdateClazz(Clazz clazz){
		try {
			
            clazzService.saveOrUpdate(clazz);
		return MsgResponse.success("提交成功!", null);
	} catch (Exception e) {
		e.printStackTrace();
		return MsgResponse.error(e.getMessage());
	}
}
	
	@ApiOperation(value="批量删除班级信息")
	@GetMapping("batchDeleteClazz")
	public MsgResponse batchDeleteClazz(long[] ids){
		try {
			clazzService.batchDelete(ids);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
}
