package jp.co.axiz.web.service;

import jp.co.axiz.web.entity.Admin;

public interface AdminService {

	public Admin findById(String id, String pass);

}
