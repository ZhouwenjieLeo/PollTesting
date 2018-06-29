package com.briup.apps.poll.bean.extend;

import java.util.List;

import com.briup.apps.poll.bean.Options;

public class QuestionVM {

	private Long id;
	private String name;
	private String QuestionType;
	
	private List<Options> options;     //一个题目所对应的所有选项信息

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuestionType() {
		return QuestionType;
	}

	public void setQuestionType(String questionType) {
		QuestionType = questionType;
	}

	public List<Options> getOptions() {
		return options;
	}

	public void setOptions(List<Options> options) {
		this.options = options;
	}
	
}
