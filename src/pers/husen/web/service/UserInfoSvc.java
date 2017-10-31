package pers.husen.web.service;

import pers.husen.web.bean.vo.UserInfoVo;
import pers.husen.web.dao.impl.UserInfoDaoImpl;


/**
 * @author 何明胜
 *
 * 2017年9月17日
 */

public class UserInfoSvc {
	private UserInfoDaoImpl uImpl = new UserInfoDaoImpl();
	
	public String queryPasswordByUserName(String userName) {
		return uImpl.queryPasswordByUserName(userName);
	}
	
	public int insertUserInfo(UserInfoVo uVo) {
		return uImpl.insertUserInfo(uVo);
	}
	
	public UserInfoVo queryUserInfoByName(String userName) {
		return uImpl.queryUserInfoByName(userName);
	}
	
	public int updateUserInfoById(UserInfoVo uVo) {
		return uImpl.updateUserInfoById(uVo);
	}
	
	public int updateUserPwdByName(UserInfoVo uVo) {
		return uImpl.updateUserPwdByName(uVo);
	}
	
	public int updateUserPwdByNameAndEmail(UserInfoVo uVo) {
		return uImpl.updateUserPwdByNameAndEmail(uVo);
	}
}