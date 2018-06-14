package jp.co.axiz.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.web.dao.AdminDao;
import jp.co.axiz.web.entity.Admin;
import jp.co.axiz.web.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	@Override
	public Admin findById(String id,String pass) {
		return adminDao.findById(id,pass);
	}

}
