package jp.co.axiz.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.entity.UserInfoForm;
import jp.co.axiz.web.service.UserInfoService;

@Controller
public class SelectController {

	@Autowired
	private UserInfoService uIS;

	@Autowired
	HttpSession session;

	@RequestMapping(value="/select", method = RequestMethod.GET )
	public String select (@ModelAttribute("command") UserInfoForm form, Model model) {

		return "select";
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String selectResult (@Validated @ModelAttribute("command") UserInfoForm form,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "エラー");
			return "select";
		}

		List<UserInfo> list = uIS.search(form.getId(), form.getName(), form.getTel());
		if (list == null || list.size() == 0) {
			model.addAttribute("msg", "入力されたデータはありませんでした");
			return "select";
		} else {
			model.addAttribute("user_info", list);
			return "selectResult";
		}
	}

}
