package jp.co.axiz.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.web.entity.Admin;
import jp.co.axiz.web.entity.AdminForm;
import jp.co.axiz.web.service.AdminService;

@Controller
public class AuthController {

	@Autowired
	private AdminService adminService;

	@Autowired
	HttpSession session;

	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String index(@ModelAttribute("login") AdminForm form, Model model) {
		return "login";
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String list(@Validated @ModelAttribute("login") AdminForm fm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "login";
		}
		String id = fm.getId();
		String pass = fm.getPass();
		Admin admin = adminService.findById(id,pass);
		if(admin==null) {
			model.addAttribute("error", "IDまたはPASSが間違っています");
			return "login";
		}else if(id.equals("") || pass.equals("")) {
			model.addAttribute("error", "いずれかの値が未入力です");
			return "login";
		}
		session.setAttribute("controller", admin.getAdminName());

		return "menu";
	}

	@RequestMapping("/logout")
	public String logout (HttpSession session, Model model) {

		session.invalidate();

		return "logout";
	}

}
