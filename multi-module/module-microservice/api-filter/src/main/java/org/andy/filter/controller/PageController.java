package org.andy.filter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @author wiiyaya
 * @date 2018/11/30.
 */
@ApiIgnore
@Controller
public class PageController {

	@RequestMapping(value = { "/swagger" ,""})
	public String index(Map<String, Object> model) {
		return "redirect:swagger-ui.html";
	}

}