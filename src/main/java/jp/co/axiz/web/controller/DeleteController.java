package jp.co.axiz.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.entity.UserInfoForm;
import jp.co.axiz.web.service.UserInfoService;

@Controller
public class DeleteController {

	@Autowired
	private UserInfoService uIS;

	@Autowired
	HttpSession session;

	@RequestMapping(value="/delete", method=RequestMethod.GET )
	public String deleteIn(@ModelAttribute("command") UserInfoForm form, Model model) {
		return "delete";
	}

	@RequestMapping(value="/deleteConfirm", method = RequestMethod.POST)
	public String delete(@ModelAttribute("command") UserInfoForm form, Model model) {
		try {
			Integer id = Integer.parseInt(form.getId());
			UserInfo success = uIS.findById(id);
			boolean isSuccess=success!=null;
			if(isSuccess) {
				session.setAttribute("deleteUser", success);
				return "deleteConfirm";
			}else {
				model.addAttribute("msg", "指定したIDは存在しません。");
				return "delete";
			}
		}catch(Exception e){
			model.addAttribute("msg", "指定したIDは存在しません。");
			return "delete";

		}
	}

	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String deleteConfirm(@ModelAttribute("command") UserInfoForm form, Model model) {
		try {
			Integer id = Integer.parseInt(form.getId());
			System.out.println(id);
			uIS.deleteById(id);
			session.removeAttribute("defo_id");
			return "deleteResult";
		}catch(Exception e){
			return "delete";
		}
	}

}
