package pers.husen.web.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import pers.husen.web.bean.vo.UserInfoVo;
import pers.husen.web.dao.UserInfoDao;
import pers.husen.web.dbutil.DbQueryUtils;
import pers.husen.web.dbutil.DbManipulationUtils;
import pers.husen.web.dbutil.mappingdb.UserInfoMapping;


/**
 * @author 何明胜
 *
 * 2017年9月17日
 */

public class UserInfoDaoImpl implements UserInfoDao{
	@Override
	public String queryPasswordByUserName(String userName) {
		String sql = "SELECT user_password FROM user_info WHERE user_name = ?";
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(userName);
		
		return DbQueryUtils.queryStringByParam(sql, paramList);
	}

	@Override
	public int insertUserInfo(UserInfoVo uVo) {
		String sql = "INSERT INTO user_info "
				+ "(user_name, user_password, user_email, user_phone, user_age, user_sex , user_address) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)"; 
		
		ArrayList<Object> paramList = new ArrayList<Object>();
		Object obj = null;
		paramList.add((obj = uVo.getUserName()) != null ? obj : new Date());
		paramList.add((obj = uVo.getUserPassword()) != null ? obj : "");
		paramList.add((obj = uVo.getUserEmail()) != null ? obj : "");
		paramList.add((obj = uVo.getUserPhone()) != null ? obj : "");
		paramList.add((obj = uVo.getUserAge()) != null ? obj : "");
		paramList.add((obj = uVo.getUserSex()) != null ? obj : "");
		paramList.add((obj = uVo.getUserAddress()) != null ? obj : 0);
		
		return DbManipulationUtils.insertNewRecord(sql, paramList);
	}

	@Override
	public UserInfoVo queryUserInfoByName(String userName) {
		String sql = "SELECT "
				+ UserInfoMapping.USER_ID + ", "
				+ UserInfoMapping.USER_NAME + ", "
				+ UserInfoMapping.USER_NICK_NAME + ", "
				+ UserInfoMapping.USER_EMAIL + ", "
				+ UserInfoMapping.USER_PHONE + ","
				+ UserInfoMapping.USER_AGE + ","
				+ UserInfoMapping.USER_SEX + ", "
				+ UserInfoMapping.USER_ADDRESS + ", "
				+ UserInfoMapping.USRE_HEAD_URL
				+ " FROM "
				+ UserInfoMapping.DB_NAME
				+ " WHERE user_name = ?";
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(userName);
		
		return DbQueryUtils.queryBeanByParam(sql, paramList, UserInfoVo.class);
	}

	@Override
	public int updateUserInfoById(UserInfoVo uVo) {
		String sql = "UPDATE " + UserInfoMapping.DB_NAME + " SET "
				+ UserInfoMapping.USER_NAME + "=?, "
				+ UserInfoMapping.USER_EMAIL + "=?, "
				+ UserInfoMapping.USER_PHONE + "=?, "
				+ UserInfoMapping.USER_AGE + "=?, "
				+ UserInfoMapping.USER_SEX + "=?, "
				+ UserInfoMapping.USER_ADDRESS + "=?, "
				+ UserInfoMapping.USRE_HEAD_URL + "=?, "
				+ UserInfoMapping.USER_NICK_NAME + "=? "
				+ "WHERE "
				+ UserInfoMapping.USER_ID + "=?";
		
		ArrayList<Object> paramList = new ArrayList<Object>();
		
		paramList.add(uVo.getUserName());
		paramList.add(uVo.getUserEmail());
		paramList.add(uVo.getUserPhone());
		paramList.add(uVo.getUserAge());
		paramList.add(uVo.getUserSex());
		paramList.add(uVo.getUserAddress());
		paramList.add(uVo.getUserHeadUrl());
		paramList.add(uVo.getUserNickName());
		
		paramList.add(uVo.getUserId());
		
		return DbManipulationUtils.updateRecordByParam(sql, paramList);
	}

	@Override
	public int updateUserPwdByName(UserInfoVo uVo) {
		String sql = "UPDATE " + UserInfoMapping.DB_NAME + " SET "
				+ UserInfoMapping.USER_PASSWORD + "=? "
				+ "WHERE "
				+ UserInfoMapping.USER_NAME + "=?";
		
		ArrayList<Object> paramList = new ArrayList<Object>();
		
		paramList.add(uVo.getUserPassword());
		paramList.add(uVo.getUserName());
		
		return DbManipulationUtils.updateRecordByParam(sql, paramList);
	}

	@Override
	public int updateUserPwdByNameAndEmail(UserInfoVo uVo) {
		String sql = "UPDATE " + UserInfoMapping.DB_NAME + " SET "
				+ UserInfoMapping.USER_PASSWORD + "=? "
				+ "WHERE "
				+ UserInfoMapping.USER_NAME + "=? "
				+ "AND "
				+ UserInfoMapping.USER_EMAIL + "=?";
		
		ArrayList<Object> paramList = new ArrayList<Object>();
		
		paramList.add(uVo.getUserPassword());
		paramList.add(uVo.getUserName());
		paramList.add(uVo.getUserEmail());
		
		return DbManipulationUtils.updateRecordByParam(sql, paramList);
	}

	@Override
	public int updateUserEmailByName(UserInfoVo uVo) {
		String sql = "UPDATE " + UserInfoMapping.DB_NAME + " SET "
				+ UserInfoMapping.USER_EMAIL + "=? "
				+ "WHERE "
				+ UserInfoMapping.USER_NAME + "=? ";
		
		ArrayList<Object> paramList = new ArrayList<Object>();
		
		paramList.add(uVo.getUserEmail());
		paramList.add(uVo.getUserName());
		
		return DbManipulationUtils.updateRecordByParam(sql, paramList);
	}

	@Override
	public int queryUserTotalCount(UserInfoVo uVo) {
		String sql = "SELECT count(*) as count FROM " + UserInfoMapping.DB_NAME;
				
		return DbQueryUtils.queryIntByParam(sql, new ArrayList<Object>());
	}

	@Override
	public ArrayList<UserInfoVo> queryUserPerPage(UserInfoVo uVo,int pageSize, int pageNo) {
		String sql = "SELECT "
				+ UserInfoMapping.USER_ID + ", "
				+ UserInfoMapping.USER_NAME + ", "
				+ UserInfoMapping.USER_NICK_NAME + ", "
				+ UserInfoMapping.USER_EMAIL + ", "
				+ UserInfoMapping.USER_PHONE + ","
				+ UserInfoMapping.USER_AGE + ","
				+ UserInfoMapping.USER_SEX + ", "
				+ UserInfoMapping.USER_ADDRESS + ", "
				+ UserInfoMapping.USRE_HEAD_URL + ", "
				+ UserInfoMapping.USER_PASSWORD
				+ " FROM "
				+ UserInfoMapping.DB_NAME;
		
		sql += " ORDER BY user_id LIMIT " + pageSize + " OFFSET " + pageNo;
		
		return DbQueryUtils.queryBeanListByParam(sql, new ArrayList<Object>(), UserInfoVo.class);
	}
}