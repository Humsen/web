package pers.husen.web.dao;

import java.util.ArrayList;

import pers.husen.web.bean.vo.UserInfoVo;


/**
 * 用户信息接口
 * 
 * @author 何明胜
 *
 * 2017年9月17日
 */

public interface UserInfoDao {
	/*  增     */
	/**
	 * 插入新注册的用户信息
	 * @param userInfoVo
	 * @return
	 */
	public int insertUserInfo(UserInfoVo userInfoVo);
	
	/*  改     */
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
	
	/**
	 * 根据用户名修改用户邮箱
	 * @param uVo
	 * @return
	 */
	public int updateUserEmailByName(UserInfoVo uVo);
	
	/*  查     */
	/**
	 * 根据用户名查询密码
	 * @param userName
	 * @return
	 */
	public String queryPasswordByUserName(String userName);
	
	/**
	 * 根据用户名查询用户信息
	 * @param userName
	 * @return
	 */
	public UserInfoVo queryUserInfoByName(String userName);
	
	/**
	 * 根据条件查询总的有效用户数量
	 * @param uVo
	 * @return
	 */
	public int 	queryUserTotalCount(UserInfoVo uVo);
	
	/**
	 * 根据页面大小和页码查询一页的用户
	 * @param uVo
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public ArrayList<UserInfoVo> queryUserPerPage(UserInfoVo uVo, int pageSize, int pageNo);
}