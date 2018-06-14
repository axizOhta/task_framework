package jp.co.axiz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.web.entity.UserInfoForm;

@Controller
public class IndexController {

	@RequestMapping(value = "/index", method= RequestMethod.GET)
	public String startGET(Model model) {
		return "index";
	}

	@RequestMapping({ "/menu"})
	public String returnMenu(UserInfoForm form, Model model) {
		return "menu";
	}

}
