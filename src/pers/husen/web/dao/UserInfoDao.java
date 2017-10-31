package pers.husen.web.dao;

import pers.husen.web.bean.vo.UserInfoVo;


/**
 * 用户信息接口
 * 
 * @author 何明胜
 *
 * 2017年9月17日
 */

public interface UserInfoDao {
	/**
	 * 根据用户名查询密码
	 * @param userName
	 * @return
	 */
	public String queryPasswordByUserName(String userName);
	
	/**
	 * 插入新注册的用户信息
	 * @param userInfoVo
	 * @return
	 */
	public int insertUserInfo(UserInfoVo userInfoVo);
	
	/**
	 * 根据用户名查询用户信息
	 * @param userName
	 * @return
	 */
	public UserInfoVo queryUserInfoByName(String userName);
	
	/**
	 * 根据id更新用户信息
	 * @param uVo
	 * @return
	 */
	public int updateUserInfoById(UserInfoVo uVo);
	
	/**
	 * 根据用户名改密码
	 * @param uVo
	 * @return
	 */
	public int updateUserPwdByName(UserInfoVo uVo);
	
	/**
	 * 根据用户名和邮箱修改密码
	 * @param uVo
	 * @return
	 */
	public int updateUserPwdByNameAndEmail(UserInfoVo uVo);
}