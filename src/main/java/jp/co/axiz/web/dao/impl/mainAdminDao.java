package jp.co.axiz.web.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.co.axiz.web.dao.AdminDao;
import jp.co.axiz.web.entity.Admin;

@Repository
public class mainAdminDao implements AdminDao {

	@Autowired
    private NamedParameterJdbcTemplate nPJT;

	@Transactional
    public Admin findById(String id, String pass) {

            SqlParameterSource param = new MapSqlParameterSource().addValue("id", id).addValue("pass",pass);

            try {
                return nPJT.queryForObject(
                        "SELECT * FROM admin WHERE admin_id = :id AND password = :pass",
                        param,
                        new BeanPropertyRowMapper<Admin>(Admin.class));
            } catch (EmptyResultDataAccessException e) {
                return null;
            }
    }

}
