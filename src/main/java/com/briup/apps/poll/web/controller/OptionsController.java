package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Course;
import com.briup.apps.poll.bean.Options;
import com.briup.apps.poll.service.ICourseService;
import com.briup.apps.poll.service.IOptionsService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="选项相关接口")
@RestController
@RequestMapping("/options")
public class OptionsController {

	/**
	 * @return
	 */
	@Autowired
	private IOptionsService optionsService;
	
	@ApiOperation(value="查询出所有的选项信息")
	@GetMapping("findAllOptions")
	public MsgResponse findAllOptions(){
		try {
			List<Options> list = optionsService.findAll();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="通过关键字查询出选项信息")
	@GetMapping("queryOptions")
	public MsgResponse queryOptions(String keywords){
		try {
			List<Options> list = optionsService.query(keywords);
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="通过id删除选项信息")
	@GetMapping("deleteOptionsById")
	public MsgResponse deleteOptionsById(@RequestParam Long id){
		try {
			optionsService.deleteById(id);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="添加选项信息",notes="如果选项id不为空，表示更新操作;如果选项id为空，表示保存操作")
	@PostMapping("saveOrUpdateOptions")
	public MsgResponse saveOrUpdateOptions(Options options){
		try {
			if(options!=null&&options.getId()!=null){
				optionsService.update(options);
			}else{
				optionsService.save(options);
			}
			
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
//	@ApiOperation(value="修改选项信息")
//	@PostMapping("updateOptions")
//	public MsgResponse updateOptions(Options options){
//		try {
//			optionsService.update(options);
//			return MsgResponse.success("success", null);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MsgResponse.error(e.getMessage());
//		}
//	}
	
	@ApiOperation(value="批量删除选项信息")
	@GetMapping("batchDeleteOptions")
	public MsgResponse batchDeleteOptions(long[] ids){
		try {
			optionsService.batchDelete(ids);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
}
