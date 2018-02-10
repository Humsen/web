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
	private UserInfoDaoImpl uImpl = new UserInfoDaoImpl();
	
	@Override
	public String queryPasswordByUserName(String userName) {
		return uImpl.queryPasswordByUserName(userName);
	}
	
	@Override
	public int insertUserInfo(UserInfoVo uVo) {
		return uImpl.insertUserInfo(uVo);
	}
	
	@Override
	public UserInfoVo queryUserInfoByName(String userName) {
		return uImpl.queryUserInfoByName(userName);
	}
	
	@Override
	public int updateUserInfoById(UserInfoVo uVo) {
		return uImpl.updateUserInfoById(uVo);
	}
	
	@Override
	public int updateUserPwdByName(UserInfoVo uVo) {
		return uImpl.updateUserPwdByName(uVo);
	}
	
	@Override
	public int updateUserPwdByNameAndEmail(UserInfoVo uVo) {
		return uImpl.updateUserPwdByNameAndEmail(uVo);
	}
	
	@Override
	public int updateUserEmailByName(UserInfoVo uVo) {
		return uImpl.updateUserEmailByName(uVo);
	}

	@Override
	public int queryUserTotalCount(UserInfoVo uVo) {
		return uImpl.queryUserTotalCount(uVo);
	}

	@Override
	public ArrayList<UserInfoVo> queryUserPerPage(UserInfoVo uVo, int pageSize, int pageNo) {
		return uImpl.queryUserPerPage(uVo, pageSize, pageNo);
	}
}