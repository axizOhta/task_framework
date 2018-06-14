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

import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.entity.UserInfoForm;
import jp.co.axiz.web.service.UserInfoService;

@Controller
public class UpdateController {

	@Autowired
	private UserInfoService uIS;

	@Autowired
	HttpSession session;

	@RequestMapping(value="/update", method = RequestMethod.GET)
	public String returnUpdate(@ModelAttribute("command") UserInfoForm form, Model model) {
		return "update";
	}

	@RequestMapping({ "/updateinput"})
	public String returnUpdateInput(@ModelAttribute("command") UserInfoForm form, Model model) {
		return "updateInput";
	}


	@RequestMapping(value="/updateInput", method=RequestMethod.POST)
	public String update(@Validated @ModelAttribute("command") UserInfoForm fm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "update";
		}

		if(fm.getId().equals("")) {
			model.addAttribute( "error","必須項目を入力してください");

			return "update";
		}else {

			Integer id  = Integer.parseInt(fm.getId());
			UserInfo user = uIS.findById(id);

			if(user == null) {
				model.addAttribute( "error","入力されたデータは存在しません");

				return "update";
			}else {
				session.setAttribute("beforeUser", user);
			}
		}

		return "updateInput";
	}

	@RequestMapping(value="/updateConfirm", method=RequestMethod.POST)
	public String updateConfirm(@Validated @ModelAttribute("command") UserInfoForm fm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "updateInput";
		}
		String newName = fm.getNewName();
		String newTel = fm.getNewTel();
		String newPass = fm.getNewPass();

		UserInfo beforeUser = (UserInfo)session.getAttribute("beforeUser");

		Integer id = beforeUser.getUserId();

		UserInfo newUser = new UserInfo(id, newName, newTel, newPass);

		session.setAttribute("NewUser", newUser);
		session.setAttribute("defo_id", id);

		if(beforeUser.getPassword().equals(newUser.getPassword())) {
			session.setAttribute("Pass", beforeUser.getPassword());

		}else {
			session.setAttribute("Pass", "");
		}

		if(newName.equals(beforeUser.getUserName()) && newTel.equals(beforeUser.getTelephone()) && newPass.equals(beforeUser.getPassword())) {
			model.addAttribute("error", "1項目以上変更してください。");

			return "updateInput";
		}

		return "updateConfirm";
	}


	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String updateResult(@ModelAttribute("command") UserInfoForm fm, Model model) {
		String rePass = fm.getRePass();
		UserInfo beforeUser = (UserInfo)session.getAttribute("beforeUser");
		UserInfo newUser = (UserInfo)session.getAttribute("NewUser");

		String newName =newUser.getUserName();
		String newTel = newUser.getTelephone();
		String newPass = newUser.getPassword();

		if(newName != null && newTel != null && newPass != null){
			if(!(newName.equals(beforeUser.getUserName())) && !(newName.equals(""))) {
				if(newPass.equals(rePass)){
					uIS.updateName(newUser);
				}else {
					model.addAttribute("error", "前画面で入力したパスワードと一致しません。");

					return "updateConfirm";
				}
			}
			if(!(newTel.equals(beforeUser.getTelephone())) && !(newTel.equals(""))){
				if(newPass.equals(rePass)) {
					uIS.updateTel(newUser);
				}else {
					model.addAttribute("error", "前画面で入力したパスワードと一致しません。");

					return "updateConfirm";
				}
			}
			if(!(newPass.equals(beforeUser.getPassword())) && !(newPass.equals(""))){
				if(newPass.equals(rePass)) {
					uIS.updatePass(newUser);
				}else {
					model.addAttribute("error", "前画面で入力したパスワードと一致しません。");

					return "updateConfirm";
				}
			}
		}

		return "updateResult";
	}

}
