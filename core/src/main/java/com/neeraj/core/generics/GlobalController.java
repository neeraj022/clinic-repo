package com.neeraj.core.generics;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neeraj.core.spring.AdvancedQueryBuilder;

@Controller
public class GlobalController<T> {

	private GlobalService<T> globalService;

	@RequestMapping(method = RequestMethod.GET, value = "/", produces = { "application/json" })
	@ResponseBody
	public MyResponse search(HttpServletRequest httpServletRequest) {
		MyResponse myResponse = globalService.search(httpServletRequest);
		return myResponse;

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/", produces = { "application/json" })
	@ResponseBody
	public MyResponse update(@RequestBody T t) {
		MyResponse myResponse = globalService.update(t);
		return myResponse;

	}

	@RequestMapping(method = RequestMethod.POST, value = "/", produces = { "application/json" })
	@ResponseBody
	public MyResponse create(@RequestBody T t) {
		MyResponse myResponse = globalService.create(t);
		return myResponse;

	}

	@RequestMapping(method = RequestMethod.POST, value = "mysearch/", produces = { "application/json" })
	@ResponseBody
	public MyResponse advancedSearch(@RequestBody AdvancedQueryBuilder queryBuilder) {
		MyResponse myResponse = globalService.advancedSearch(queryBuilder);
		return myResponse;

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/", produces = { "application/json" })
	@ResponseBody
	public MyResponse delete(@RequestBody T t) {
		MyResponse myResponse = globalService.delete(t);
		return myResponse;

	}

	public GlobalService<T> getGlobalService() {
		return globalService;
	}

	public void setGlobalService(GlobalService<T> globalService) {
		this.globalService = globalService;
	}
}
