package pers.husen.web.service;

import java.util.ArrayList;

import pers.husen.web.bean.vo.UserInfoVo;
import pers.husen.web.dao.UserInfoDao;
import pers.husen.web.dao.impl.UserInfoDaoImpl;


/**
 * @author 何明胜
 *
 * 2017年9月17日
 */

public class UserInfoSvc implements UserInfoDao{
	private static final UserInfoDaoImpl userInfoDaoImpl = new UserInfoDaoImpl();
	
	@Override
	public String queryPasswordByUserName(String userName) {
		return userInfoDaoImpl.queryPasswordByUserName(userName);
	}
	
	@Override
	public int insertUserInfo(UserInfoVo uVo) {
		return userInfoDaoImpl.insertUserInfo(uVo);
	}
	
	@Override
	public UserInfoVo queryUserInfoByName(String userName) {
		return userInfoDaoImpl.queryUserInfoByName(userName);
	}
	
	@Override
	public int updateUserInfoById(UserInfoVo uVo) {
		return userInfoDaoImpl.updateUserInfoById(uVo);
	}
	
	@Override
	public int updateUserPwdByName(UserInfoVo uVo) {
		return userInfoDaoImpl.updateUserPwdByName(uVo);
	}
	
	@Override
	public int updateUserPwdByNameAndEmail(UserInfoVo uVo) {
		return userInfoDaoImpl.updateUserPwdByNameAndEmail(uVo);
	}
	
	@Override
	public int updateUserEmailByName(UserInfoVo uVo) {
		return userInfoDaoImpl.updateUserEmailByName(uVo);
	}

	@Override
	public int queryUserTotalCount(UserInfoVo uVo) {
		return userInfoDaoImpl.queryUserTotalCount(uVo);
	}

	@Override
	public ArrayList<UserInfoVo> queryUserPerPage(UserInfoVo uVo, int pageSize, int pageNo) {
		return userInfoDaoImpl.queryUserPerPage(uVo, pageSize, pageNo);
	}
}