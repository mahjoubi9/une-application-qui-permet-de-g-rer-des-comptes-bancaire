package org.lsi.sec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class securitycontroller {
	@RequestMapping(value="/login")
public String login() {
	return"login";
}
	
	@RequestMapping(value="/")
	public String home() {
		return"redirect:/clients";
	}
}
